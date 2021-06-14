package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class CartaFedeltaModelDAO implements OperazioniModel<CartaFedeltaBean> {

	DataSource ds;
	
	public CartaFedeltaModelDAO(DataSource d) {
		ds=d;
	}
	
	@Override
	public CartaFedeltaBean doRetriveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CartaFedeltaBean> doRetriveAll(String order) throws SQLException {
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM cartafedelta ORDER BY ?;";
		PreparedStatement ps= connessione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1, "codice asc");
		
		ArrayList<CartaFedeltaBean> a= new ArrayList<CartaFedeltaBean>();
		ResultSet risultato= ps.executeQuery();
		while(risultato.next()) {
			CartaFedeltaBean app= new CartaFedeltaBean();
			app.setCodice(risultato.getString("codice"));
			app.setPunti(risultato.getInt("punti"));
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
	}

	@Override
	public void doSave(CartaFedeltaBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUpdate(CartaFedeltaBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(CartaFedeltaBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

}
