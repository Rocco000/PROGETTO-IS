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
import it.unisa.model.PresenteInBean;
import it.unisa.model.PresenteInModelDAO;
import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModelDAO;
import it.unisa.model.VideogiocoBean;
import it.unisa.model.VideogiocoModelDAO;


@WebServlet("/servletprodotto")
public class ServletProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletProdotto() {
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
		
		String id = request.getParameter("id");
		if(id==null || id.equals(""))
		{
			String message="PRODOTTO NON ESISTENTE!";
			request.setAttribute("message", message);
			request.setAttribute("visitato", "");
			String url="/paginaGioco.jsp";
			url= response.encodeURL(url);
			RequestDispatcher dispatcher= request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			return;
		}
		else {
			DataSource ds = (DataSource)super.getServletContext().getAttribute("DataSource");
			ProdottoModelDAO dao = new ProdottoModelDAO(ds);
			try {
	
				ProdottoBean prod = dao.doRetriveByKey(id);
				
				if(prod==null)//prodotto non esiste nel db
				{
					String message="PRODOTTO NON ESISTENTE!";
					request.setAttribute("message", message);
					request.setAttribute("visitato", "");
					String url="/paginaGioco.jsp";
					url= response.encodeURL(url);
					RequestDispatcher dispatcher= request.getRequestDispatcher(url);
					dispatcher.forward(request, response);
					return;
				}
				else {//esiste il prodotto nel db e vedo se c'è in magazzino
					
					PresenteInModelDAO present = new PresenteInModelDAO(ds);
					ArrayList<PresenteInBean> presente = present.doRetriveByProdotto(id);
		
					request.setAttribute("presente",null);
					for(PresenteInBean l : presente)
					{
						if(l.getQuantita_disponibile()>0)
						{
							request.setAttribute("presente","");
							break;
						}
					}
					
					VideogiocoModelDAO vdao = new VideogiocoModelDAO(ds);
					VideogiocoBean video = vdao.doRetriveByKey(""+prod.getCodice_prodotto());
					if(video!=null)
					{
						request.setAttribute("tipo","videogioco");
						request.setAttribute("videogioco",video);
						request.setAttribute("Prodotto",prod);
						request.setAttribute("visitato","");
						String url="/paginaGioco.jsp";
						url= response.encodeURL(url);
						request.getRequestDispatcher(url).forward(request, response);
						return;
					}
					else
					{
						ConsoleModelDAO cons = new ConsoleModelDAO(ds);
						ConsoleBean console = cons.doRetriveByKey(""+prod.getCodice_prodotto());
						if(console!=null)
						{
							request.setAttribute("tipo","console");
							request.setAttribute("console",console);
							request.setAttribute("Prodotto",prod);
							request.setAttribute("visitato","");
							String url="/paginaGioco.jsp";
							url= response.encodeURL(url);
							request.getRequestDispatcher(url).forward(request, response);
							return;
						}
						else
						{
							AbbonamentoModelDAO abb = new AbbonamentoModelDAO(ds);
							AbbonamentoBean abbo = abb.doRetriveByKey(""+prod.getCodice_prodotto());
							if(abbo!=null)
							{
								request.setAttribute("tipo","abbonamento");
								request.setAttribute("abbonamento",abbo);
								request.setAttribute("Prodotto",prod);
								request.setAttribute("visitato","");
								String url="/paginaGioco.jsp";
								url= response.encodeURL(url);
								request.getRequestDispatcher(url).forward(request, response);
								return;
							}
							else
							{
								DlcModelDAO dl = new DlcModelDAO(ds);
								DlcBean dlc = dl.doRetriveByKey(""+prod.getCodice_prodotto());
								if(dlc!=null)
								{
									request.setAttribute("tipo","dlc");
									request.setAttribute("dlc",dlc);
									request.setAttribute("Prodotto",prod);
									request.setAttribute("visitato","");
									String url="/paginaGioco.jsp";
									url= response.encodeURL(url);
									request.getRequestDispatcher(url).forward(request, response);
									return;
								}
								else
								{
									String message="PRODOTTO NON ESISTENTE!";
									request.setAttribute("message", message);
									request.setAttribute("visitato", "");
									String url="/paginaGioco.jsp";
									url= response.encodeURL(url);
									RequestDispatcher dispatcher= request.getRequestDispatcher(url);
									dispatcher.forward(request, response);
									return;
								}
							}
						}
					}
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
