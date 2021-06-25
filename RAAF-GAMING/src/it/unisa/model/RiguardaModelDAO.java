package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class RiguardaModelDAO implements OperazioniModel<RiguardaBean> {
	DataSource ds;
	public RiguardaModelDAO(DataSource d) {
		ds=d;
	}
	
	public RiguardaBean doRetriveByKey(String code) throws SQLException {
		
		return null;
	}

	
	public ArrayList<RiguardaBean> doRetriveAll(String order) throws SQLException {
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM Riguarda WHERE riguarda.prodotto=prodotto.codice_prodotto AND riguarda.ordine=ordine.codice ORDER BY ;?";
		PreparedStatement ps=connessione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1,order);
		else	
			ps.setString(1, "riguarda.prodotto asc");
		ArrayList<RiguardaBean>a=new ArrayList<RiguardaBean>();
		ResultSet risultato=ps.executeQuery();
		while(risultato.next()) {
			RiguardaBean app=new RiguardaBean();
			app.setOrdine(risultato.getString("riguarda.ordine"));
			app.setProdotto(risultato.getInt("riguarda.prodotto"));
			app.setQuantita_acquistata(risultato.getInt("riguarda.qauntita_acquistata"));
			a.add(app);
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
	}

	
	public void doSave(RiguardaBean item) throws SQLException {
		Connection con = ds.getConnection();
		String str = "insert into riguarda values(?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setInt(1,item.getProdotto());
		ps.setString(2,item.getOrdine());
		ps.setInt(3, item.getQuantita_acquistata());
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}

	
	public void doUpdate(RiguardaBean item) throws SQLException {
		
		
	}


	public void doDelete(RiguardaBean item) throws SQLException {
		
		
	}

}
