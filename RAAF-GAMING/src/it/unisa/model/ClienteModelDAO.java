package it.unisa.model;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ClienteBean> doRetriveAll(String order) throws SQLException {
		
		Connection connessione= ds.getConnection();//ottengo la connessione al DB
		
		String query="SELECT * FROM cliente ORDER BY ?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		if(order!=null && order!="") {
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
			app.setCarta_fedelta(risultato.getString("carta_fedeltà"));
			
			a.add(app);//aggiungo il bean all'arraylist
		}
			
		return a;
	}

	@Override
	public void doSave(ClienteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(ClienteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(ClienteBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	
}
