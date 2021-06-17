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
			request.setAttribute("impostazione1", impostazione1);
			request.setAttribute("impostazione2", impostazione2);
			request.setAttribute("impostazione3", impostazione3);
		}
		else
		{
			String impostazione1 = "Login";
			String impostazione2="Registrati";
			request.setAttribute("impostazione1", impostazione1);
			request.setAttribute("impostazione2", impostazione2);
		}
		
		VideogiocoModelDAO vdao= new VideogiocoModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
		try {
			VideogiocoBean migliorVideogioco= vdao.getTopRecensione();
			VideogiocoBean ultimoUscito= vdao.getUltimoUscito();
			ArrayList<VideogiocoBean> scontati= vdao.getVideogiochiScontati();
			
			
			
			request.setAttribute("migliorVideogioco", migliorVideogioco);
			request.setAttribute("ultimoUscito", ultimoUscito);
			request.setAttribute("scontati", scontati);
			RequestDispatcher dispatcher= super.getServletContext().getRequestDispatcher("/lol.jsp");
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