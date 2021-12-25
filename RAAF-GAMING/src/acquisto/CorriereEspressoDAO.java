package acquisto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.OperazioniModel;

public class CorriereEspressoDAO implements OperazioniModel<CorriereEspressoBean> {
	
	DataSource ds =null;//la connessione al DB la ottengo dal Controller che se la va a prendere dal ServletContext

	
	public CorriereEspressoDAO(DataSource d)
	{
		ds=d;
	}

	@Override
	public CorriereEspressoBean doRetriveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CorriereEspressoBean> doRetriveAll(String order) throws SQLException {
		Connection connessione = ds.getConnection();
		String Query="SELECT * FROM corriereespresso ODER BY ?";
		
		PreparedStatement ps= connessione.prepareStatement(Query);
		if(order!=null && order!="") 
		{
		ps.setString(1, order);
		}else { ps.setString(1, "nome asc");}
		
		ResultSet rs= ps.executeQuery();
		ArrayList<CorriereEspressoBean> a= new ArrayList<CorriereEspressoBean>();
		
		while(rs.next())
		{
		CorriereEspressoBean app= new CorriereEspressoBean();
		app.setNome(rs.getString("nome"));
		app.setSito(rs.getString("sito"));
		a.add(app);
		}
		rs.close();
		ps.close();
		connessione.close();
		return a;
	}

	@Override
	public void doSave(CorriereEspressoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(CorriereEspressoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(CorriereEspressoBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
