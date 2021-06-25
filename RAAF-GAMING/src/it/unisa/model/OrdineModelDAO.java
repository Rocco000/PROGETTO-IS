package it.unisa.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import javax.sql.DataSource;

public class OrdineModelDAO implements OperazioniModel<OrdineBean> {
	
	DataSource ds = null;
	
	public OrdineModelDAO(DataSource ds)
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
			array.add(bean);
		}
		
		st.close();
		ps.close();
		con.close();
		return array;
	}

	public void doSave(OrdineBean item) throws SQLException {
		Connection con = ds.getConnection();
		String str = "insert into ordine values(?,?,?,?,?,?);";
		PreparedStatement ps = con.prepareStatement(str);
		ps.setString(1,item.getCodice());
		ps.setString(2,item.getMetodo_di_pagamento());
		ps.setDate(3, item.getData_acquisto());
		ps.setString(4,item.getIndirizzo_di_consegna());
		ps.setString(5,item.getCliente());
		ps.setDouble(6,item.getPrezzo_totale());
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
			
			a.add(app);
		}
		
		risultato.close();
		ps.close();
		connessione.close();
		return a;
	}

}
