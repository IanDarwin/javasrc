package JDBC.regress;

import java.io.FileReader;
import java.io.Reader;

import javax.sql.rowset.WebRowSet;

import com.sun.rowset.WebRowSetImpl;

import junit.framework.TestCase;

/**
 * ResultsDecoratorSQLTest
 * @version $Id$
 */
public class ResultsDecoratorSQLTest extends TestCase {
	protected static final String TEST_FILE = "rowsetdata.xml";
	
	protected WebRowSet testData;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {

		testData= new WebRowSetImpl();
		Reader r = new FileReader(TEST_FILE);
		testData.readXml(r);

	}
	
	public  void test1() throws Exception {
		testData.absolute(1);
		assertEquals("get data", "Ian", testData.getString("first_name"));
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}
