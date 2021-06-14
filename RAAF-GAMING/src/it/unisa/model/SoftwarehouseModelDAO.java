package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class SoftwarehouseModelDAO implements OperazioniModel<SoftwarehouseBean> {
	DataSource ds;
	public SoftwarehouseModelDAO(DataSource d) {
		ds=d;
	}

	public SoftwarehouseBean doRetriveByKey(String code) throws SQLException {
		
		return null;
	}

	
	public ArrayList<SoftwarehouseBean> doRetriveAll(String order) throws SQLException {
		Connection connessione=ds.getConnection();
		String query="SELECT * FROM softwarehouse ORDER BY ?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1,"softwarehouse.nomesfh");
		ArrayList<SoftwarehouseBean> a=new ArrayList<SoftwarehouseBean>();
		ResultSet risultato=ps.executeQuery();
		while(risultato.next()) {
			SoftwarehouseBean app=new SoftwarehouseBean();
			app.setNomesfh(risultato.getString("softwarehouse.nomesfh"));
			app.setLogo(risultato.getBytes("softwarehouse.logo"));
			a.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
		
		
	}

	
	public void doSave(SoftwarehouseBean item) throws SQLException {
		
		
	}

	
	public void doUpdate(SoftwarehouseBean item) throws SQLException {
	
		
	}

	
	public void doDelete(SoftwarehouseBean item) throws SQLException {
		
		
	}

}
