package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class AnnullatoModelDAO implements OperazioniModel<AnnullatoBean> {

	DataSource ds;
	
	public AnnullatoModelDAO(DataSource d) {
		ds=d;
	}
	
	@Override
	public AnnullatoBean doRetriveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AnnullatoBean> doRetriveAll(String order) throws SQLException {
		
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM annullato,ordine WHERE annullato.codice=ordine.codice ORDER BY ?;";
		PreparedStatement ps= connessione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1, "ordine.data_acquisto desc");
		
		ArrayList<AnnullatoBean> a= new ArrayList<AnnullatoBean>();
		ResultSet risultato= ps.executeQuery();
		while(risultato.next()) {
			AnnullatoBean app= new AnnullatoBean();
			app.setCodice(risultato.getString("annullato.codice"));
			app.setData_di_annullamento(risultato.getDate("annullato.data_di_annullamento"));
			
			a.add(app);
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		return a;
	}

	@Override
	public void doSave(AnnullatoBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUpdate(AnnullatoBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(AnnullatoBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

}
