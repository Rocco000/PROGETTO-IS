package it.unisa.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ClienteModelDAO implements OperazioniModel<ClienteBean> {

	DataSource ds= null;//la connessione al DB la ottengo dal Controller che se la va a prendere dal ServletContext
	
	public ClienteModelDAO(DataSource d) {
		ds=d;
	}
	
	@Override
	public ClienteBean doRetriveByKey(String code) throws SQLException {
		Connection connessione= ds.getConnection();
		String query= "SELECT * FROM cliente WHERE email=?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setString(1, code);
		ResultSet risultato= ps.executeQuery();
		if(risultato.next()) {
			ClienteBean app= new ClienteBean();
			app.setEmail(risultato.getString("email"));
			app.setNome(risultato.getString("nome"));
			app.setCognome(risultato.getString("cognome"));
			app.setPassword(risultato.getString("password"));
			app.setData_di_nascita(risultato.getDate("data_di_nascita"));
			app.setIban(risultato.getString("iban"));
			app.setCarta_fedelta(risultato.getString("carta_fedelta"));
			
			risultato.close();
			ps.close();
			connessione.close();
			return app;
		}
		else {
			risultato.close();
			ps.close();
			connessione.close();
			return null;
		}
	}

	@Override
	public ArrayList<ClienteBean> doRetriveAll(String order) throws SQLException {
		
		Connection connessione= ds.getConnection();//ottengo la connessione al DB
		
		String query="SELECT * FROM cliente ORDER BY ?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		if(order!=null && !order.equals("")) {
			ps.setString(1, order);
		}
		else {
			ps.setString(1, "email asc");
		}
		
		ResultSet risultato= ps.executeQuery();//eseguo la query
		ArrayList<ClienteBean> a= new ArrayList<ClienteBean>();//mi salvo tutte le righe dei clienti che ho ottenuto dalla query
		
		while(risultato.next()) {
		
			ClienteBean app= new ClienteBean();//creo un ClienteBean d'appoggio per salvarmi i campi della riga man mano
			app.setEmail(risultato.getString("email"));
			app.setNome(risultato.getString("nome"));
			app.setCognome(risultato.getString("cognome"));
			app.setPassword(risultato.getString("password"));
			app.setData_di_nascita(risultato.getDate("data_di_nascita"));
			app.setIban(risultato.getString("iban"));
			app.setCarta_fedelta(risultato.getString("carta_fedelta"));
			
			a.add(app);//aggiungo il bean all'arraylist
		}
		risultato.close();
		ps.close();
		connessione.close();
		return a;
	}

	@Override
	public void doSave(ClienteBean item) throws SQLException {
		Connection connessione = ds.getConnection();//ottengo la connessione
		
		String query="INSERT INTO cliente VALUES(?,?,?,?,?,?,?);";
		PreparedStatement ps= connessione.prepareStatement(query);
		
		ps.setString(1, item.getEmail());
		ps.setString(2, item.getNome());
		ps.setString(3, item.getCognome());
		ps.setDate(4, (Date) item.getData_di_nascita());
		ps.setString(5, item.getPassword());
		ps.setString(6, item.getIban());
		ps.setString(7, item.getCarta_fedelta());
		
		CartaFedeltaBean carta= new CartaFedeltaBean();	//creao la carta fedeltà del nuovo cliente
		carta.setCodice(item.getCarta_fedelta());
		carta.setPunti(0);
		CartaFedeltaModelDAO cartaDAO= new CartaFedeltaModelDAO(this.ds);//eseguo la insert della carta
		cartaDAO.doSave(carta);
		
		ps.executeUpdate();//eseguo la insert del cliente
		ps.close();
		connessione.close();
		
	}

	@Override
	public void doUpdate(ClienteBean item) throws SQLException {
		
		Connection connessione= ds.getConnection();//ottengo la connessione al DB
		
		String query= "SELECT * FROM cliente WHERE email=?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		
		ps.setString(1, item.getEmail());
		
		ResultSet risultato= ps.executeQuery();
		if(risultato.next()) {
			ClienteBean app= new ClienteBean();
			app.setEmail(risultato.getString("email"));
			app.setNome(risultato.getString("nome"));
			app.setCognome(risultato.getString("cognome"));
			app.setPassword(risultato.getString("password"));
			app.setData_di_nascita(risultato.getDate("data_di_nascita"));
			app.setIban(risultato.getString("iban"));
			app.setCarta_fedelta(risultato.getString("carta_fedelta"));
			
			if(!item.getIban().equals(app.getIban())) {//se l'iban non corrisponde con quello del db allora devo aggiornare l'IBAN
				
				String aggiornamento= "UPDATE cliente SET iban=? WHERE email=? ;";
				PreparedStatement ps2= connessione.prepareStatement(aggiornamento);
				
				ps2.setString(1, item.getIban());
				ps2.setString(2, item.getEmail());
				if(ps2.executeUpdate()==1)
					;
				else
					System.out.println("query errata");
				
				ps2.close();
			}
			else{ //se l'iban non è cambiato vuol dire che deve aggiornare la password
				
				String aggiornamento= "UPDATE cliente SET password=MD5(?) WHERE email=? ;";
				PreparedStatement ps2= connessione.prepareStatement(aggiornamento);
				
				
				ps2.setString(1, item.getPassword());
				ps2.setString(2, item.getEmail());
				if(ps2.execute())
					;
				else
					System.out.println("query errata");
				
				ps2.close();
			}
		}
		
		risultato.close();
		ps.close();
		connessione.close();
	}

	@Override
	public void doDelete(ClienteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}
