package it.unisa.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletFormProdEsistentiAdmin
 */
@WebServlet("/servletformprodesistentiadmin")
public class ServletFormProdEsistentiAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletFormProdEsistentiAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession(true);
		synchronized(sessione) {
			Object logAdminB= sessione.getAttribute("logAdmin");
			if(logAdminB!=null) {
				//l'admin potrebbe essere loggato
				
				boolean logAdmin= (Boolean) logAdminB;
				if(logAdmin==true) {
					//l'admin e' loggato e può eseguire il form
					
					
					
					
				}
				else{
					//l'admin non e' loggato
					String url="servletaccessoadmin";
					url= response.encodeURL(url);
					response.sendRedirect(url);
					return;
				}
			}
			else {
				//l'admin non e' loggato
				String url="servletaccessoadmin";
				url= response.encodeURL(url);
				response.sendRedirect(url);
				return;
			}
		}
	}

}
