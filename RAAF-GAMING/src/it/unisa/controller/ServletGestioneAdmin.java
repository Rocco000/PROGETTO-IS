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

import it.unisa.model.FornitoreBean;
import it.unisa.model.FornitoreModelDAO;
import it.unisa.model.OrdineBean;
import it.unisa.model.OrdineModelDAO;
import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModelDAO;
import it.unisa.model.SoftwarehouseBean;
import it.unisa.model.SoftwarehouseModelDAO;

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
						
						//ottengo tutti i fornitori
						FornitoreModelDAO fdao= new FornitoreModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
						ArrayList<FornitoreBean> fornitori= fdao.doRetriveAll(null);
						request.setAttribute("fornitori", fornitori);
						
						//ottengo tutte le softwarehouse
						SoftwarehouseModelDAO sdao= new SoftwarehouseModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
						ArrayList<SoftwarehouseBean> sfh= sdao.doRetriveAll(null);
						request.setAttribute("softwarehouse", sfh);
						
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
