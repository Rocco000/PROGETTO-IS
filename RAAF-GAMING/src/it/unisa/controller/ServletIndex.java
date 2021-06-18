package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModelDAO;
import it.unisa.model.VideogiocoBean;
import it.unisa.model.VideogiocoModelDAO;

/**
 * Servlet implementation class ServletIndex
 */
@WebServlet("/servletindex")
public class ServletIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ServletIndex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		synchronized(session)
		{
			Object logB= session.getAttribute("log");
			boolean log;
			if(logB!=null) {
				log = (Boolean) logB;
			}
			else
			{
				log = false;
				session.setAttribute("log", log);
			}
			
			if(log == true)
			{
				String impostazione1 = "LogOut";
				String impostazione2="Profilo";
				String impostazione3="I miei ordini";
				ArrayList<String> array = new ArrayList<String>();
				array.add(impostazione1);
				array.add(impostazione2);
				array.add(impostazione3);
				request.setAttribute("impostazione",array);
				String impostazione4 = "servletlogout";
				String impostazione5="#";
				String impostazione6="#";
				ArrayList<String> array2 = new ArrayList<String>();
				array2.add(impostazione4);
				array2.add(impostazione5);
				array2.add(impostazione6);
				request.setAttribute("impostazione2",array2);
			}
			else
			{
				String impostazione1 = "Login";
				String impostazione2="Registrati";
				ArrayList<String> array = new ArrayList<String>();
				array.add(impostazione1);
				array.add(impostazione2);
				request.setAttribute("impostazione",array);
				String impostazione4 = "servletloginfirst";
				String impostazione5="#";
				ArrayList<String> array2 = new ArrayList<String>();
				array2.add(impostazione4);
				array2.add(impostazione5);
				request.setAttribute("impostazione2",array2);
			}
		}
		
		DataSource ds = (DataSource)super.getServletContext().getAttribute("DataSource");
		VideogiocoModelDAO vdao= new VideogiocoModelDAO(ds);
		try {
			VideogiocoBean migliorVideogioco= vdao.getTopRecensione();
			VideogiocoBean ultimoUscito= vdao.getUltimoUscito();
			ArrayList<VideogiocoBean> scontati= vdao.getVideogiochiScontati();
			ProdottoModelDAO dao = new ProdottoModelDAO(ds);
			ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
			prod.add(dao.doRetriveByKey(""+migliorVideogioco.getProdotto()));
			prod.add(dao.doRetriveByKey(""+ultimoUscito.getProdotto()));
			prod.add(dao.doRetriveByKey(""+scontati.get(0).getProdotto()));
			prod.add(dao.doRetriveByKey(""+scontati.get(1).getProdotto()));
			prod.add(dao.doRetriveByKey(""+scontati.get(2).getProdotto()));
			prod.add(dao.doRetriveByKey(""+scontati.get(3).getProdotto()));
			request.setAttribute("Prodotti",prod);
			request.setAttribute("visitato","");
			RequestDispatcher dispatcher= super.getServletContext().getRequestDispatcher("/homepage.jsp");
			dispatcher.forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	

}