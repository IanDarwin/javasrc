package oo;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

// BEGIN main
/** Some JUnit test cases for EqualsDemo.
 * Writing a full set is left as "an exercise for the reader".
 */
public class EqualsDemoTest {

	/** an object being tested */
	EqualsDemo d1;
	/** another object being tested */
	EqualsDemo d2;

	/** Method to be invoked before each test method */
	@Before
	public void setUp() {
		d1 = new EqualsDemo();
		d2 = new EqualsDemo();
	}

	@Test
	public void testSymmetry() { 
		assertTrue(d1.equals(d1));
	}

	@Test
	public void testSymmetric() {
		assertTrue(d1.equals(d2) && d2.equals(d1));
	}

	@Test
	public void testCaution() {
		assertTrue(!d1.equals(null));
	}
}
// END main
