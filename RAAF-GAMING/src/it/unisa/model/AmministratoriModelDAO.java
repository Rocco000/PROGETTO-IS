package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.catalina.connector.Response;

public class AmministratoriModelDAO implements OperazioniModel<AmministratoriBean> {

	DataSource ds;
	
	public AmministratoriModelDAO(DataSource d) {
		ds=d;
	}
	
	@Override
	public AmministratoriBean doRetriveByKey(String code) throws SQLException {
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM amministratori WHERE email=?;";
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setString(1, code);
		
		ResultSet risultato= ps.executeQuery();
		AmministratoriBean app= null;
		while(risultato.next()) {
			app= new AmministratoriBean();
			app.setEmail(risultato.getString("email"));
			app.setNome(risultato.getString("nome"));
			app.setPassword(risultato.getString("password"));
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		return app;
	}

	@Override
	public ArrayList<AmministratoriBean> doRetriveAll(String order) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void doSave(AmministratoriBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(AmministratoriBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(AmministratoriBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
