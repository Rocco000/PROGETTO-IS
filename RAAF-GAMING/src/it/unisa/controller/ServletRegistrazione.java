package it.unisa.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import it.unisa.model.CartaFedeltaModelDAO;
import it.unisa.model.ClienteBean;
import it.unisa.model.ClienteModelDAO;
import it.unisa.model.CartaFedeltaBean;

@WebServlet("/ServletRegistrazione")
public class ServletRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
     
    public ServletRegistrazione() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione= request.getSession(true);//controllo se esiste gia una sessione se no la creo
		synchronized(sessione)
		{
		Object logB= sessione.getAttribute("log");
		if(logB!=null) {//se il campo log esiste
			
			Boolean log= (Boolean) logB;
			if(log==true) {// se l'utente è loggato non può registrarsi
				{
					String url="servletindex";
					url=response.encodeURL(url);
					response.sendRedirect(url);
					return;
				}

			}
			else {
				//ottengo i dati dal form
				
				String nome=request.getParameter("nome");
				String cognome=request.getParameter("cognome");
				String email=request.getParameter("email");
				String iban=request.getParameter("iban");
				String data=request.getParameter("data");
				if(nome==null || nome.length()==0 || cognome==null || cognome.length()==0 || email==null || email.length()==0 || iban==null ||iban.length()==0 || data==null || data.length()==0)
				{
					
					request.setAttribute("visitato", "");
					String url="/registrazione.jsp";
					url=response.encodeURL(url);
					RequestDispatcher di = request.getRequestDispatcher(url);
					di.forward(request, response);
					return;
					
				}
				
				
				java.sql.Date nuovaData = java.sql.Date.valueOf(data);
				
				String password=request.getParameter("password");
				ClienteModelDAO cmd=new ClienteModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
				try {
					ClienteBean cb=cmd.doRetriveByKey(email);
					if(cb!=null) { //il cliente e' gia registrato
						String message="Sei gia' iscritto al nostro sito!";
						request.setAttribute("message", message);
						
						request.setAttribute("visitato", "");
						
						String url = "/registrazione.jsp";
						url = response.encodeURL(url);
						RequestDispatcher dispatcher=super.getServletContext().getRequestDispatcher(url);//ritorno alla registrazione
						dispatcher.forward(request, response);
					}else {
						CartaFedeltaModelDAO cf=new CartaFedeltaModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
						ArrayList<CartaFedeltaBean>carte=cf.doRetriveAll("codice desc");//otteniamo tutte le carte fedelta in ordine decrescente
						CartaFedeltaBean carta = new CartaFedeltaBean();
						Random ran= new Random();
						int i=0;
						String nuovaCarta = null;
						String codice = null;
						while(i<carte.size())
						{
							if(i==0)
							{
								codice = ""+ran.nextInt(999999999);
								nuovaCarta=codice;
							}
							if(codice.equals(carte.get(i).getCodice()))
								i=0;
							else
								i++;
						}
						
						carta.setCodice(nuovaCarta);
						carta.setPunti(0);
						ClienteBean nuovoCliente=new ClienteBean(); //creiamo il nuovo utente
						nuovoCliente.setNome(nome);
						nuovoCliente.setCognome(cognome);
						nuovoCliente.setEmail(email);
						nuovoCliente.setIban(iban);
						nuovoCliente.setData_di_nascita(nuovaData);//da correggere
						nuovoCliente.setPassword(password);
						nuovoCliente.setCarta_fedelta(nuovaCarta);
						cmd.doSave(nuovoCliente,carta);
						String url="servletloginfirst";
						url=response.encodeURL(url);
						response.sendRedirect(url);
						return;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		else {//se non è loggato
			//ottengo i dati dal form
			String nome=request.getParameter("nome");
			String cognome=request.getParameter("cognome");
			String email=request.getParameter("email");
			String iban=request.getParameter("iban");
			String data=request.getParameter("data");
			if(nome==null || nome.length()==0 || cognome==null || cognome.length()==0 || email==null || email.length()==0 || iban==null ||iban.length()==0 || data==null || data.length()==0)
			{
				request.setAttribute("visitato", "");
				String url="/registrazione.jsp";
				url=response.encodeURL(url);
				RequestDispatcher di = request.getRequestDispatcher(url);
				di.forward(request, response);
				return;
			}
			
			java.sql.Date nuovaData = java.sql.Date.valueOf(data);
			
			String password=request.getParameter("password");
			ClienteModelDAO cmd=new ClienteModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
			try {
				ClienteBean cb=cmd.doRetriveByKey(email);
				
				if(cb!=null) { //il cliente e' gia registrato
					String message="Sei gia' iscritto al nostro sito!";
					request.setAttribute("message", message);
					String url = "/registrazione.jsp";
					url = response.encodeURL(url);
					RequestDispatcher dispatcher=super.getServletContext().getRequestDispatcher(url);//ritorno alla registrazione
					dispatcher.forward(request, response);
				}else {
					CartaFedeltaModelDAO cf=new CartaFedeltaModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
					ArrayList<CartaFedeltaBean>carte=cf.doRetriveAll("codice desc");//otteniamo tutte le carte fedelta in ordine decrescente
					CartaFedeltaBean carta = new CartaFedeltaBean();
					Random ran= new Random();
					int i=0;
					String nuovaCarta = null;
					String codice = null;
					while(i<carte.size())
					{
						if(i==0)
						{
							codice = ""+ran.nextInt(999999999);
							nuovaCarta=codice;
						}
						if(codice.equals(carte.get(i).getCodice()))
							i=0;
						else
							i++;
					}
					carta.setCodice(nuovaCarta);
					carta.setPunti(0);
					ClienteBean nuovoCliente=new ClienteBean(); //creiamo il nuovo utente
					nuovoCliente.setNome(nome);
					nuovoCliente.setCognome(cognome);
					nuovoCliente.setEmail(email);
					nuovoCliente.setIban(iban);
					nuovoCliente.setData_di_nascita(nuovaData);//da correggere
					nuovoCliente.setPassword(password);
					nuovoCliente.setCarta_fedelta(nuovaCarta);
					cmd.doSave(nuovoCliente,carta);
					String url="servletloginfirst";
					url=response.encodeURL(url);
					response.sendRedirect(url);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		}
	}
}
