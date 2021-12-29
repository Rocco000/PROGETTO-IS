package prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;



public class ProdottoDAO{
	DataSource ds = null;
	
	public ProdottoDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public ProdottoBean ricercaPerChiave(String code) throws SQLException {
		if(code==null || code.equals(""))
			throw new NullPointerException("code e' null o stringa vuota");
		else {
			Connection con = ds.getConnection();
			String str = "SELECT * FROM prodotto WHERE codice_prodotto=? ;";
			PreparedStatement ps = con.prepareStatement(str);
			int codice= Integer.parseInt(code);
			ps.setInt(1,codice);
			ResultSet st = ps.executeQuery();
			ProdottoBean bean = null;
			if(st.next())
			{
				bean = new ProdottoBean();
				bean.setCodice_prodotto(st.getInt("codice_prodotto"));
				bean.setCopertina(st.getBytes("copertina"));
				bean.setData_uscita(st.getDate("data_uscita"));
				bean.setFornitore(st.getString("fornitore"));
				bean.setNome(st.getString("nome"));
				bean.setPrezzo(st.getDouble("prezzo"));
				bean.setQuantita_fornitura(st.getInt("quantita_fornitura"));
				bean.setSconto(st.getInt("sconto"));
				bean.setUltima_fornitura(st.getDate("data_fornitura"));
				
				st.close();
				ps.close();
				con.close();
				return bean;
			}
			st.close();
			ps.close();
			con.close();
			return bean;
		}
	}
	
	public ArrayList<ProdottoBean> ricercaPerSottostringa(String nome) throws SQLException {
		if(nome==null)
			throw new NullPointerException("nome e' null");
		else {
			Connection con = ds.getConnection();
			String str = "SELECT * FROM prodotto WHERE nome LIKE ? ;";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,"%"+nome+"%");
			ResultSet st = ps.executeQuery();
			ArrayList<ProdottoBean> b = new ArrayList<ProdottoBean>();
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
				
				b.add(bean);
			}
			st.close();
			ps.close();
			con.close();
			return b;
		}
	}
	
	public ArrayList<ProdottoBean> ricercaPerNome(String nome) throws SQLException {
		if(nome==null)
			throw new NullPointerException("nome e' null");
		else {
			Connection con = ds.getConnection();
			String str = "SELECT * FROM prodotto WHERE nome=?";
			PreparedStatement ps = con.prepareStatement(str);
			ps.setString(1,nome);
			ResultSet st = ps.executeQuery();
			ArrayList<ProdottoBean> b = new ArrayList<ProdottoBean>();
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
				
				b.add(bean);
			}
			st.close();
			ps.close();
			con.close();
			return b;
		}
	}
	
	public ProdottoBean getMax() throws SQLException
	{
		Connection con = ds.getConnection();
		String str = "SELECT MAX(codice_prodotto) AS massimo FROM prodotto;";
		PreparedStatement ps = con.prepareStatement(str);
		ResultSet st = ps.executeQuery();
		ProdottoBean prod = new ProdottoBean();
		if(st.next())
		{
			prod.setCodice_prodotto(st.getInt("massimo"));
		}
		st.close();
		ps.close();
		con.close();
		return prod;
	}

	public ArrayList<ProdottoBean> allElements(String ordinamento) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM prodotto ORDER BY ?;";
		PreparedStatement ps = con.prepareStatement(str);
		if(ordinamento!=null && ordinamento!="") {
			ps.setString(1, ordinamento);
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


	public void newInsert(ProdottoBean item) throws SQLException {
		if(item==null)
			throw new NullPointerException("l'item e' null");
		else {
			Connection con = ds.getConnection();
			String str = "INSERT INTO prodotto VALUES(?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(str);
			
			ps.setInt(1,item.getCodice_prodotto());
			ps.setDouble(2,item.getPrezzo());
			ps.setBytes(3,item.getCopertina());
			ps.setInt(4,item.getSconto());
			ps.setDate(5, item.getData_uscita());
			ps.setString(6, item.getNome());
			ps.setInt(7,item.getQuantita_fornitura());
			ps.setDate(8, item.getUltima_fornitura());
			ps.setString(9,item.getFornitore());
			
			ps.executeUpdate();
			ps.close();
			con.close();
		}
	}

	public void doUpdate(ProdottoBean item) throws SQLException {
		if(item==null)
			throw new NullPointerException("l'item e' nulll");
		else {
			Connection con = ds.getConnection();
			String str = "UPDATE prodotto SET quantita_fornitura=? , ultima_fornitura=? WHERE codice_prodotto=?;";
			PreparedStatement ps = con.prepareStatement(str);
			
			ps.setInt(1,item.getQuantita_fornitura());
			ps.setDate(2,item.getUltima_fornitura());
			ps.setInt(3,item.getCodice_prodotto());
			
			ps.executeUpdate();
			ps.close();
			con.close();
		}
	}


	public void doDelete(ProdottoBean item) throws SQLException {

		
	}

	

}
