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
import javax.sql.DataSource;

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
				if(str.equals(cb.getPassword())) {
					System.out.println("Sei loggato");
				}
				else {
					String message="";
					request.setAttribute("message",message);
					RequestDispatcher view=super.getServletContext().getRequestDispatcher("/admin.jsp");
					view.forward(request, response);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
