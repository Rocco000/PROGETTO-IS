package it.unisa.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;




/**
 * Servlet implementation class ServletProfilo
 */
@WebServlet("/servletprofilo")
public class ServletProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletProfilo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		BufferedReader body= request.getReader();
		StringBuffer jb = new StringBuffer();
		String campo="";
		
		while((campo=body.readLine())!=null){
			jb.append(campo);
		}
		String encode = jb.toString();
		System.out.println(encode);
		String decode = URLDecoder.decode(encode,"UTF-8");
		System.out.println(decode);
		
	}

}
