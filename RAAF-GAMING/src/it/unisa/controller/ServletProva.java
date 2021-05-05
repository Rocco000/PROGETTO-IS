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
import javax.sql.DataSource;

import it.unisa.model.ClienteBean;
import it.unisa.model.ClienteModelDAO;


@WebServlet("/servletprova")
public class ServletProva extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DataSource ds= (DataSource) getServletContext().getAttribute("DataSource");//prendo dal contesto il pool di connessioni al DB
		
		ClienteModelDAO modello= new ClienteModelDAO(ds);
		
		try {
			ArrayList<ClienteBean> a= modello.doRetriveAll("nome asc ,cognome asc");
			request.setAttribute("listaClienti", a);
			RequestDispatcher dispatcher= getServletContext().getRequestDispatcher("/Prova.jsp");//indico a chi dare il controllo
			dispatcher.forward(request, response);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
