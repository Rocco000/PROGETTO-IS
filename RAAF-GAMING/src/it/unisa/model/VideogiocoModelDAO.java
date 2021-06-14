package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class VideogiocoModelDAO implements OperazioniModel<VideogiocoBean> {

	DataSource ds;
	public VideogiocoModelDAO(DataSource d) {
		ds=d;
	}
	public VideogiocoBean doRetriveByKey(String code) throws SQLException {
		
		return null;
	}

	
	public ArrayList<VideogiocoBean> doRetriveAll(String order) throws SQLException {
		Connection connessione=ds.getConnection();
		String query="SELECT * FROM videogioco WHERE videogioco.prodotto=prodotto.codice_prodotto ORDER BY ?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1,"videogioco.prodotto");
		ArrayList<VideogiocoBean>a=new ArrayList<VideogiocoBean>();
		ResultSet risultato=ps.executeQuery();
		while(risultato.next()) {
			VideogiocoBean app=new VideogiocoBean();
			app.setDimensione(risultato.getDouble("videogioco.dimensione"));
			app.setEdizione_limitata(risultato.getInt("videogioco.edizione_limitata"));
			app.setNcd(risultato.getInt("videogioco.ncd"));
			app.setPegi(risultato.getInt("videogioco.pegi"));
			app.setProdotto(risultato.getInt("videogioco.prodotto"));
			app.setSoftware_house(risultato.getString("videogioco.software_house"));
			app.setVkey(risultato.getString("videogioco.vkey"));
			a.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
			
		
		
	}

	
	public void doSave(VideogiocoBean item) throws SQLException {
		
		
	}

	
	public void doUpdate(VideogiocoBean item) throws SQLException {
	
		
	}

	
	public void doDelete(VideogiocoBean item) throws SQLException {
		
		
	}

}
