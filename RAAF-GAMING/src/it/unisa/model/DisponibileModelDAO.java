package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class DisponibileModelDAO implements OperazioniModel<DisponibileBean> {
	
	DataSource ds =null;//la connessione al DB la ottengo dal Controller che se la va a prendere dal ServletContext

	
	public DisponibileModelDAO(DataSource d)
	{
		ds=d;
	}

	@Override
	public DisponibileBean doRetriveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DisponibileBean> doRetriveAll(String order) throws SQLException {
		Connection connessione = ds.getConnection();
		String Query="SELECT * FROM disponibile ODER BY ?";
		
		PreparedStatement ps= connessione.prepareStatement(Query);
		if(order!=null && order!="") 
		{
		ps.setString(1, order);
		}else { ps.setString(1, "videogioco asc");}
		
		ResultSet rs= ps.executeQuery();
		ArrayList<DisponibileBean> a= new ArrayList<DisponibileBean>();
		
		while(rs.next())
		{
			DisponibileBean app= new DisponibileBean();
		app.setVideogioco(rs.getInt("videogioco"));
		app.setConsole(rs.getInt("console"));
		a.add(app);
		}
		rs.close();
		ps.close();
		connessione.close();
		return a;
	}

	@Override
	public void doSave(DisponibileBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(DisponibileBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(DisponibileBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
