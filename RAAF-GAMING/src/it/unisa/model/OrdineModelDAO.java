package it.unisa.model;

import java.sql.Connection;
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
	return array;
	}

	public void doSave(OrdineBean item) throws SQLException {

	}


	public void doUpdate(OrdineBean item) throws SQLException {

	}

	public void doDelete(OrdineBean item) throws SQLException {

	}

}
