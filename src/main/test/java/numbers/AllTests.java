package numbers;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * JUnit test suite for all numbers tests.
 * @author ian
 */
public class AllTests {
	public static void main(String[] args) {
	}
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for 'numbers' directory");
		//$JUnit-BEGIN$
		suite.addTestSuite(BigNumCalcTest.class);
		suite.addTestSuite(ComplexTest.class);
		// ScaledNumberFormat is not supported; need to sync with
		// latest OpenBSD C version
		// suite.addTestSuite(ScaledNumberFormatTest.class);
		//$JUnit-END$
		return suite;
	}
}
