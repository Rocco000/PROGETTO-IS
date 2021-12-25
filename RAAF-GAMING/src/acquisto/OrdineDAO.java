package acquisto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;


import javax.sql.DataSource;

import it.unisa.model.OperazioniModel;

public class OrdineDAO implements OperazioniModel<OrdineBean> {
	
	DataSource ds = null;
	
	public OrdineDAO(DataSource ds)
	{
		this.ds = ds;
	}

	public OrdineBean doRetriveByKey(String code) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM ordine WHERE codice=? ;";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,code);
		ResultSet st = ps.executeQuery();
		OrdineBean bean = new OrdineBean();
		while(st.next())
		{
			bean.setCodice(st.getString("codice"));
			bean.setMetodo_di_pagamento(st.getString("metodo_di_pagamento"));
			bean.setData_acquisto(st.getDate("data_acquisto"));
			bean.setIndirizzo_di_consegna(st.getString("indirizzo_di_consegna"));
			bean.setCliente(st.getString("cliente"));
			bean.setPrezzo_totale(st.getDouble("prezzo_totale"));
			bean.setGestore(st.getString("gestore"));
			bean.setStato(st.getString("stato"));
		}
		
		st.close();
		ps.close();
		con.close();
		
		return bean;
	}

	public ArrayList<OrdineBean> doRetriveAll(String order) throws SQLException {
		Connection con = ds.getConnection();
		String str = "SELECT * FROM ordine ORDER BY ?;";
		PreparedStatement ps = con.prepareStatement(str);
		if(order!=null && order!="") {
			ps.setString(1, order);
		}
		else {
			ps.setString(1, "codice asc");
		}
		ResultSet st = ps.executeQuery();
		ArrayList<OrdineBean> array = new ArrayList<OrdineBean>();
		while(st.next())
		{
			OrdineBean bean = new OrdineBean();
			bean.setCodice(st.getString("codice"));
			bean.setMetodo_di_pagamento(st.getString("metodo_di_pagamento"));
			bean.setData_acquisto(st.getDate("data_acquisto"));
			bean.setIndirizzo_di_consegna(st.getString("indirizzo_di_consegna"));
			bean.setCliente(st.getString("cliente"));
			bean.setPrezzo_totale(st.getDouble("prezzo_totale"));
			bean.setGestore(st.getString("gestore"));
			bean.setStato(st.getString("stato"));
			array.add(bean);
		}
		
		st.close();
		ps.close();
		con.close();
		return array;
	}

	public void doSave(OrdineBean item) throws SQLException {
		Connection con = ds.getConnection();
		String str = "insert into ordine values(?,?,?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,item.getCodice());
		ps.setDate(2, item.getData_acquisto());
		ps.setString(3,item.getIndirizzo_di_consegna());
		ps.setString(4,item.getCliente());
		ps.setDouble(5,item.getPrezzo_totale());
		ps.setNull(6, Types.NULL);//per settare la chiave del gestore a null
		ps.setString(7, item.getStato());
		ps.setString(8, item.getMetodo_di_pagamento());
		ps.executeUpdate();
		ps.close();
		con.close();
		
	}


	public void doUpdate(OrdineBean item) throws SQLException {

	}

	public void doDelete(OrdineBean item) throws SQLException {

	}
	
	public ArrayList<OrdineBean> doRetriveByClient(String cliente) throws SQLException{
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM ordine WHERE cliente=? ORDER BY data_acquisto desc;";
		PreparedStatement ps= connessione.prepareStatement(query);
		ps.setString(1, cliente);
		
		ResultSet risultato= ps.executeQuery();
		ArrayList<OrdineBean> a= new ArrayList<OrdineBean>();
		while(risultato.next()) {
			OrdineBean app= new OrdineBean();
			app.setCodice(risultato.getString("codice"));
			app.setIndirizzo_di_consegna(risultato.getString("indirizzo_di_consegna"));
			app.setMetodo_di_pagamento(risultato.getString("metodo_di_pagamento"));
			app.setCliente(risultato.getString("cliente"));
			app.setPrezzo_totale(risultato.getDouble("prezzo_totale"));
			app.setData_acquisto(risultato.getDate("data_acquisto"));
			app.setGestore(risultato.getString("gestore"));
			app.setStato(risultato.getString("stato"));
			a.add(app);
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		return a;
	}
	
	public ArrayList<OrdineBean> getOrdiniNonConsegnati() throws SQLException{
		Connection connessione= ds.getConnection();
		String query="SELECT * FROM ordine o WHERE gestore=NULL;";
		PreparedStatement ps= connessione.prepareStatement(query);
		
		ResultSet risultato= ps.executeQuery();
		ArrayList<OrdineBean> nonConsegnati= new ArrayList<OrdineBean>();
		while(risultato.next()) {
			OrdineBean app= new OrdineBean();
			app.setCodice(risultato.getString("codice"));
			app.setIndirizzo_di_consegna(risultato.getString("indirizzo_di_consegna"));
			app.setMetodo_di_pagamento(risultato.getString("metodo_di_pagamento"));
			app.setCliente(risultato.getString("cliente"));
			app.setData_acquisto(risultato.getDate("data_acquisto"));
			app.setPrezzo_totale(risultato.getDouble("prezzo_totale"));
			app.setGestore(risultato.getString("gestore"));
			app.setStato(risultato.getString("stato"));
			
			nonConsegnati.add(app);
		}
		risultato.close();
		ps.close();
		connessione.close();
		return nonConsegnati;
	}

}
