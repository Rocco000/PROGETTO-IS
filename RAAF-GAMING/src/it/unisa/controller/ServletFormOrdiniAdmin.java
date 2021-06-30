package it.unisa.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.SpeditoBean;
import it.unisa.model.SpeditoModelDAO;

/**
 * Servlet implementation class ServletFormAdmin
 */
@WebServlet("/servletformordiniadmin")
public class ServletFormOrdiniAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletFormOrdiniAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
					
					SpeditoModelDAO sdao= new SpeditoModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
					SpeditoBean spedizione= new SpeditoBean();
					String ordine= request.getParameter("numeroOrdine");
					String corriere= request.getParameter("corriere");
					String data= request.getParameter("consegnaO");
					java.sql.Date dataConsegna= java.sql.Date.valueOf(data);
					
					spedizione.setOrdine(ordine);
					spedizione.setCorriere_esprersso(corriere);
					spedizione.setData_consegna(dataConsegna);
					
					System.out.println(ordine+" "+corriere+" "+dataConsegna);
					System.out.println(spedizione.getData_consegna());
					try {
						sdao.doSave(spedizione);
						String url="/servletgestioneadmin";
						url= response.encodeURL(url);
						request.setAttribute("message", "Ordine spedito con successo!");
						RequestDispatcher dispatcher= request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
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
