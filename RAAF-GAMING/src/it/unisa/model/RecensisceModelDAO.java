package it.unisa.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;



public class RecensisceModelDAO implements OperazioniModel<RecensisceBean>{

	DataSource ds;
	
	public RecensisceModelDAO(DataSource d) {
		ds=d;
	}
	
	public RecensisceBean doRetriveByKey(String code) throws SQLException {
		
		return null;
	}


	public ArrayList<RecensisceBean> doRetriveAll(String order) throws SQLException {
	
		Connection connessione=ds.getConnection();
		String query="SELECT * FROM recensisce WHERE recensisce.prodotto=prodotto.codice_prodotto AND recensisce.cliente=cliente.email ORDER BY ?;";
		PreparedStatement ps=connessione.prepareStatement(query);
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1,"recensisce.voto asc");
		ArrayList<RecensisceBean >a=new ArrayList<RecensisceBean>();
		ResultSet risultato=ps.executeQuery();
		while(risultato.next()) {
			RecensisceBean app=new RecensisceBean();
			
			app.setCliente(risultato.getString("recensisce.cliente"));
			app.setProdotto(risultato.getInt("recensice.prodotto"));
			app.setVoto(risultato.getInt("recensice.voto"));
			a.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
		
	}

	
	public void doSave(RecensisceBean item) throws SQLException {
	
		
	}


	public void doUpdate(RecensisceBean item) throws SQLException {
		
	}


	public void doDelete(RecensisceBean item) throws SQLException {

		
	}

}
