package prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.OperazioniModel;

public class CategoriaDAO implements OperazioniModel<CategoriaBean> {

	DataSource ds;
	
	public CategoriaDAO(DataSource d) {
		ds=d;
	}
	
	@Override
	public CategoriaBean doRetriveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CategoriaBean> doRetriveAll(String order) throws SQLException {
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM categoria ORDER BY ?;";
		PreparedStatement ps= connessione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1, "nome asc");
		
		ArrayList<CategoriaBean> a= new ArrayList<CategoriaBean>();
		ResultSet risultato= ps.executeQuery();
		while(risultato.next()) {
			CategoriaBean app= new CategoriaBean();
			app.setNome(risultato.getString("nome"));
			a.add(app);
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		return a;
	}

	@Override
	public void doSave(CategoriaBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doUpdate(CategoriaBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doDelete(CategoriaBean item) throws SQLException {
		// TODO Auto-generated method stub

	}

}
