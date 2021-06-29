package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class AbbonamentoModelDAO implements OperazioniModel<AbbonamentoBean> {

	DataSource ds;
	
	public AbbonamentoModelDAO(DataSource d) {
		ds=d;
	}
	
	@Override
	public AbbonamentoBean doRetriveByKey(String code) throws SQLException {

		Connection connessione= ds.getConnection();
		String query="SELECT * FROM abbonamento WHERE prodotto=?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setString(1,code);
		ResultSet risultato= ps.executeQuery();
		
		while(risultato.next()) {
			AbbonamentoBean app= new AbbonamentoBean();
			
			app.setCodice(risultato.getString("codice"));
			app.setProdotto(risultato.getInt("prodotto"));
			app.setDurata_abbonamento(risultato.getInt("durata_abbonamento"));

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

	@Override
	public ArrayList<AbbonamentoBean> doRetriveAll(String order) throws SQLException {
		
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM abbonamento ORDER BY ?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1, "prodotto asc");
		
		ArrayList<AbbonamentoBean> a= new ArrayList<AbbonamentoBean>();
		ResultSet risultato= ps.executeQuery();
		
		while(risultato.next()) {
			AbbonamentoBean app= new AbbonamentoBean();
			
			app.setCodice(risultato.getString("codice"));
			app.setProdotto(risultato.getInt("prodotto"));
			app.setDurata_abbonamento(risultato.getInt("durata_abbonamento"));
			
			a.add(app);
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
	}

	@Override
	public void doSave(AbbonamentoBean item) throws SQLException {
		Connection con = ds.getConnection();
		String str ="INSERT INTO abbonamento VALUES(?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		
		ps.setString(1,item.getCodice());
		ps.setInt(2,item.getProdotto());
		ps.setInt(3,item.getDurata_abbonamento());
		
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}

	@Override
	public void doUpdate(AbbonamentoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(AbbonamentoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
