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

import it.unisa.model.AbbonamentoBean;
import it.unisa.model.AbbonamentoModelDAO;
import it.unisa.model.ConsoleBean;
import it.unisa.model.ConsoleModelDAO;
import it.unisa.model.DlcBean;
import it.unisa.model.DlcModelDAO;
import it.unisa.model.ParteDiBean;
import it.unisa.model.ParteDiModelDAO;
import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModelDAO;
import it.unisa.model.VideogiocoBean;
import it.unisa.model.VideogiocoModelDAO;

@WebServlet("/servletcategorie")
public class ServletCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String per = request.getParameter("per");
		
		if(per==null)
		{
			String url="servletindex";
			url= response.encodeURL(url);
			response.sendRedirect(url);
			return;
		}
		
		else
		{
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
					String impostazione5="servletaccessoprofilo";
					String impostazione6="servletordini";
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
					String impostazione5="ServletRegistrazione";
					ArrayList<String> array2 = new ArrayList<String>();
					array2.add(impostazione4);
					array2.add(impostazione5);
					request.setAttribute("impostazione2",array2);
				}
				ArrayList<String> carr = (ArrayList<String>) session.getAttribute("carrello");
				if(carr == null)
				{
					request.setAttribute("carrello",null);
				}
				else if(carr.size()==0)
				{
					request.setAttribute("carrello",null);
				}
				else
				{
					request.setAttribute("carrello",carr);
				}
			}
			DataSource ds = (DataSource)super.getServletContext().getAttribute("DataSource");
			if(per.equals("catalogo"))
			{
				ProdottoModelDAO dao = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> bean = new ArrayList<ProdottoBean>();
				try {
						bean = dao.doRetriveAll("");
				} catch (SQLException e) {
					e.printStackTrace();
				}
					request.setAttribute("prodotti",bean);
					request.setAttribute("visitato","");
					RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
					view.forward(request, response);
					return;
			}
			else if(per.equals("azione"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				ParteDiModelDAO dao = new ParteDiModelDAO(ds);
				try {
					ArrayList<ParteDiBean> bean = dao.doRetriveByCategoria("Azione");
					for(ParteDiBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getVideogioco()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else if(per.equals("avventura"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				ParteDiModelDAO dao = new ParteDiModelDAO(ds);
				try {
					ArrayList<ParteDiBean> bean = dao.doRetriveByCategoria("Avventura");
					for(ParteDiBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getVideogioco()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else if(per.equals("battleroyale"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				ParteDiModelDAO dao = new ParteDiModelDAO(ds);
				try {
					ArrayList<ParteDiBean> bean = dao.doRetriveByCategoria("Battle Royale");
					for(ParteDiBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getVideogioco()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else if(per.equals("sport"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				ParteDiModelDAO dao = new ParteDiModelDAO(ds);
				try {
					ArrayList<ParteDiBean> bean = dao.doRetriveByCategoria("Sport");
					for(ParteDiBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getVideogioco()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else if(per.equals("horror"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				ParteDiModelDAO dao = new ParteDiModelDAO(ds);
				try {
					ArrayList<ParteDiBean> bean = dao.doRetriveByCategoria("Survival horror");
					for(ParteDiBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getVideogioco()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else if(per.equals("console"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				ConsoleModelDAO dao = new ConsoleModelDAO(ds);
				try {
					ArrayList<ConsoleBean> bean = dao.doRetriveAll("");
					for(ConsoleBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getProdotto()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else if(per.equals("videogiochi"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				VideogiocoModelDAO dao = new VideogiocoModelDAO(ds);
				try {
					ArrayList<VideogiocoBean> bean = dao.doRetriveAll("");
					for(VideogiocoBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getProdotto()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else if(per.equals("abbonamenti"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				AbbonamentoModelDAO dao = new AbbonamentoModelDAO(ds);
				try {
					ArrayList<AbbonamentoBean> bean = dao.doRetriveAll("");
					for(AbbonamentoBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getProdotto()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else if(per.equals("dlc"))
			{
				ProdottoModelDAO dap = new  ProdottoModelDAO(ds);
				ArrayList<ProdottoBean> prod = new ArrayList<ProdottoBean>();
				DlcModelDAO dao = new DlcModelDAO(ds);
				try {
					ArrayList<DlcBean> bean = dao.doRetriveAll("");
					for(DlcBean b : bean)
					{
						prod.add(dap.doRetriveByKey(b.getProdotto()+""));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("prodotti",prod);
				request.setAttribute("visitato","");
				RequestDispatcher view = super.getServletContext().getRequestDispatcher(response.encodeURL("/paginaRicerca.jsp"));
				view.forward(request, response);
				return;
			}
			else
			{
				String url="servletindex";
				url= response.encodeURL(url);
				response.sendRedirect(url);
				return;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

}
