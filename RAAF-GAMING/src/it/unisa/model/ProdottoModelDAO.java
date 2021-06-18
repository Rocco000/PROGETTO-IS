package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ProdottoModelDAO implements OperazioniModel<ProdottoBean>{
	DataSource ds = null;
	
	public ProdottoModelDAO(DataSource ds)
	{
		this.ds = ds;
	}
	public ProdottoBean doRetriveByKey(String code) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM prodotto WHERE codice_prodotto=? ;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,code);
		ResultSet st = ps.executeQuery();
		ProdottoBean bean = new ProdottoBean();
		if(st.next())
		{
			bean.setCodice_prodotto(st.getInt("codice_prodotto"));
			bean.setCopertina(st.getBytes("copertina"));
			bean.setData_uscita(st.getDate("data_uscita"));
			bean.setFornitore(st.getString("fornitore"));
			bean.setNome(st.getString("nome"));
			bean.setPrezzo(st.getDouble("prezzo"));
			bean.setQuantita_fornitura(st.getInt("quantita_fornitura"));
			bean.setSconto(st.getInt("sconto"));
			bean.setUltima_fornitura(st.getDate("ultima_fornitura"));
		}
		st.close();
		ps.close();
		con.close();
	return bean;
	}

	public ArrayList<ProdottoBean> doRetriveAll(String order) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM prodotto ORDER BY ?;";
		PreparedStatement ps = con.prepareStatement(str);
		if(order!=null && order!="") {
			ps.setString(1, order);
		}
		else {
			ps.setString(1, "codice_prodotto asc");
		}
		ResultSet st = ps.executeQuery();
		ArrayList<ProdottoBean> array = new ArrayList<ProdottoBean>();
		while(st.next())
		{
			ProdottoBean bean = new ProdottoBean();
			bean.setCodice_prodotto(st.getInt("codice_prodotto"));
			bean.setCopertina(st.getBytes("copertina"));
			bean.setData_uscita(st.getDate("data_uscita"));
			bean.setFornitore(st.getString("fornitore"));
			bean.setNome(st.getString("nome"));
			bean.setPrezzo(st.getDouble("prezzo"));
			bean.setQuantita_fornitura(st.getInt("quantita_fornitura"));
			bean.setSconto(st.getInt("sconto"));
			bean.setUltima_fornitura(st.getDate("ultima_fornitura"));
			array.add(bean);
		}
		st.close();
		ps.close();
		con.close();
	return array;
	}


	public void doSave(ProdottoBean item) throws SQLException {

	}

	public void doUpdate(ProdottoBean item) throws SQLException {

	}


	public void doDelete(ProdottoBean item) throws SQLException {

		
	}

}
