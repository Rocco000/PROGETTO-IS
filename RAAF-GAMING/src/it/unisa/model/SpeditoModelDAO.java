package it.unisa.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class SpeditoModelDAO implements OperazioniModel<SpeditoBean> {

	DataSource ds;
	public SpeditoModelDAO(DataSource d) {
		ds=d;
	}
	public SpeditoBean doRetriveByKey(String code) throws SQLException {
		Connection connessione = ds.getConnection();
		String query="SELECT * FROM spedito WHERE ordine=?;";
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setString(1, code);
		
		ResultSet risultato= ps.executeQuery();
		SpeditoBean app= null;
		while(risultato.next()) {
			app= new SpeditoBean();
			app.setOrdine(risultato.getString("ordine"));
			app.setData_consegna(risultato.getDate("data_consegna"));
			app.setCorriere_esprersso(risultato.getString("corriere_espresso"));
		}
		risultato.close();
		ps.close();
		connessione.close();
		return app;
	}


	public ArrayList<SpeditoBean> doRetriveAll(String order) throws SQLException {
		
		Connection connesione=ds.getConnection();
		String query="SELECT * FROM spedito WHERE spedito.ordine=ordine.codice AND spedito.corriere_espresso=corriereespresso.nome ORDER BY ?;";
		
		PreparedStatement ps=connesione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1,order);
		else
			ps.setString(1,"spedito.ordine asc");
		
		ArrayList<SpeditoBean> a=new ArrayList<SpeditoBean>();
		ResultSet risultato=ps.executeQuery();
		while(risultato.next()) {
			SpeditoBean app=new SpeditoBean();
			app.setOrdine(risultato.getString("spedito.ordine"));
			app.setCorriere_esprersso(risultato.getString("spedito.corriere_espresso"));
			app.setData_consegna(risultato.getDate("spedito.data_consegna"));
			a.add(app);
		}
		risultato.close();
		ps.close();
		connesione.close();
		
		return a;
	}


	public void doSave(SpeditoBean item) throws SQLException {
		Connection connessione= ds.getConnection();
		String query="INSERT INTO spedito VALUES(?,?,?);";
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setString(1, item.getOrdine());
		ps.setString(2, item.getCorriere_espresso());
		ps.setDate(3, (Date) item.getData_consegna());
		
		ps.executeUpdate();
		return;
	}

	
	public void doUpdate(SpeditoBean item) throws SQLException {
	
		
	}

	
	public void doDelete(SpeditoBean item) throws SQLException {
		
		
	}

}
