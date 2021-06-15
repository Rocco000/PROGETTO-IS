package it.unisa.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.ClienteBean;
import it.unisa.model.ClienteModelDAO;


@WebServlet("/servletlogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletLogin() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession sessioneExists= request.getSession(false);//controllo se esiste già una sessione
		if(sessioneExists!=null) {
			RequestDispatcher dispatcher=super.getServletContext().getRequestDispatcher("index.jsp");//ritorno alla index
			dispatcher.forward(request, response);
		}		
		else {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			ClienteModelDAO cmd=new ClienteModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
			try {
				ClienteBean cb=cmd.doRetriveByKey(email);
				if(cb!=null) {
					MessageDigest md = MessageDigest.getInstance("MD5"); 
					byte[] digest = md.digest(password.getBytes()); 
					BigInteger big=new BigInteger(1,digest);
					String str=big.toString(16);
					while(str.length()<32) {
						str="0"+str;
					}
					if(str.equals(cb.getPassword())) {   //se la password e l'email coincidono nel DB
						System.out.println("Sei loggato");
						HttpSession sessione= request.getSession(true);//crea la sessione
						sessione.setAttribute("emailSession", email);
						sessione.setAttribute("passwordSession", str);
						boolean flag= true;
						request.setAttribute("omino", flag);
						String url="index.jsp";
						url=response.encodeURL(url);
						RequestDispatcher dispatcher=super.getServletContext().getRequestDispatcher(url);
						dispatcher.forward(request, response);
					}
					else {
						String message="";
						request.setAttribute("message",message);
						RequestDispatcher view=super.getServletContext().getRequestDispatcher("/login.jsp");
						view.forward(request, response);
					}
				}
				else {
					String message="";
					request.setAttribute("message",message);
					RequestDispatcher view=super.getServletContext().getRequestDispatcher("/login.jsp");
					view.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
