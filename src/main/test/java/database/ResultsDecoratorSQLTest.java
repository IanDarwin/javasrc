package regress;
import javax.sql.rowset.WebRowSet;
import java.io.*;
import junit.framework.TestCase;

/**
 * ResultsDecoratorSQLTest
 * @version $Id$
 */
public class ResultsDecoratorSQLTest extends TestCase {
	protected static final String TEST_FILE = "rowdata.xml";
	
	protected WebRowSet testData;
	
	public static void main(String[] args) {
		//junit.textui.TestRunner.run(ResultsDecoratorSQLTest.suite());
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {

		Reader r = new FileReader(TEST_FILE);
		testData.readXml(r);

	}
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
}
