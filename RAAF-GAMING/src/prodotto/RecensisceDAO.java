package prodotto;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.OperazioniModel;



public class RecensisceDAO implements OperazioniModel<RecensisceBean>{

	DataSource ds;
	
	public RecensisceDAO(DataSource d) {
		ds=d;
	}
	
	public RecensisceBean doRetriveByKey(String code) throws SQLException {
		return null;
	}
	
	public RecensisceBean doRetriveByKey(String prodotto, String cliente) throws SQLException {
		Connection connessione=ds.getConnection();
		String query="SELECT * FROM recensisce WHERE prodotto=? AND cliente=?;";
		PreparedStatement ps=connessione.prepareStatement(query);
		ps.setString(1,prodotto);
		ps.setString(2,cliente);
		ResultSet risultato=ps.executeQuery();
		if(risultato.next()) {
			RecensisceBean app=new RecensisceBean();
			app.setCliente(risultato.getString("cliente"));
			app.setProdotto(risultato.getInt("prodotto"));
			app.setVoto(risultato.getInt("voto"));
			risultato.close();
			ps.close();
			connessione.close();
			return app;
		}
		risultato.close();
		ps.close();
		connessione.close();
		return null;
	}
	

	public ArrayList<RecensisceBean> doRetriveAll(String order) throws SQLException {
	
		Connection connessione=ds.getConnection();
		String query="SELECT * FROM recensisce ORDER BY ?;";
		PreparedStatement ps=connessione.prepareStatement(query);
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1,"recensisce.voto asc");
		ArrayList<RecensisceBean >a=new ArrayList<RecensisceBean>();
		ResultSet risultato=ps.executeQuery();
		while(risultato.next()) {
			RecensisceBean app=new RecensisceBean();
			
			app.setCliente(risultato.getString("cliente"));
			app.setProdotto(risultato.getInt("prodotto"));
			app.setVoto(risultato.getInt("voto"));
			a.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
		
	}

	
	public void doSave(RecensisceBean item) throws SQLException {
		Connection connessione = ds.getConnection();//ottengo la connessione
		
		String query="INSERT INTO recensisce VALUES(?,?,?);";
		PreparedStatement ps= connessione.prepareStatement(query);
		
		ps.setString(1,item.getCliente());
		ps.setInt(2,item.getProdotto());
		ps.setInt(3,item.getVoto());
		
		ps.executeUpdate();//eseguo la insert della recensione
		ps.close();
		connessione.close();
		
	}


	public void doUpdate(RecensisceBean item) throws SQLException {
		
		
	}


	public void doDelete(RecensisceBean item) throws SQLException {

		
	}
	
	public ArrayList<RecensisceBean> getRecensioniCriterio(String condizione,String order) throws SQLException{
		Connection connessione= ds.getConnection();
		String query="SELECT recensisce.cliente,recensisce.prodotto,recensisce.voto FROM recensisce WHERE ? ORDER BY ?;";
		PreparedStatement ps=connessione.prepareStatement(query);
		
		ps.setString(1, condizione);
		ps.setString(2, order);
		ResultSet risultato= ps.executeQuery();
		ArrayList<RecensisceBean> recensioni= new ArrayList<RecensisceBean>();
		while(risultato.next()) {
			RecensisceBean app= new RecensisceBean();
			app.setCliente(risultato.getString("recensisce.cliente"));
			app.setProdotto(risultato.getInt("recensisce.prodotto"));
			app.setVoto(risultato.getInt("recensisce.voto"));
			
			recensioni.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		return recensioni;
	}

}
