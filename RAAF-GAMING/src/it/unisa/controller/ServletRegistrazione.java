package it.unisa.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
		HttpSession sessioneExists= request.getSession(false);//controllo se esiste gia  una sessione
		System.out.println(sessioneExists.getId());
		if(sessioneExists!=null) {
			RequestDispatcher dispatcher=super.getServletContext().getRequestDispatcher("/index.html");//ritorno alla index
			dispatcher.forward(request, response);
		}
		else {
			String nome=request.getParameter("nome");
			String cognome=request.getParameter("cognome");
			String email=request.getParameter("email");
			String iban=request.getParameter("Iban");
			String data=request.getParameter("data");
			SimpleDateFormat dataN=new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date nuovaData=dataN.parse(data);
				System.out.println(nuovaData);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			/*String password=request.getParameter("password");
			ClienteModelDAO cmd=new ClienteModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
			try {
				ClienteBean cb=cmd.doRetriveByKey(email);
				if(cb!=null) { //il cliente è gia registrato
					RequestDispatcher dispatcher=super.getServletContext().getRequestDispatcher("registrazione.jsp");//ritorno alla index
					dispatcher.forward(request, response);
				}else {
					CartaFedeltaModelDAO cf=new CartaFedeltaModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));
					ArrayList<CartaFedeltaBean>carte=cf.doRetriveAll("codice desc");//otteniamo tutte le carte fedelta in ordine decrescente
					CartaFedeltaBean maxcarta=carte.get(0);//carta con codice massimo
					String max=maxcarta.getCodice();
					int a=Integer.parseInt(max);
					a=a+1;
					String nuovaCarta=""+a;
					ClienteBean nuovoCliente=new ClienteBean(); //creiamo il nuovo utente
					nuovoCliente.setNome(nome);
					nuovoCliente.setCognome(cognome);
					nuovoCliente.setEmail(email);
					nuovoCliente.setIban(iban);
					nuovoCliente.setData_di_nascita(null);//da correggere
					nuovoCliente.setPassword(password);
					nuovoCliente.setCarta_fedelta(nuovaCarta);
					cmd.doSave(nuovoCliente);
					RequestDispatcher dispatcher=super.getServletContext().getRequestDispatcher("login.jsp");//ritorno alla pagina di login dopo l'iscrizione
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
				
				
			
			
		}
	}
}
