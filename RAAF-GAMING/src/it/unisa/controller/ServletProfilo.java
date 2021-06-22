package it.unisa.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.HTTP;
import org.json.JSONObject;

import it.unisa.model.ClienteBean;
import it.unisa.model.ClienteModelDAO;




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

		HttpSession sessione= request.getSession(true);//controllo se esiste gia una sessione se no la creo
		synchronized(sessione){
			Object logB= sessione.getAttribute("log");//controlle se c'è il campo di log che mi indica se l'utente è loggato
			if(logB!=null) {
				//l'utente potrebbe essere loggato
				Boolean log= (Boolean) logB;
				if(log==true) {
					//l'utente è loggato e può vedere la pagina profilo
					
					response.setContentType("application/json");//dico che il tipo di dato che restituisce la servlet è un json
					
					System.out.println("ciao sei nella servlet json");
					
					
					BufferedReader body= request.getReader();//ottengo un riferimento al body della request(lo vede come un file)
					StringBuffer jb = new StringBuffer();
					String campo="";
					
					while((campo=body.readLine())!=null){//leggo tutto ciò che sta nel body e lo salvo in jb
						jb.append(campo);
						
					}
					
					String app= jb.toString();
					
					String decodifica= URLDecoder.decode(app, "UTF-8");//decodifichiamo la richiesta
				
					JSONObject oggettoJson= new JSONObject(decodifica);//otteniamo l'oggetto json della richiesta
					System.out.println("richiesta in JSON: "+oggettoJson);
					
					ClienteModelDAO cdao= new ClienteModelDAO((DataSource)super.getServletContext().getAttribute("DataSource"));//otteniamo il dao del cliente per effettuare le operazioni sul db
					
					try {
						boolean flag= false;//indica se l'utente ha modificato la password
						
						ClienteBean utente= cdao.doRetriveByKey((String)sessione.getAttribute("emailSession"));//ottengo i dati dell'utente loggato che sta visitanto la pagina profilo
						
						String passwordAtt= utente.getPassword();//ottengo la password attuale nel db dell'utente
						
						String passwordNuova= oggettoJson.getString("passwordNuova");//ottengo la nuova password che l'utente vuole impostare
						String ibanAtt= utente.getIban();
						String ibanNuovo= oggettoJson.getString("ibanNuovo");
						System.out.println("password db e richiesta: "+passwordAtt+" "+passwordNuova);
						
						//creo l'utente con i dati nuovi da passare al DAO per aggiornare il cliente
						ClienteBean utenteAggiornato= new ClienteBean();
						utenteAggiornato.setEmail(utente.getEmail());
						utenteAggiornato.setNome(utente.getNome());
						utenteAggiornato.setCognome(utente.getCognome());
						utenteAggiornato.setCarta_fedelta(utente.getCarta_fedelta());
						utenteAggiornato.setData_di_nascita(utente.getData_di_nascita());
						
						if(passwordNuova!=null) {//se ha modificato la password
							
							if(!passwordAtt.equals(passwordNuova)) {//se la nuova password è diversa da quella nel db
								
								System.out.println("settato nel bean la nuova password");
								utenteAggiornato.setPassword(passwordNuova);//setto la nuova password
								flag=true;
							}
							else {
								//se la password è uguale mando un errore
								System.out.println("password uguali");
								response.setStatus(response.SC_FORBIDDEN);//indica che il server ha compreso la richiesta ma si è rifiutato di soddisfarla.
								response.sendError(response.SC_FORBIDDEN, "LA PASSWORD COINCIDE CON QUELLA GIA' IN USO");
								return;
							}
						}
						else {
							//se non ha modificato la password assegno quella attuale perchè nel dao aggiorno tutti e 2 i campi
							System.out.println("non ha inviato la password");
							passwordNuova=passwordAtt;
							utenteAggiornato.setPassword(passwordAtt);//setto la stessa password
						}
						
						if(ibanNuovo!=null) {//se ha modificato l'iban
							
							if(!ibanAtt.equals(ibanNuovo)) {//se il nuovo iban è diverso da quello del db
								System.out.println("settato nel bean il nuovo iban");
								utenteAggiornato.setIban(ibanNuovo);//setto il nuovo iban
							}
							else {
								//se l'iban è uguale mando un errore
								System.out.println("iban uguale");
								response.setStatus(response.SC_FORBIDDEN);//indica che il server ha compreso la richiesta ma si è rifiutato di soddisfarla.
								response.sendError(response.SC_FORBIDDEN, "L'IBAN COINCIDE CON QUELLA GIA' IN USO");
								return;
							}
						}
						else {
							//se non ha modificato l'iban setto quello attuale
							System.out.println("non ha messo l'iban");
							ibanNuovo=ibanAtt;
							utenteAggiornato.setIban(ibanAtt);//setto lo stesso IBAN
						}
						
						cdao.doUpdate(utenteAggiornato);//aggiorno i dati dell'utente
						System.out.println("aggiornato il db");
						
						utente= cdao.doRetriveByKey((String)sessione.getAttribute("emailSession"));//riottengo l'utente aggiornato
						sessione.setAttribute("passwordSession", utente.getPassword());//aggiorno il campo password della sessione
						System.out.println("dati aggiornati: "+utente.getPassword()+" "+utente.getIban());
						
						
						JSONObject rispostaJson= new JSONObject();
						rispostaJson.put("iban", utente.getIban());
						rispostaJson.put("password", flag);
						System.out.println(rispostaJson);
						
						PrintWriter out= response.getWriter();//posso scrivere sulla response
						out.print(rispostaJson.toString());
						System.out.println("scritto l'oggetto json nella risposta");
						
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else {
					//esiste il campo log ma è false, quindi non è loggato
					String url="servletindex";
					url=response.encodeURL(url);
					response.sendRedirect(url);//costringo l'utente a ridirezionarsi sulla home
				}
			}
			else {
				//se non è loggato lo porto alla home
				String url="servletindex";
				url=response.encodeURL(url);
				response.sendRedirect(url);//costringo l'utente a ridirezionarsi sulla home
			}
			
		}

		String encode = jb.toString();
		System.out.println(encode);
		String decode = URLDecoder.decode(encode,"UTF-8");
		System.out.println(decode);
		

	}

}
