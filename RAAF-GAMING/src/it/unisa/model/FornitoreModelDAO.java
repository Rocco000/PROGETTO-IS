package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class FornitoreModelDAO implements OperazioniModel<FornitoreBean> {

	DataSource ds =null;
	
	public  FornitoreModelDAO(DataSource d)
	{
		ds=d;
	}
	@Override
	public FornitoreBean doRetriveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FornitoreBean> doRetriveAll(String order) throws SQLException {
		Connection connessione = ds.getConnection();
		String Query="SELECT * FROM fornitore ODER BY ?";
		
		PreparedStatement ps= connessione.prepareStatement(Query);
		if(order!=null && order!="") 
		{
		ps.setString(1, order);
		}else { ps.setString(1, "nome asc");}
		
		ResultSet rs= ps.executeQuery();
		ArrayList<FornitoreBean> a= new ArrayList<FornitoreBean>();
		
		while(rs.next())
		{
		FornitoreBean app= new FornitoreBean();
		app.setNome(rs.getString("nome"));
		app.setIndirizzo(rs.getString("indirizzo"));
		app.setTelefono(rs.getString("telefono"));
		a.add(app);
		}
		rs.close();
		ps.close();
		connessione.close();
		return a;
	}

	@Override
	public void doSave(FornitoreBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(FornitoreBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(FornitoreBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
