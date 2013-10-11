package database.jdbc.regress;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.sql.rowset.WebRowSet;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.sun.rowset.WebRowSetImpl;

/**
 * ResultsDecoratorSQLTest
 * @version $Id$
 */
public class ResultsDecoratorSQLTest {
	protected static final String TEST_FILE = "rowsetdata.xml";
	
	protected WebRowSet testData;
	
	@Before
	public void setUp() throws Exception {

		testData= new WebRowSetImpl();
		final InputStream dataStream = getClass().getResourceAsStream(TEST_FILE);
		if (dataStream == null) {
			throw new RuntimeException("Could not load " + TEST_FILE);
		}
		Reader r = new InputStreamReader(dataStream);
		testData.readXml(r);
	}
	
	@Ignore("Missing XML data file contents!!") @Test
	public  void test1() throws Exception {
		testData.absolute(1);
		assertEquals("get data", "Ian", testData.getString("first_name"));
	}
	
}
