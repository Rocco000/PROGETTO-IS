package it.unisa.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import it.unisa.model.AmministratoriBean;
import it.unisa.model.AmministratoriModelDAO;
import it.unisa.model.ClienteBean;
import it.unisa.model.ClienteModelDAO;


@WebServlet("/servletAdmin")
public class servletAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public servletAdmin() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione= request.getSession(true);
		synchronized(sessione) {
			Object logAmB= sessione.getAttribute("logAdmin");
			if(logAmB!=null) {
				//potrebbe essere gia' loggato
				boolean logAdmin= (Boolean) logAmB;
				if(logAdmin==true) {
					//l'admin e' gia' loggato e quindi non puo' utilizzare questa servlet
					
					String url="servletgestioneadmin";//url pagina fornitura prodotti
					url= response.encodeURL(url);
					response.sendRedirect(url);
					return;
				}
				else {
					//l'admin non e' loggato quindi posso controllare i campi del form
					
					String emailAm= request.getParameter("email");
					String passwordAm= request.getParameter("password");
					
					AmministratoriModelDAO adao= new AmministratoriModelDAO((DataSource) super.getServletContext().getAttribute("DataSource"));
					try {
						//cerco l'amministratore con quella email
						AmministratoriBean amministratore= adao.doRetriveByKey(emailAm);
						if(amministratore!=null) {
							//l'amministratore esiste nel DB controllo se la password corrisponde
							//cripto la password della request
							MessageDigest md = MessageDigest.getInstance("MD5"); 
							byte[] digest = md.digest(passwordAm.getBytes()); 
							BigInteger big=new BigInteger(1,digest);
							String str=big.toString(16);//contiene la password della request criptata in MD5
							while(str.length()<32) {
								str="0"+str;
							}
							
							if(str.equals(amministratore.getPassword())) {
								//se la password corrisponde lo mando alla pagina di fornitura prodotti
								sessione.setAttribute("emailAdmin", amministratore.getEmail());
								sessione.setAttribute("passwordAdmin", amministratore.getPassword());
								sessione.setAttribute("logAdmin", true);
								
								String url="servletgestioneadmin";//url pagina fornitura prodotti
								url= response.encodeURL(url);
								response.sendRedirect(url);
								return;
							}
							else {
								//se la password non corrisponde
								request.setAttribute("message", "");
								String url="/admin.jsp";
								url= response.encodeURL(url);
								RequestDispatcher dispatcher= request.getRequestDispatcher(url);
								dispatcher.forward(request, response);
								return;
							}
						}
						else {
							//l'amministratore non esiste nel db
							
							request.setAttribute("message", "");
							String url="/admin.jsp";
							url= response.encodeURL(url);
							RequestDispatcher dispatcher= request.getRequestDispatcher(url);
							dispatcher.forward(request, response);
							return;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else {
				//l'admin non e' loggato posso controllare 
				
				String emailAm= request.getParameter("email");
				String passwordAm= request.getParameter("password");
				
				AmministratoriModelDAO adao= new AmministratoriModelDAO((DataSource) super.getServletContext().getAttribute("DataSource"));
				try {
					//cerco l'amministratore con quella email
					AmministratoriBean amministratore= adao.doRetriveByKey(emailAm);
					if(amministratore!=null) {
						//l'amministratore esiste nel DB controllo se la password corrisponde
						//cripto la password della request
						MessageDigest md = MessageDigest.getInstance("MD5"); 
						byte[] digest = md.digest(passwordAm.getBytes()); 
						BigInteger big=new BigInteger(1,digest);
						String str=big.toString(16);//contiene la password della request criptata in MD5
						while(str.length()<32) {
							str="0"+str;
						}
						
						if(str.equals(amministratore.getPassword())) {
							//se la password corrisponde lo mando alla pagina di fornitura prodotti
							sessione.setAttribute("emailAdmin", amministratore.getEmail());
							sessione.setAttribute("passwordAdmin", amministratore.getPassword());
							sessione.setAttribute("logAdmin", true);
							
							String url="servletgestioneadmin";//url pagina fornitura prodotti
							url= response.encodeURL(url);
							response.sendRedirect(url);
							return;
						}
						else {
							//se la password non corrisponde
							request.setAttribute("message", "");
							String url="/admin.jsp";
							url= response.encodeURL(url);
							RequestDispatcher dispatcher= request.getRequestDispatcher(url);
							dispatcher.forward(request, response);
							return;
						}
					}
					else {
						//l'amministratore non esiste nel db
						
						request.setAttribute("message", "");
						String url="/admin.jsp";
						url= response.encodeURL(url);
						RequestDispatcher dispatcher= request.getRequestDispatcher(url);
						dispatcher.forward(request, response);
						return;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}

}
