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

import it.unisa.model.OrdineBean;
import it.unisa.model.OrdineModelDAO;
import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModelDAO;

/**
 * Servlet implementation class ServletGestioneAdmin
 */
@WebServlet("/servletgestioneadmin")
public class ServletGestioneAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletGestioneAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione= request.getSession(true);
		synchronized(sessione) {
			Object logAdminB= sessione.getAttribute("logAdmin");
			if(logAdminB!=null) {
				//l'admin potrebbe essere loggato
				
				boolean logAdmin= (Boolean)logAdminB;
				if(logAdmin==true) {
					//l'admin e' loggato e può andare alla pagine di gestione(paginaAmministratore)
					
					OrdineModelDAO odao= new OrdineModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
					try {
						request.setAttribute("visitato", "");
						
						//ottengo tutti gli ordini non consegnati
						ArrayList<OrdineBean> ordiniNonConsegnati= odao.getOrdiniNonConsegnati();
						request.setAttribute("nonConsegnati", ordiniNonConsegnati);
						
						//ottengo tutti i prodotti esistenti
						ProdottoModelDAO pdao= new ProdottoModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
						ArrayList<ProdottoBean> prodottiEsistenti= pdao.doRetriveAll(null);
						request.setAttribute("prodottiEsistenti", prodottiEsistenti);
						
						String url="/paginaAmministratore.jsp";
						url= response.encodeURL(url);
						RequestDispatcher dispatcher= request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
						return;
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					//l'admin non e' loggato
					String url="admin.jsp";
					url= response.encodeURL(url);
					response.sendRedirect(url);
					return;
				}
			}
			else {
				//l'admin non e' loggato
				String url="admin.jsp";
				url= response.encodeURL(url);
				response.sendRedirect(url);
				return;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
