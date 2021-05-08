package it.unisa.model;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class ConsoleModelDAO implements OperazioniModel<ConsoleBean> {
	
	DataSource ds =null;//la connessione al DB la ottengo dal Controller che se la va a prendere dal ServletContext

	
	public ConsoleModelDAO(DataSource d)
	{
		ds=d;
	}
	@Override
	public ConsoleBean doRetriveByKey(String code) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ConsoleBean> doRetriveAll(String order) throws SQLException {
		
		return null;
	}

	@Override
	public void doSave(ConsoleBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doUpdate(ConsoleBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(ConsoleBean item) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
