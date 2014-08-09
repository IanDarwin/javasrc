package database;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * ResultsDecoratorSQLTest
 */
public class ResultsDecoratorSQLTest {
	protected static final String TEST_FILE = "rowsetdata.xml";
	
	protected WebRowSet testData;
	
	@Before
	public void setUp() throws Exception {

		RowSetFactory rsFactory = RowSetProvider.newFactory();
	    testData = rsFactory.createWebRowSet();
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
