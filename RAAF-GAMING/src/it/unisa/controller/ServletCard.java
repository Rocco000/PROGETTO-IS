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

import it.unisa.model.VideogiocoBean;
import it.unisa.model.VideogiocoModelDAO;

/**
 * Servlet implementation class ServletCard
 * Servlet per ottenere i videogiochi della home (card)
 */
@WebServlet("/servletcard")
public class ServletCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletCard() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VideogiocoModelDAO vdao= new VideogiocoModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
		try {
			VideogiocoBean migliorVideogioco= vdao.getTopRecensione();
			VideogiocoBean ultimoUscito= vdao.getUltimoUscito();
			ArrayList<VideogiocoBean> scontati= vdao.getVideogiochiScontati();
			
			
			
			request.setAttribute("migliorVideogioco", migliorVideogioco);
			request.setAttribute("ultimoUscito", ultimoUscito);
			request.setAttribute("scontati", scontati);
			RequestDispatcher dispatcher= super.getServletContext().getRequestDispatcher("lol.jsp");
			dispatcher.forward(request, response);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
