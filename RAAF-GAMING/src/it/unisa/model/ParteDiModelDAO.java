package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ParteDiModelDAO implements OperazioniModel<ParteDiBean>{
	DataSource ds = null;
	
	public ParteDiModelDAO(DataSource ds)
	{
		this.ds = ds;
	}


	public ParteDiBean doRetriveByKey(String code1, String code2) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM parte_di WHERE videogioco=? && categoria=? ;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,code1);
		ps.setString(2,code2);
		ResultSet st = ps.executeQuery();
		ParteDiBean bean = new ParteDiBean();
		while(st.next())
		{
			bean.setCategoria(st.getString("categoria"));
			bean.setVideogioco(st.getInt("videogioco"));
		}
		
		st.close();
		ps.close();
		con.close();
		return bean;
	}


	public ArrayList<ParteDiBean> doRetriveAll(String order) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM parte_di ORDER BY ?;";
		PreparedStatement ps = con.prepareStatement(str);
		if(order!=null && order!="") {
			ps.setString(1, order);
		}
		else {
			ps.setString(1, "categoria asc");
		}
		ResultSet st = ps.executeQuery();
		ArrayList<ParteDiBean> array = new ArrayList<ParteDiBean>();
		while(st.next())
		{
			ParteDiBean bean = new ParteDiBean();
			bean.setCategoria(st.getString("categoria"));
			bean.setVideogioco(st.getInt("videogioco"));
			array.add(bean);
		}
		st.close();
		ps.close();
		con.close();
		return array;
	}


	public void doSave(ParteDiBean item) throws SQLException {

	}


	public void doUpdate(ParteDiBean item) throws SQLException {

	}


	public void doDelete(ParteDiBean item) throws SQLException {

	}


	public ParteDiBean doRetriveByKey(String code) throws SQLException {
		return null;
	}
	
	public ArrayList<ParteDiBean> doRetriveByCategoria(String code) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM parte_di WHERE categoria=?;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,code);
		ResultSet st = ps.executeQuery();
		ArrayList<ParteDiBean> array = new ArrayList<ParteDiBean>();
		while(st.next())
		{
			ParteDiBean bean = new ParteDiBean();
			bean.setCategoria(st.getString("categoria"));
			bean.setVideogioco(st.getInt("videogioco"));
			array.add(bean);
		}
		
		st.close();
		ps.close();
		con.close();
		return array;
	}

}
