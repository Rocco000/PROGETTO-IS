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
		System.out.println("ciao sei nella servlet json");
		
		BufferedReader body= request.getReader();
		StringBuffer jb = new StringBuffer();
		String campo="";
		
		if((campo=body.readLine())!=null){
			System.out.println("campo= "+campo);
			jb.append(campo);
			System.out.println("jb= "+jb);
			
			String prova= URLDecoder.decode(campo, "utf-8");
			System.out.println(prova);
			
		}
		/*BufferedReader body= request.getReader();//mi restituisce il body della richiesta
		String campo="";
		if((campo=body.readLine())!=null){//leggo un campo alla volta del body della richiesta
			System.out.println(campo);
			StringBuffer jb = new StringBuffer();
			jb.append(campo);
			
			JSONObject oggettoJson =  HTTP.toJSONObject(campo);//mi posso riferire all'oggetto json della request
			
			String conversionePass= oggettoJson.getString("passwordNuova");
			String conversioneIban= oggettoJson.getString("ibanNuovo");
			System.out.println(conversionePass+" "+conversioneIban);
		}*/

		
	}

}
