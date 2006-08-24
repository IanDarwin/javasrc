package strings;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * JUnit tests for some of my String demos.
 */
public class AllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(AllTests.class);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for default package");
		//$JUnit-BEGIN$
		suite.addTestSuite(EnTabTest.class);
		suite.addTestSuite(SoundexTest.class);
		suite.addTestSuite(StringAlignTest.class);
		suite.addTestSuite(TabsTest.class);
		suite.addTestSuite(DeTabTest.class);
		//$JUnit-END$
		return suite;
	}
}
