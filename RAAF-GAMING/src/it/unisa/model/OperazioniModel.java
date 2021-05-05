package it.unisa.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OperazioniModel<T> {
	public T doRetriveByKey(String code) throws SQLException;//ricerca in base al codice
	
	public ArrayList<T> doRetriveAll(String order) throws SQLException;//ottengo tutte le righe in ordine
	
	public void doSave(T item) throws SQLException;
	
	public void doUpdate(T item) throws SQLException;
	
	public void doDelete(T item) throws SQLException;
}
