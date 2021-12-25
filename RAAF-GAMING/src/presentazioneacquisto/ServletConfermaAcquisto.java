package presentazioneacquisto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import acquisto.OrdineBean;
import acquisto.OrdineDAO;
import acquisto.RiguardaBean;
import acquisto.RiguardaDAO;
import magazzino.PresenteInBean;
import magazzino.PresenteInDAO;
import profilo.CartaFedeltaBean;
import profilo.CartaFedeltaDAO;
import profilo.ClienteBean;
import profilo.ClienteDAO;


@WebServlet("/servletconfermaacquisto")
public class ServletConfermaAcquisto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(true);
		synchronized(session)
		{
			Object obj = session.getAttribute("log");
			if(obj==null)
			{
				String url="servletloginfirst";
				url=response.encodeURL(url);
				response.sendRedirect(url);
				return;
			}
			
			else
			{
				boolean log = (Boolean) obj;
				
				if(log==false)
				{
					String url="servletloginfirst";
					url=response.encodeURL(url);
					response.sendRedirect(url);
					return;
				}
				
				else
				{
					ArrayList<String> carrello = (ArrayList<String>) session.getAttribute("carrello");
					
					if(carrello == null)
					{
						String url="servletcarrello";
						url=response.encodeURL(url);
						response.sendRedirect(url);
						return;
					}
					
					else if(carrello.size()==0)
					{
						String url="servletcarrello";
						url=response.encodeURL(url);
						response.sendRedirect(url);
						return;
					}
					
					else
					{
						DataSource ds = (DataSource)super.getServletContext().getAttribute("DataSource");
						OrdineDAO cf=new OrdineDAO(ds);
						ArrayList<OrdineBean> ordini = null;
						try {
							ordini = cf.doRetriveAll("");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						OrdineBean ordine = new OrdineBean();
						Random ran= new Random();
						int i=0;
						String nuovoCodice = null;
						String codice = null;
						while(i<ordini.size())
						{
							if(i==0)
							{
								codice = ""+ran.nextInt(999999999);
								nuovoCodice=codice;
							}
							if(codice.equals(ordini.get(i).getCodice()))
								i=0;
							else
								i++;
						}
						
						ordine.setCodice(nuovoCodice);
						ordine.setCliente((String)session.getAttribute("emailSession"));
						java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
						ordine.setData_acquisto(date);
						ClienteDAO clien = new ClienteDAO(ds);
						ClienteBean cliente = null;
						try {
							cliente = clien.doRetriveByKey((String)session.getAttribute("emailSession"));
						} catch (SQLException e) {
							e.printStackTrace();
						}
						ordine.setMetodo_di_pagamento(cliente.getIban());
						ordine.setIndirizzo_di_consegna(request.getParameter("indirizzodiconsegna"));
						ordine.setPrezzo_totale(Double.parseDouble(request.getParameter("prezzototale")));
						try {
							cf.doSave(ordine);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
						RiguardaDAO rig = new RiguardaDAO(ds);
						
						for(String id : carrello)
						{
							RiguardaBean riguarda = new RiguardaBean();
							riguarda.setProdotto(Integer.parseInt(id));
							riguarda.setOrdine(ordine.getCodice());
							riguarda.setQuantita_acquistata(1);
							try {
								rig.doSave(riguarda);
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							
							PresenteInDAO presente = new PresenteInDAO(ds);
							ArrayList<PresenteInBean> present = null;
							try {
								present = presente.doRetriveByProdotto(id);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							for(PresenteInBean b : present)
							{
								if(b.getQuantita_disponibile()>0)
								{
									try {
										presente.doUpdate(b);
									} catch (SQLException e) {
										e.printStackTrace();
									}
									break;
								}
							}
						}
						
						CartaFedeltaDAO cart = new CartaFedeltaDAO(ds);
						try {
							CartaFedeltaBean cartaf = cart.doRetriveByKey(cliente.getCarta_fedelta());
							cart.doUpdate(cartaf);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						session.setAttribute("carrello",null);
						request.setAttribute("confermato","Acquisto Confermato");
						String url="/servletcarrello";
						url=response.encodeURL(url);
						RequestDispatcher dispatcher= super.getServletContext().getRequestDispatcher(url);
						dispatcher.forward(request, response);
						return;
					}
				}
			}
		}
	}

}
