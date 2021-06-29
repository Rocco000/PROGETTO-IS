package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class DlcModelDAO implements OperazioniModel<DlcBean> {

	DataSource ds =null;//la connessione al DB la ottengo dal Controller che se la va a prendere dal ServletContext

	
	public  DlcModelDAO(DataSource d)
	{
		ds=d;
	}
	
	@Override
	public DlcBean doRetriveByKey(String code) throws SQLException {
		Connection connessione = ds.getConnection();
		String Query="SELECT * FROM dlc WHERE prodotto=?;";
		
		PreparedStatement ps= connessione.prepareStatement(Query);
		ps.setString(1,code);
		
		ResultSet rs= ps.executeQuery();
		
		while(rs.next())
		{
		DlcBean app= new DlcBean();
		app.setProdotto(rs.getInt("prodotto"));
		app.setDimensione(rs.getDouble("dimensione"));
		app.setDescrizione(rs.getString("descrizione"));
		rs.close();
		ps.close();
		connessione.close();
		return app;
		}
		rs.close();
		ps.close();
		connessione.close();
		return null;
	}

	@Override
	public ArrayList<DlcBean> doRetriveAll(String order) throws SQLException {
		Connection connessione = ds.getConnection();
		String Query="SELECT * FROM dlc ODER BY ?";
		
		PreparedStatement ps= connessione.prepareStatement(Query);
		if(order!=null && order!="") 
		{
		ps.setString(1, order);
		}else { ps.setString(1, "prodotto asc");}
		
		ResultSet rs= ps.executeQuery();
		ArrayList<DlcBean> a= new ArrayList<DlcBean>();
		
		while(rs.next())
		{
		DlcBean app= new DlcBean();
		app.setProdotto(rs.getInt("prodotto"));
		app.setDimensione(rs.getDouble("dimensione"));
		app.setDescrizione(rs.getString("descrizione"));
		a.add(app);
		}
		rs.close();
		ps.close();
		connessione.close();
		return a;
	}

	@Override
	public void doSave(DlcBean item) throws SQLException {
		Connection con = ds.getConnection();
		String str ="INSERT INTO dlc VALUES(?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		
		ps.setInt(1,item.getProdotto());
		ps.setDouble(2,item.getDimensione());
		ps.setString(3,item.getDescrizione());
		
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}

	@Override
	public void doUpdate(DlcBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(DlcBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
