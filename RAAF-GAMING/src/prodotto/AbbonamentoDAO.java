package prodotto;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class AbbonamentoDAO{

	DataSource ds;
	
	public AbbonamentoDAO(DataSource d) {
		ds=d;
	}
	

	public AbbonamentoBean ricercaPerChiave(String code) throws SQLException {
		if(code==null || code =="")throw new NullPointerException();
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM abbonamento WHERE prodotto=?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setString(1,code);
		ResultSet risultato= ps.executeQuery();
		
		while(risultato.next()) {
			AbbonamentoBean app= new AbbonamentoBean();
			
			app.setCodice(risultato.getString("codice"));
			app.setProdotto(risultato.getInt("prodotto"));
			app.setDurata_abbonamento(risultato.getInt("durata_abbonamento"));

			risultato.close();
			ps.close();
			connessione.close();
			
			return app;
		}

		risultato.close();
		ps.close();
		connessione.close();
		
		return null;
		
	}


	public ArrayList<AbbonamentoBean> allElements(String ordinamento) throws SQLException {
		if(ordinamento==null || ordinamento=="")throw new NullPointerException("ordinamento vuoto o null");
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM abbonamento ORDER BY ?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setString(1, ordinamento);
		ArrayList<AbbonamentoBean> a= new ArrayList<AbbonamentoBean>();
		ResultSet risultato= ps.executeQuery();
		
		while(risultato.next()) {
			AbbonamentoBean app= new AbbonamentoBean();
			
			app.setCodice(risultato.getString("codice"));
			app.setProdotto(risultato.getInt("prodotto"));
			app.setDurata_abbonamento(risultato.getInt("durata_abbonamento"));
			
			a.add(app);
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
	}


	public void newInsert(AbbonamentoBean item) throws SQLException {
		if(item==null)throw new NullPointerException();
		Connection con = ds.getConnection();
		String str ="INSERT INTO abbonamento VALUES(?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		
		ps.setString(1,item.getCodice());
		ps.setInt(2,item.getProdotto());
		ps.setInt(3,item.getDurata_abbonamento());
		
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}
}
