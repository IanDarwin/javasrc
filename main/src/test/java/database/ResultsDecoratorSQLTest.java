package database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * ResultsDecoratorSQLTest
 */
class ResultsDecoratorSQLTest {
	protected static final String TEST_FILE = "rowsetdata.xml";
	
	protected WebRowSet testData;

	@BeforeEach
	void setUp() throws Exception {

		RowSetFactory rsFactory = RowSetProvider.newFactory();
	    testData = rsFactory.createWebRowSet();
		final InputStream dataStream = getClass().getResourceAsStream(TEST_FILE);
		if (dataStream == null) {
			throw new RuntimeException("Could not load " + TEST_FILE);
		}
		Reader r = new InputStreamReader(dataStream);
		testData.readXml(r);
	}

	@Disabled("Missing XML data file contents!!")
	@Test
	void test1() throws Exception {
		testData.absolute(1);
		assertEquals("Ian", testData.getString("first_name"), "get data");
	}
}
