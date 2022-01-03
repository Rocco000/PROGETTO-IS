/**
 * 
 */
package acquisto;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;




/**
 * @author Utente
 *
 */
public class CorriereEspressoDAOTest extends DataSourceBasedDBTestCase{
	 	private DataSource ds;
	    private CorriereEspressoDAO cre;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
        ds = this.getDataSource();
        cre = new CorriereEspressoDAO(ds);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		 super.tearDown();
	}

	@Override
	protected DataSource getDataSource() {
		JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;init=runscript from 'classpath:resources/db/init/schema.sql'");
        dataSource.setUser("root");
        dataSource.setPassword("veloce123");
        return dataSource;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(this.getClass().getClassLoader().getResourceAsStream("resources/db/init/init.xml"));
	}
	
	 @Override
	    protected DatabaseOperation getSetUpOperation() {
	        return DatabaseOperation.REFRESH;
	    }

	 @Override
	    protected DatabaseOperation getTearDownOperation() {
	        return DatabaseOperation.DELETE_ALL;
	    }
	 
	 	/**
		 * Test method for {@link acquisto.CorriereEspressoDAO#allElements(java.lang.String)}.
	 	 * @throws SQLException 
		 */
		@Test
		public void testAllElements() throws SQLException {
			
			ArrayList<CorriereEspressoBean> a = cre.allElements("nome asc");
			ArrayList<CorriereEspressoBean> b = new ArrayList<CorriereEspressoBean>();
			CorriereEspressoBean bean = new CorriereEspressoBean();
			
			bean.setNome("bartolini");
			bean.setSito("bartolini.com");
			
			b.add(bean);
			
			CorriereEspressoBean bean2 = new CorriereEspressoBean();
			
			bean2.setNome("ups");
			bean2.setSito("ups.com");
			
			b.add(bean2);
			
			
			CorriereEspressoBean bean3 = new CorriereEspressoBean();
			bean3.setNome("dhl");
			bean3.setSito("dhl.com");
			
			b.add(bean3);
			
			
			CorriereEspressoBean bean4 = new CorriereEspressoBean();
			bean4.setNome("lol");
			bean4.setSito("lol.com");
			
			b.add(bean4);
			
			assertEquals(a.toString(),b.toString());
			
			
		}



}
