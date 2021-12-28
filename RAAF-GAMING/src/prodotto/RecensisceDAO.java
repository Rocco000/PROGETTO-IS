package prodotto;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;





public class RecensisceDAO{

	DataSource ds;
	
	public RecensisceDAO(DataSource d) {
		ds=d;
	}
	
	public RecensisceBean ricercaPerChiave(String cliente,String prodotto) throws SQLException {
		if((prodotto==null || prodotto=="")&&(cliente==null || cliente==""))throw new NullPointerException("prodotto o cliente è vuoto o null");
		Connection connessione = ds.getConnection();
		String Query="SELECT * FROM console WHERE prodotto=? and cliente=?;";
		
		PreparedStatement ps= connessione.prepareStatement(Query);
		ps.setString(1,prodotto);
		ps.setString(2,cliente);
		ResultSet rs= ps.executeQuery();		
		while(rs.next())
		{
		RecensisceBean app= new RecensisceBean();
		app.setCliente(rs.getString("cliente"));
		app.setProdotto(rs.getInt("prodotto"));
		app.setVoto(rs.getInt("voto"));
		app.setCommento(rs.getString("commento"));
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
	



	public ArrayList<RecensisceBean> allElements(String ordinamento) throws SQLException {
	
		Connection connessione=ds.getConnection();
		String query="SELECT * FROM recensisce ORDER BY ?;";
		PreparedStatement ps=connessione.prepareStatement(query);
		if(ordinamento!=null && !ordinamento.equals("")) 
			ps.setString(1, ordinamento);
		else {
			ps.setString(1,"recensisce.voto asc");}
		ArrayList<RecensisceBean >a=new ArrayList<RecensisceBean>();
		ResultSet risultato=ps.executeQuery();
		while(risultato.next()) {
			RecensisceBean app=new RecensisceBean();
			
			app.setCliente(risultato.getString("cliente"));
			app.setProdotto(risultato.getInt("prodotto"));
			app.setVoto(risultato.getInt("voto"));
			app.setCommento(risultato.getString("commento"));
			a.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
		
	}

	
	public void newInsert(RecensisceBean item) throws SQLException {
		if(item==null) throw new NullPointerException("item è null");
		Connection connessione = ds.getConnection();
		
		String query="INSERT INTO recensisce VALUES(?,?,?,?);";
		PreparedStatement ps= connessione.prepareStatement(query);
		
		ps.setString(1,item.getCliente());
		ps.setInt(2,item.getProdotto());
		ps.setInt(3,item.getVoto());
		ps.setString(4,item.getCommento());
		
		ps.executeUpdate();
		ps.close();
		connessione.close();
		
	}


	
	public ArrayList<RecensisceBean> getRecensioniCriterio(String condizione,String ordinamento) throws SQLException{
		if((condizione==null || condizione=="")&&(ordinamento==null || ordinamento=="")) throw new NullPointerException("ordimanento e condizione sono null o vuoti");
		Connection connessione= ds.getConnection();
		String query="SELECT recensisce.cliente,recensisce.prodotto,recensisce.voto,recensisce.commento FROM recensisce WHERE ? ORDER BY ?;";
		PreparedStatement ps=connessione.prepareStatement(query);
		
		ps.setString(1, condizione);
		ps.setString(2, ordinamento);
		ResultSet risultato= ps.executeQuery();
		ArrayList<RecensisceBean> recensioni= new ArrayList<RecensisceBean>();
		while(risultato.next()) {
			RecensisceBean app= new RecensisceBean();
			app.setCliente(risultato.getString("recensisce.cliente"));
			app.setProdotto(risultato.getInt("recensisce.prodotto"));
			app.setVoto(risultato.getInt("recensisce.voto"));
			app.setCommento(risultato.getString("recensice.commento"));
			recensioni.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		return recensioni;
	}

}
