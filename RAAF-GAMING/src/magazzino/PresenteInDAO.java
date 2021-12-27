package magazzino;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


public class PresenteInDAO{
	DataSource ds = null;
	
	public PresenteInDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	public ArrayList<PresenteInBean> ricercaPerProdotto(String id) throws SQLException {
		if(id==null || id=="")throw new NullPointerException();
		Connection con = ds.getConnection();
		String str = "SELECT * FROM presente_in WHERE prodotto=?;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,id);
		ResultSet st = ps.executeQuery();
		ArrayList<PresenteInBean> app = new ArrayList<PresenteInBean>();
		while(st.next())
		{
			PresenteInBean bean = new PresenteInBean();
			bean.setMagazzino(st.getString("magazzino"));
			bean.setProdotto(st.getInt("prodotto"));
			bean.setQuantita_disponibile(st.getInt("quantita_disponibile"));
			app.add(bean);
		}
		
		st.close();
		ps.close();
		con.close();
		return app;
	}
	
	public PresenteInBean ricercaPerChiave(int id1,String id2) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM presente_in WHERE prodotto=? && magazzino=? ;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setInt(1,id1);
		ps.setString(2,id2);
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


	    public ArrayList<PresenteInBean> allElements(String ordinamento) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM presente_in ORDER BY ?;";
		PreparedStatement ps = con.prepareStatement(str);
		if(ordinamento!=null && ordinamento!="") {
			ps.setString(1, ordinamento);
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


	public void newInsert(PresenteInBean item) throws SQLException {
		if(item==null)throw new NullPointerException();
		Connection con = ds.getConnection();
		String str = "INSERT INTO presente_in VALUES(?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,item.getMagazzino());
		ps.setInt(2,item.getProdotto());
		ps.setInt(3,item.getQuantita_disponibile());
		ps.executeUpdate();
		ps.close();
		con.close();
	}


	public void doUpdate(PresenteInBean item) throws SQLException {
		if(item==null)throw new NullPointerException();
		Connection con = ds.getConnection();
		String str = "UPDATE presente_in SET quantita_disponibile=quantita_disponibile-1 where prodotto=? AND magazzino=?;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setInt(1,item.getProdotto());
		ps.setString(2,item.getMagazzino());
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	public void rifornitura(PresenteInBean item) throws SQLException {
		if(item==null)throw new NullPointerException();
		Connection con = ds.getConnection();
		String str = "UPDATE presente_in SET quantita_disponibile=? where prodotto=? AND magazzino=?;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setInt(1, item.getQuantita_disponibile());
		ps.setInt(2,item.getProdotto());
		ps.setString(3,item.getMagazzino());
		ps.executeUpdate();
		ps.close();
		con.close();
	}


	public ArrayList<PresenteInBean> ricercaPerMagazzino(String id) throws SQLException{
		if(id==null || id=="")throw new NullPointerException();
		Connection cn=ds.getConnection();
		String str="SELECT * FROM presente_in WHERE magazzino=?;";
		PreparedStatement ps=cn.prepareStatement(str);
		ps.setString(1, id);
		ArrayList<PresenteInBean> pib=new ArrayList<PresenteInBean>();
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			PresenteInBean bean = new PresenteInBean();
			bean.setMagazzino(rs.getString("magazzino"));
			bean.setProdotto(rs.getInt("prodotto"));
			bean.setQuantita_disponibile(rs.getInt("quantita_disponibile"));
			pib.add(bean);
		}
		rs.close();
		ps.close();
		cn.close();
		return pib;
	}
		
	
}
