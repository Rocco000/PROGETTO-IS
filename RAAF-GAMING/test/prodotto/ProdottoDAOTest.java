package prodotto;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acquisto.OrdineBean;
import acquisto.OrdineDAO;
import acquisto.OrdineDAOTest;

public class ProdottoDAOTest extends DataSourceBasedDBTestCase{
	private DataSource ds;
	private ProdottoDAO prod;
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
        ds = this.getDataSource();
        prod = new  ProdottoDAO(ds);
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}


	@Override
	protected DataSource getDataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/db/init/prodotto.sql'");
        dataSource.setUser("root");
        dataSource.setPassword("veloce123");
        return dataSource;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("resources/db/init/prodotto.xml"));
	}
	
	@Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

	@Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }
	
	@Test
	public void testricercaPerChiave() throws SQLException {
		ProdottoBean a = prod.ricercaPerChiave("1");
		ProdottoBean b = new ProdottoBean();
		
		b.setCodice_prodotto(1);
		b.setFornitore("Sony");
		b.setGestore("prodotto@admin.com");
		b.setPrezzo(10.5);
		b.setSconto(0);
		b.setQuantita_fornitura(12);
		b.setUltima_fornitura(java.sql.Date.valueOf("2020-12-20"));
		b.setData_uscita(java.sql.Date.valueOf("2021-12-25"));
		b.setNome("FIFA");
		
		
		assertEquals(a.getCodice_prodotto(),b.getCodice_prodotto());
		assertEquals(a.getFornitore(),b.getFornitore());
		assertEquals(a.getGestore(),b.getGestore());
		assertEquals(a.getPrezzo(),b.getPrezzo());
		assertEquals(a.getSconto(),b.getSconto());
		assertEquals(a.getQuantita_fornitura(),b.getQuantita_fornitura());
		assertEquals(a.getUltima_fornitura(),b.getUltima_fornitura());
		assertEquals(a.getData_uscita(),b.getData_uscita());
		assertEquals(a.getNome(),b.getNome());
	}
	
	public void testRicercaPerChiaveNULL() throws SQLException{
		ProdottoBean  a = null;
		try {
			a = prod.ricercaPerChiave(null);
			}catch(NullPointerException e)
			{
				assertNull(a);
			}
	}
	
	public void testRicercaPerChiaveVoid() throws SQLException{
		ProdottoBean  a = null;
		try {
		a = prod.ricercaPerChiave("");
		}catch(NullPointerException e)
		{
			assertNull(a);
		}
	}
	
	public void testRicercaPerChiaveNotValid() throws NullPointerException
	{
		ProdottoBean  a = null;
		try {
		a = prod.ricercaPerChiave("8");
		}catch(SQLException e)
		{
			assertNull(a);
		}
	}
	
	public void testAllElementsASC() throws SQLException
	{
		ArrayList<ProdottoBean> a = prod.allElements("codice_prodotto asc");
		ArrayList<ProdottoBean> b = new ArrayList<ProdottoBean>();
		
		
		ProdottoBean b2 = new ProdottoBean();
		b2.setCodice_prodotto(1);
		b2.setFornitore("Sony");
		b2.setGestore("prodotto@admin.com");
		b2.setPrezzo(10.5);
		b2.setSconto(0);
		b2.setQuantita_fornitura(12);
		b2.setUltima_fornitura(java.sql.Date.valueOf("2020-12-20"));
		b2.setData_uscita(java.sql.Date.valueOf("2021-12-25"));
		b2.setNome("FIFA");
		b.add(b2);

		ProdottoBean b1 = new ProdottoBean();
		b1.setCodice_prodotto(2);
		b1.setFornitore("Activision");
		b1.setGestore("prodotto@admin.com");
		b1.setPrezzo(15.5);
		b1.setSconto(0);
		b1.setQuantita_fornitura(12);
		b1.setUltima_fornitura(java.sql.Date.valueOf("2019-12-20"));
		b1.setData_uscita(java.sql.Date.valueOf("2020-12-22"));
		b1.setNome("PES");
		b.add(b1);
		
		
		
		assertEquals(a.size(), b.size());
		
		for(int i=0; i<a.size();i++)
		{			
			assertEquals(a.get(i).getCodice_prodotto(),b.get(i).getCodice_prodotto());
			assertEquals(a.get(i).getFornitore(),b.get(i).getFornitore());
			assertEquals(a.get(i).getGestore(),b.get(i).getGestore());
			assertEquals(a.get(i).getPrezzo(),b.get(i).getPrezzo());
			assertEquals(a.get(i).getSconto(),b.get(i).getSconto());
			assertEquals(a.get(i).getQuantita_fornitura(),b.get(i).getQuantita_fornitura());
			assertEquals(a.get(i).getUltima_fornitura(),b.get(i).getUltima_fornitura());
			assertEquals(a.get(i).getData_uscita(),b.get(i).getData_uscita());
			assertEquals(a.get(i).getNome(),b.get(i).getNome());
		}
		
	}
	
	public void testAllElementsDESC() throws SQLException
	{
		ArrayList<ProdottoBean> a = prod.allElements("codice_prodotto desc");
		ArrayList<ProdottoBean> b = new ArrayList<ProdottoBean>();
		

		ProdottoBean b1 = new ProdottoBean();
		b1.setCodice_prodotto(2);
		b1.setFornitore("Activision");
		b1.setGestore("prodotto@admin.com");
		b1.setPrezzo(15.5);
		b1.setSconto(0);
		b1.setQuantita_fornitura(12);
		b1.setUltima_fornitura(java.sql.Date.valueOf("2019-12-20"));
		b1.setData_uscita(java.sql.Date.valueOf("2020-12-22"));
		b1.setNome("PES");
		b.add(b1);
		
		ProdottoBean b2 = new ProdottoBean();
		b2.setCodice_prodotto(1);
		b2.setFornitore("Sony");
		b2.setGestore("prodotto@admin.com");
		b2.setPrezzo(10.5);
		b2.setSconto(0);
		b2.setQuantita_fornitura(12);
		b2.setUltima_fornitura(java.sql.Date.valueOf("2020-12-20"));
		b2.setData_uscita(java.sql.Date.valueOf("2021-12-25"));
		b2.setNome("FIFA");
		b.add(b2);
		
		
		
		assertEquals(a.size(), b.size());
		
		for(int i=0; i<a.size();i++)
		{			
			assertEquals(a.get(i).getCodice_prodotto(),b.get(i).getCodice_prodotto());
			assertEquals(a.get(i).getFornitore(),b.get(i).getFornitore());
			assertEquals(a.get(i).getGestore(),b.get(i).getGestore());
			assertEquals(a.get(i).getPrezzo(),b.get(i).getPrezzo());
			assertEquals(a.get(i).getSconto(),b.get(i).getSconto());
			assertEquals(a.get(i).getQuantita_fornitura(),b.get(i).getQuantita_fornitura());
			assertEquals(a.get(i).getUltima_fornitura(),b.get(i).getUltima_fornitura());
			assertEquals(a.get(i).getData_uscita(),b.get(i).getData_uscita());
			assertEquals(a.get(i).getNome(),b.get(i).getNome());
		}
		
	}
	
	public void testAllElementsNULL() throws SQLException{
		ArrayList<ProdottoBean> a = null;
		try {
			a = prod.allElements(null);
			}catch(NullPointerException e)
			{
				assertNull(a);
			}
	}
	
	public void testAllElementsVoid() throws SQLException{
		ArrayList<ProdottoBean> a = null;
		try {
			a = prod.allElements("");
			}catch(NullPointerException e)
			{
				assertNull(a);
			}
	}
	public void testAllElementsNotValid() throws NullPointerException{
		ArrayList<ProdottoBean> a = null;
		try {
			a = prod.allElements("Codice_pro as");
			}catch(SQLException e)
			{
				assertNull(a);
			}
	}
	
	/*public void testNewInsertNew() throws Exception {      
	      
        ITable expectedTable = new FlatXmlDataSetBuilder()
                   .build(OrdineDAOTest.class.getClassLoader().getResourceAsStream("resources/db/expected/prodotto.xml"))
                   .getTable("prodotto");
     
        ProdottoBean b = new ProdottoBean();
		b.setCodice_prodotto(3);
		b.setFornitore("2K");
		b.setGestore("prodotto@admin.com");
		b.setPrezzo(11.0);
		b.setSconto(0);
		b.setQuantita_fornitura(100);
		b.setUltima_fornitura(java.sql.Date.valueOf("2021-02-20"));
		b.setData_uscita(java.sql.Date.valueOf("2021-12-22"));
		b.setNome("WWE2K22");
        
		prod.newInsert(b);
       
       IDatabaseTester tester = this.getDatabaseTester();
       
       ITable actualTable =  tester.getConnection().createDataSet().getTable("prodotto");       
      
     
      Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
		
	}*/
	
	public void testricercaPerNome() throws SQLException {
		ArrayList<ProdottoBean> a = prod.ricercaPerNome("FIFA");
		ArrayList<ProdottoBean> b = new ArrayList<ProdottoBean>();
		
		ProdottoBean b2 = new ProdottoBean();
		b2.setCodice_prodotto(1);
		b2.setFornitore("Sony");
		b2.setGestore("prodotto@admin.com");
		b2.setPrezzo(10.5);
		b2.setSconto(0);
		b2.setQuantita_fornitura(12);
		b2.setUltima_fornitura(java.sql.Date.valueOf("2020-12-20"));
		b2.setData_uscita(java.sql.Date.valueOf("2021-12-25"));
		b2.setNome("FIFA");
		b.add(b2);
		
		
		assertEquals(a.size(), b.size());
		
		for(int i=0; i<a.size();i++)
		{			
			assertEquals(a.get(i).getCodice_prodotto(),b.get(i).getCodice_prodotto());
			assertEquals(a.get(i).getFornitore(),b.get(i).getFornitore());
			assertEquals(a.get(i).getGestore(),b.get(i).getGestore());
			assertEquals(a.get(i).getPrezzo(),b.get(i).getPrezzo());
			assertEquals(a.get(i).getSconto(),b.get(i).getSconto());
			assertEquals(a.get(i).getQuantita_fornitura(),b.get(i).getQuantita_fornitura());
			assertEquals(a.get(i).getUltima_fornitura(),b.get(i).getUltima_fornitura());
			assertEquals(a.get(i).getData_uscita(),b.get(i).getData_uscita());
			assertEquals(a.get(i).getNome(),b.get(i).getNome());
		}
	}
	
	public void testricercaPerNomeVoid() throws SQLException{
		ArrayList<ProdottoBean> a = null;
		try {
		a = prod.ricercaPerNome("");
		}catch(NullPointerException e)
		{
			assertNull(a);
		}
	}
	public void testricercaPerNomeNotValid() throws NullPointerException
	{
		ArrayList<ProdottoBean> a = null;
		try {
		a = prod.ricercaPerNome("78");
		}catch(SQLException e)
		{
			assertNull(a);
		}
	}
	public void testricercaPerNomeNULL() throws SQLException{
		ArrayList<ProdottoBean> a = null;
		try {
			a = prod.ricercaPerNome(null);
			}catch(NullPointerException e)
			{
				assertNull(a);
			}
	}
	
	public void testricercaPerSottostringa() throws SQLException {
		ArrayList<ProdottoBean> a = prod.ricercaPerSottostringa("FIF");
		ArrayList<ProdottoBean> b = new ArrayList<ProdottoBean>();
		
		ProdottoBean b2 = new ProdottoBean();
		b2.setCodice_prodotto(1);
		b2.setFornitore("Sony");
		b2.setGestore("prodotto@admin.com");
		b2.setPrezzo(10.5);
		b2.setSconto(0);
		b2.setQuantita_fornitura(12);
		b2.setUltima_fornitura(java.sql.Date.valueOf("2020-12-20"));
		b2.setData_uscita(java.sql.Date.valueOf("2021-12-25"));
		b2.setNome("FIFA");
		b.add(b2);
		
		
		assertEquals(a.size(), b.size());
		
		for(int i=0; i<a.size();i++)
		{			
			assertEquals(a.get(i).getCodice_prodotto(),b.get(i).getCodice_prodotto());
			assertEquals(a.get(i).getFornitore(),b.get(i).getFornitore());
			assertEquals(a.get(i).getGestore(),b.get(i).getGestore());
			assertEquals(a.get(i).getPrezzo(),b.get(i).getPrezzo());
			assertEquals(a.get(i).getSconto(),b.get(i).getSconto());
			assertEquals(a.get(i).getQuantita_fornitura(),b.get(i).getQuantita_fornitura());
			assertEquals(a.get(i).getUltima_fornitura(),b.get(i).getUltima_fornitura());
			assertEquals(a.get(i).getData_uscita(),b.get(i).getData_uscita());
			assertEquals(a.get(i).getNome(),b.get(i).getNome());
		}
	}
	
	public void testricercaPerSottostringaVoid() throws SQLException{
		ArrayList<ProdottoBean> a = null;
		try {
		a = prod.ricercaPerSottostringa("");
		}catch(NullPointerException e)
		{
			assertNull(a);
		}
	}
	public void testricercaPerSottostringaNotValid() throws NullPointerException
	{
		ArrayList<ProdottoBean> a = null;
		try {
		a = prod.ricercaPerSottostringa("78");
		}catch(SQLException e)
		{
			assertNull(a);
		}
	}
	public void testricercaPerSottostringaNULL() throws SQLException{
		ArrayList<ProdottoBean> a = null;
		try {
			a = prod.ricercaPerSottostringa(null);
			}catch(NullPointerException e)
			{
				assertNull(a);
			}
	}
	
		/*public void testDoUpdatePresente() throws Exception {
		
		DataSource dataSource = new JdbcDataSource();
        ((JdbcDataSource) dataSource).setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/db/init/prodotto.sql'");
        ((JdbcDataSource) dataSource).setUser("root");
        ((JdbcDataSource) dataSource).setPassword("veloce123");
        
       IDataSet i = new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("resources/db/init/prodotto.xml"));
        
       ITable expectedTable = new FlatXmlDataSetBuilder()
               .build(OrdineDAOTest.class.getClassLoader().getResourceAsStream("resources/db/expected/prodottoupdate.xml"))
               .getTable("prodotto");
       

       ProdottoBean b1 = new ProdottoBean();
		b1.setCodice_prodotto(2);
		b1.setFornitore("Activision");
		b1.setGestore("prodotto@admin.com");
		b1.setPrezzo(15.5);
		b1.setSconto(0);
		b1.setQuantita_fornitura(102);
		b1.setUltima_fornitura(java.sql.Date.valueOf("2020-12-20"));
		b1.setData_uscita(java.sql.Date.valueOf("2020-12-22"));
		b1.setNome("PES");
		
      
		
		ProdottoDAO d = new ProdottoDAO(dataSource);
		
		d.doUpdate(b1);
       
		 IDatabaseTester tester = this.getDatabaseTester();
       
		 ITable actualTable =  tester.getConnection().createDataSet().getTable("prodotto");       
	       
	     Assertion.assertEquals(new SortedTable(expectedTable), new SortedTable(actualTable));
		
	}*/
		
		public void testGetMax() throws Exception {
			ProdottoBean a = prod.getMax();
			ProdottoBean b = new ProdottoBean();	
			
			 ProdottoBean b1 = new ProdottoBean();
				b1.setCodice_prodotto(2);
				b1.setFornitore("Activision");
				b1.setGestore("prodotto@admin.com");
				b1.setPrezzo(15.5);
				b1.setSconto(0);
				b1.setQuantita_fornitura(102);
				b1.setUltima_fornitura(java.sql.Date.valueOf("2020-12-20"));
				b1.setData_uscita(java.sql.Date.valueOf("2020-12-22"));
				b1.setNome("PES");
				
				System.out.println(a.getCodice_prodotto());
				
				assertEquals(a.getCodice_prodotto(),b.getCodice_prodotto());
				assertEquals(a.getFornitore(),b.getFornitore());
				assertEquals(a.getGestore(),b.getGestore());
				assertEquals(a.getPrezzo(),b.getPrezzo());
				assertEquals(a.getSconto(),b.getSconto());
				assertEquals(a.getQuantita_fornitura(),b.getQuantita_fornitura());
				assertEquals(a.getUltima_fornitura(),b.getUltima_fornitura());
				assertEquals(a.getData_uscita(),b.getData_uscita());
				assertEquals(a.getNome(),b.getNome());
		}

}
