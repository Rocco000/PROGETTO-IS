package prodotto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;


public class VideogiocoDAO{

	DataSource ds;
	public VideogiocoDAO(DataSource d) {
		ds=d;
	}
	
	public VideogiocoBean ricercaPerChiave(String code) throws SQLException {
		if(code==null||code=="")throw new NullPointerException("code vuoto o null");
		Connection connessione = ds.getConnection();
		String query="SELECT * FROM videogioco WHERE prodotto=?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		int codice= Integer.parseInt(code);
		ps.setInt(1, codice);
		
		ResultSet risultato= ps.executeQuery();
		if(risultato.next()) {
			VideogiocoBean gioco= new VideogiocoBean();
			gioco.setDimensione(risultato.getDouble("dimensione"));
			gioco.setEdizione_limitata(risultato.getBoolean("edizione_limitata"));
			gioco.setNcd(risultato.getInt("ncd"));
			gioco.setPegi(risultato.getInt("pegi"));
			gioco.setProdotto(risultato.getInt("prodotto"));
			gioco.setSoftware_house(risultato.getString("software_house"));
			gioco.setVkey(risultato.getString("vkey"));
			
			risultato.close();
			ps.close();
			connessione.close();
			
			return gioco;
		}
		else {
			risultato.close();
			ps.close();
			connessione.close();
			return null;
		}
	}

	
	public ArrayList<VideogiocoBean> allElements(String ordinamento) throws SQLException {
		if(ordinamento==null||ordinamento=="")throw new NullPointerException("ordinamento vuoto o null");
		Connection connessione=ds.getConnection();
		String query="SELECT * FROM videogioco,prodotto WHERE videogioco.prodotto=prodotto.codice_prodotto ORDER BY ?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
			ps.setString(1, ordinamento);
		ArrayList<VideogiocoBean>a=new ArrayList<VideogiocoBean>();
		ResultSet risultato=ps.executeQuery();
		while(risultato.next()) {
			VideogiocoBean app=new VideogiocoBean();
			app.setDimensione(risultato.getDouble("videogioco.dimensione"));
			app.setEdizione_limitata(risultato.getBoolean("videogioco.edizione_limitata"));
			app.setNcd(risultato.getInt("videogioco.ncd"));
			app.setPegi(risultato.getInt("videogioco.pegi"));
			app.setProdotto(risultato.getInt("videogioco.prodotto"));
			app.setSoftware_house(risultato.getString("videogioco.software_house"));
			app.setVkey(risultato.getString("videogioco.vkey"));
			a.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		
		return a;
			
		
		
	}

	
	public void newInsert(VideogiocoBean item) throws SQLException {
		if(item==null)throw new NullPointerException("item null");
		Connection con = ds.getConnection();
		String str = "INSERT INTO videogioco VALUES(?,?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		
		ps.setInt(1,item.getProdotto());
		ps.setDouble(2,item.getDimensione());
		ps.setInt(3,item.getPegi());
		ps.setBoolean(4,item.getEdizione_limitata());
		ps.setInt(5,item.getNcd());
		ps.setString(6,item.getVkey());
		ps.setString(7,item.getSoftware_house());
		
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}

	
	
	public VideogiocoBean getTopRecensione() throws SQLException{
		Connection connessione=ds.getConnection();
		String query="SELECT v.*,recensione.voto_medio_assegnato FROM videogioco v,(SELECT r.prodotto as codice_prodotto, avg(voto) as voto_medio_assegnato FROM recensisce r GROUP BY r.prodotto) as recensione WHERE v.prodotto = recensione.codice_prodotto ORDER BY recensione.voto_medio_assegnato desc;";
		PreparedStatement ps= connessione.prepareStatement(query);
		ResultSet risultato= ps.executeQuery();
		
		if(risultato.next()) {
			VideogiocoBean migliorVideogioco= new VideogiocoBean();
			migliorVideogioco.setDimensione(risultato.getDouble("v.dimensione"));
			migliorVideogioco.setEdizione_limitata(risultato.getBoolean("v.edizione_limitata"));
			migliorVideogioco.setNcd(risultato.getInt("v.ncd"));
			migliorVideogioco.setPegi(risultato.getInt("v.pegi"));
			migliorVideogioco.setProdotto(risultato.getInt("v.prodotto"));
			migliorVideogioco.setSoftware_house(risultato.getString("v.software_house"));
			migliorVideogioco.setVkey(risultato.getString("v.vkey"));
			
			risultato.close();
			ps.close();
			connessione.close();
			return migliorVideogioco;
		}
		else {
			risultato.close();
			ps.close();
			connessione.close();
			return null;
		}
		
	}
	
	public VideogiocoBean getUltimoUscito(int code) throws SQLException{
		if(code<=0)throw new NullPointerException("code minore di 0");
		Connection connessione=ds.getConnection();
		String query="SELECT videogioco.* FROM videogioco,prodotto,(SELECT MAX(prodotto.data_uscita) AS ultimo FROM prodotto,videogioco WHERE prodotto.codice_prodotto=videogioco.prodotto AND prodotto.codice_prodotto!=?) AS temp WHERE videogioco.prodotto=prodotto.codice_prodotto AND prodotto.data_uscita=temp.ultimo;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setInt(1, code);
		ResultSet risultato= ps.executeQuery();
		if(risultato.next()) {
			int codiceVideogioco= risultato.getInt("videogioco.prodotto");//ottengo il codice del videogioco con data ultima
			VideogiocoBean ultimoUscito= this.ricercaPerChiave(""+codiceVideogioco);//ottengo quel videogioco
			
			risultato.close();
			ps.close();
			connessione.close();
			return ultimoUscito;
		}
		else {
			risultato.close();
			ps.close();
			connessione.close();
			return null;
		}
	}
	
	public ArrayList<VideogiocoBean> getVideogiochiScontati(int code1,int code2) throws SQLException{
		if((code1<=0 && code2<=0) && code1==code2)throw new NullPointerException("code1 e code2 minoro o uguale a 0 oppure sono uguali");
		Connection connessione=ds.getConnection();
		String query="SELECT videogioco.* FROM prodotto,videogioco WHERE prodotto.codice_prodotto=videogioco.prodotto AND sconto>0 AND prodotto.codice_prodotto!=? AND prodotto.codice_prodotto!=?;";
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setInt(1, code1);
		ps.setInt(2, code2);
		ResultSet risultato= ps.executeQuery();
		
		int i=1;
		ArrayList<VideogiocoBean> a= new ArrayList<VideogiocoBean>();
		while(risultato.next() && i<=4) {
			VideogiocoBean app= new VideogiocoBean();
			app.setDimensione(risultato.getDouble("videogioco.dimensione"));
			app.setEdizione_limitata(risultato.getBoolean("videogioco.edizione_limitata"));
			app.setNcd(risultato.getInt("videogioco.ncd"));
			app.setPegi(risultato.getInt("videogioco.pegi"));
			app.setProdotto(risultato.getInt("videogioco.prodotto"));
			app.setSoftware_house(risultato.getString("videogioco.software_house"));
			app.setVkey(risultato.getString("videogioco.vkey"));
			a.add(app);
			i++;
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		return a;
	}

}
