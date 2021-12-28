package presentazioneprodotto;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import magazzino.MagazzinoBean;
import magazzino.MagazzinoDAO;
import magazzino.PresenteInBean;
import magazzino.PresenteInDAO;
import prodotto.ProdottoBean;
import prodotto.ProdottoDAO;

@WebServlet("/servletformprodesistentiadmin")
public class ServletFormProdEsistentiAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletFormProdEsistentiAdmin() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessione = request.getSession(true);
		synchronized(sessione) {
			Object logAdminB= sessione.getAttribute("logAdmin");
			if(logAdminB!=null) {
				//l'admin potrebbe essere loggato
				
				boolean logAdmin= (Boolean) logAdminB;
				if(logAdmin==true) {
				//l'admin e' loggato e puo eseguire il form
				String codiceP=request.getParameter("codicePesistente");
				String quantita=request.getParameter("quantitaPesistente");
				DataSource ds=(DataSource)super.getServletContext().getAttribute("DataSource");
				ProdottoDAO pdo=new ProdottoDAO(ds);
				try {
					Object obj=pdo.ricercaPerChiave(codiceP);
					if(obj==null) {
						request.setAttribute("message", "Prodotto non esistente");
						String url="/servletgestioneadmin";
						RequestDispatcher view=super.getServletContext().getRequestDispatcher(response.encodeURL(url));
						view.forward(request, response);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				MagazzinoDAO mmd=new MagazzinoDAO(ds);
				ArrayList<MagazzinoBean> mb=null;
				try {
					mb=mmd.allElements("");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				PresenteInDAO pid=new PresenteInDAO(ds);
				for(int i=0;i<mb.size();i++) {
				ArrayList<PresenteInBean> pb=null;
				try {
					 pb=pid.ricercaPerMagazzino(mb.get(i).getIndirizzo());
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				int cont=0;
					for(PresenteInBean pib:pb) {
						cont+=pib.getQuantita_disponibile();
					}
				if((cont+Integer.parseInt(quantita))<=mb.get(i).getCapienza()) {
					for(PresenteInBean pib:pb) {
						if(pib.getProdotto()==Integer.parseInt(codiceP)) {
							
							pib.setQuantita_disponibile(pib.getQuantita_disponibile()+Integer.parseInt(quantita));
							
							ProdottoDAO prod=new ProdottoDAO(ds);
							ProdottoBean prodB=null;
							try {
								prodB=prod.ricercaPerChiave(codiceP);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							prodB.setQuantita_fornitura(Integer.parseInt(quantita));
							Date date = new Date(Calendar.getInstance().getTime().getTime());
							prodB.setUltima_fornitura(date);
							try {
								prod.doUpdate(prodB);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							try {
								pid.rifornitura(pib);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							request.setAttribute("messageok", "Prodotto Rifornito con Successo");
							String url="/servletgestioneadmin";
							RequestDispatcher view=super.getServletContext().getRequestDispatcher(response.encodeURL(url));
							view.forward(request, response);
							return;
						}
					}
				}	
				}
				request.setAttribute("message", "Capienza non disponibile");
				String url="/servletgestioneadmin";
				RequestDispatcher view=super.getServletContext().getRequestDispatcher(response.encodeURL(url));
				view.forward(request, response);
				return;
				}
				else{
					//l'admin non e' loggato
					String url="servletaccessoadmin";
					url= response.encodeURL(url);
					response.sendRedirect(url);
					return;
				}
			}
			else {
				//l'admin non e' loggato
				String url="servletaccessoadmin";
				url= response.encodeURL(url);
				response.sendRedirect(url);
				return;
			}
		}
	}

}
