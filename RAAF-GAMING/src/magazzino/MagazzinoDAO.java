package magazzino;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


public class MagazzinoDAO{
	
	DataSource ds = null;
	
	public MagazzinoDAO(DataSource ds)
	{
		this.ds = ds;
	}

	public MagazzinoBean  ricercaPerChiave(String id) throws SQLException {
		if(id==null || id=="")throw new NullPointerException();
		Connection con = ds.getConnection();
		String str = "SELECT * FROM magazzino WHERE indirizzo=? ;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,id);
		ResultSet st = ps.executeQuery();
		MagazzinoBean bean = new MagazzinoBean();
		while(st.next())
		{
			bean.setIndirizzo(st.getString("indirizzo"));
			bean.setCapienza(st.getInt("capienza"));
		}
		
		st.close();
		ps.close();
		con.close();
		
		return bean;
		
	}

	public ArrayList<MagazzinoBean> allElements(String ordinamento) throws SQLException {
		if(ordinamento==null || ordinamento=="")throw new NullPointerException("ordinamento vuoto o null");
		Connection con = ds.getConnection();
		String str = "SELECT * FROM magazzino ORDER BY ?;";
		PreparedStatement ps = con.prepareStatement(str);
		
			ps.setString(1, ordinamento);

		ResultSet st = ps.executeQuery();
		ArrayList<MagazzinoBean> array = new ArrayList<MagazzinoBean>();
		while(st.next())
		{
			MagazzinoBean bean = new MagazzinoBean();
			bean.setIndirizzo(st.getString("indirizzo"));
			bean.setCapienza(st.getInt("capienza"));
			array.add(bean);
		}
		
		st.close();
		ps.close();
		con.close();
		return array;
	}
}
