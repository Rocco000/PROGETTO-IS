package it.unisa.model;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoModelDAO implements OperazioniModel<ProdottoBean>{

	public ProdottoBean doRetriveByKey(String code) throws SQLException {
		return null;
	}

	public ArrayList<ProdottoBean> doRetriveAll(String order) throws SQLException {
		return null;
	}


	public void doSave(ProdottoBean item) throws SQLException {

	}

	public void doUpdate(ProdottoBean item) throws SQLException {

	}


	public void doDelete(ProdottoBean item) throws SQLException {

		
	}

}
