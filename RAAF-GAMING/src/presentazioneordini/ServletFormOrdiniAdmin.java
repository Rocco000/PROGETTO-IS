package presentazioneordini;

import java.io.IOException;
import java.sql.Date;
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

import acquisto.CorriereEspressoBean;
import acquisto.CorriereEspressoDAO;
import acquisto.OrdineBean;
import acquisto.OrdineDAO;
import acquisto.SpeditoBean;
import acquisto.SpeditoDAO;

/**
 * Servlet per mostrare gli ordini non spediti al gestore ordine
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
					
					OrdineDAO odao= new OrdineDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
					CorriereEspressoDAO sdao= new CorriereEspressoDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
					
					try {
						ArrayList<OrdineBean> ordiniNonSpediti= odao.getOrdiniNonConsegnati();
						ArrayList<CorriereEspressoBean> corrieri= sdao.allElements("");
						
						request.setAttribute("visitato", "");
						request.setAttribute("ordiniNonSpediti", ordiniNonSpediti);
						request.setAttribute("corrieri", corrieri);
						
						String url="/paginaGestioneOrdini.jsp";
						url= response.encodeURL(url);
						RequestDispatcher dispatcher= request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
						return;
						
					} catch (SQLException e) {
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
