package acquisto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import it.unisa.model.OperazioniModel;

public class CorriereEspressoDAO{
	
	DataSource ds =null;//la connessione al DB la ottengo dal Controller che se la va a prendere dal ServletContext

	
	public CorriereEspressoDAO(DataSource d)
	{
		ds=d;
	}
	
	public ArrayList<CorriereEspressoBean> allElements(String ordinamento) throws SQLException {
		if(ordinamento==null || ordinamento=="")
			throw new NullPointerException();
		
		Connection connessione = ds.getConnection();
		String Query="SELECT * FROM corriereespresso ORDER BY ?";
		
		PreparedStatement ps= connessione.prepareStatement(Query);
			ps.setString(1, ordinamento);

		
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

}
