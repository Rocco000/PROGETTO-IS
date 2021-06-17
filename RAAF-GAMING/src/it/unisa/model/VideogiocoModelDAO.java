package it.unisa.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class VideogiocoModelDAO implements OperazioniModel<VideogiocoBean> {

	DataSource ds;
	public VideogiocoModelDAO(DataSource d) {
		ds=d;
	}
	
	public VideogiocoBean doRetriveByKey(String code) throws SQLException {
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

	
	public ArrayList<VideogiocoBean> doRetriveAll(String order) throws SQLException {
		Connection connessione=ds.getConnection();
		String query="SELECT * FROM videogioco,prodotto WHERE videogioco.prodotto=prodotto.codice_prodotto ORDER BY ?;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		
		if(order!=null && !order.equals(""))
			ps.setString(1, order);
		else
			ps.setString(1,"videogioco.prodotto");
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

	
	public void doSave(VideogiocoBean item) throws SQLException {
		
		
	}

	
	public void doUpdate(VideogiocoBean item) throws SQLException {
	
		
	}

	
	public void doDelete(VideogiocoBean item) throws SQLException {
		
		
	}
	
	public VideogiocoBean getTopRecensione() throws SQLException{
		Connection connessione=ds.getConnection();
		//ottengo per ogni videogioco il suo voto medio, li ordino in senso decrescente
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
	
	public VideogiocoBean getUltimoUscito() throws SQLException{
		Connection connessione=ds.getConnection();
		//ottengo i videogiochi con data di uscita ultima
		String query="SELECT MAX(data_uscita) AS ultimaData,prodotto.codice_prodotto FROM prodotto,videogioco WHERE prodotto.codice_prodotto=videogioco.prodotto GROUP BY prodotto.codice_prodotto;";
		
		PreparedStatement ps= connessione.prepareStatement(query);
		ResultSet risultato= ps.executeQuery();
		if(risultato.next()) {
			int codiceVideogioco= risultato.getInt("prodotto.codice_prodotto");//ottengo il codice del videogioco con data ultima
			VideogiocoBean ultimoUscito= this.doRetriveByKey(""+codiceVideogioco);//ottengo quel videogioco
			
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
	
	public ArrayList<VideogiocoBean> getVideogiochiScontati() throws SQLException{
		Connection connessione=ds.getConnection();
		//ottengo tutti i videogiochi che sono scontati
		String query="SELECT videogioco.prodotto,videogioco.dimensione,videogioco.pegi,videogioco.edizione_limitata,videogioco.ncd,videogioco.vkey,videogioco.software_house FROM prodotto,videogioco WHERE prodotto.codice_prodotto=videogioco.prodotto AND sconto>0;";
		PreparedStatement ps= connessione.prepareStatement(query);
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
