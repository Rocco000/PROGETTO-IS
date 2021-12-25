package magazzino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.OperazioniModel;

public class MagazzinoDAO implements OperazioniModel<MagazzinoBean>{
	
	DataSource ds = null;
	
	public MagazzinoDAO(DataSource ds)
	{
		this.ds = ds;
	}

	public MagazzinoBean doRetriveByKey(String code) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM magazzino WHERE indirizzo=? ;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,code);
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

	public ArrayList<MagazzinoBean> doRetriveAll(String order) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM magazzino ORDER BY ?;";
		PreparedStatement ps = con.prepareStatement(str);
		if(order!=null && order!="") {
			ps.setString(1, order);
		}
		else {
			ps.setString(1, "indirizzo asc");
		}
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

	public void doSave(MagazzinoBean item) throws SQLException {

	}

	public void doUpdate(MagazzinoBean item) throws SQLException {

	}


	public void doDelete(MagazzinoBean item) throws SQLException {

	}


}
