package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ConsoleModelDAO implements OperazioniModel<ConsoleBean> {
	
	DataSource ds =null;//la connessione al DB la ottengo dal Controller che se la va a prendere dal ServletContext

	
	public ConsoleModelDAO(DataSource d)
	{
		ds=d;
	}
	@Override
	public ConsoleBean doRetriveByKey(String code) throws SQLException {
		
		Connection connessione = ds.getConnection();
		String Query="SELECT * FROM console WHERE prodotto=?;";
		
		PreparedStatement ps= connessione.prepareStatement(Query);
		ps.setString(1,code);		
		ResultSet rs= ps.executeQuery();		
		while(rs.next())
		{
		ConsoleBean app= new ConsoleBean();
		app.setProdotto(rs.getInt("prodotto"));
		app.setSpecifica(rs.getString("specifica"));
		app.setColore(rs.getString("colore"));
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
	public ArrayList<ConsoleBean> doRetriveAll(String order) throws SQLException {
		
	Connection connessione = ds.getConnection();
	String Query="SELECT * FROM console ODER BY ?";
	
	PreparedStatement ps= connessione.prepareStatement(Query);
	if(order!=null && order!="") 
	{
	ps.setString(1, order);
	}else { ps.setString(1, "prodotto asc");}
	
	ResultSet rs= ps.executeQuery();
	ArrayList<ConsoleBean> a= new ArrayList<ConsoleBean>();
	
	while(rs.next())
	{
	ConsoleBean app= new ConsoleBean();
	app.setProdotto(rs.getInt("prodotto"));
	app.setSpecifica(rs.getString("specifica"));
	app.setColore(rs.getString("colore"));
	
	a.add(app);
	}
	rs.close();
	ps.close();
	connessione.close();
	return a;
	}//fine doRetriveAll

	@Override
	public void doSave(ConsoleBean item) throws SQLException {
		Connection con = ds.getConnection();
		String str ="INSERT INTO console VALUES(?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		
		ps.setInt(1,item.getProdotto());
		ps.setString(2,item.getSpecifica());
		ps.setString(3,item.getColore());
		
		ps.executeUpdate();
		ps.close();
		con.close();
		
		

	}

	@Override
	public void doUpdate(ConsoleBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(ConsoleBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
