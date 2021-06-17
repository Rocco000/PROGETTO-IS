package it.unisa.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoModelDAO;

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
		response.setContentType("image/*");
		DataSource ds = (DataSource) super.getServletContext().getAttribute("DataSource");
		try {
			ProdottoModelDAO dao = new ProdottoModelDAO(ds);
			ProdottoBean bean = dao.doRetriveByKey(request.getParameter("id"));
			OutputStream out = response.getOutputStream();
			out.write(bean.getCopertina());
			out.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
