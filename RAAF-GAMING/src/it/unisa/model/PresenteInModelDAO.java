package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class PresenteInModelDAO implements OperazioniModel<PresenteInBean>{
	DataSource ds = null;
	
	public PresenteInModelDAO(DataSource ds)
	{
		this.ds = ds;
	}
	public PresenteInBean doRetriveByKey(String code) throws SQLException {

		return null;
	}
	
	public PresenteInBean doRetriveByKey(int code1,String code2) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM presente_in WHERE prodotto=? && magazzino=? ;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setInt(1,code1);
		ps.setString(2,code2);
		ResultSet st = ps.executeQuery();
		PresenteInBean bean = new PresenteInBean();
		while(st.next())
		{
			bean.setMagazzino(st.getString("magazzino"));
			bean.setProdotto(st.getInt("prodotto"));
			bean.setQuantita_disponibile(st.getInt("quantita_disponibile"));
		}
		
		st.close();
		ps.close();
		con.close();
		return bean;
	}


	public ArrayList<PresenteInBean> doRetriveAll(String order) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM presente_in ORDER BY ?;";
		PreparedStatement ps = con.prepareStatement(str);
		if(order!=null && order!="") {
			ps.setString(1, order);
		}
		else {
			ps.setString(1, "prodotto asc");
		}
		ResultSet st = ps.executeQuery();
		
		ArrayList<PresenteInBean> array = new ArrayList<PresenteInBean>();
		while(st.next())
		{
			PresenteInBean bean = new PresenteInBean();
			bean.setMagazzino(st.getString("magazzino"));
			bean.setProdotto(st.getInt("prodotto"));
			bean.setQuantita_disponibile(st.getInt("quantita_disponibile"));
			array.add(bean);
		}
		
		st.close();
		ps.close();
		con.close();
		return array;
	}


	public void doSave(PresenteInBean item) throws SQLException {

		
	}


	public void doUpdate(PresenteInBean item) throws SQLException {
		
	}

	public void doDelete(PresenteInBean item) throws SQLException {

	}

}
