package oo;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/** Some JUnit test cases for EqualsDemo.
 * Writing a full set is left as "an exercise for the reader".
 * Run as: $ java junit.textui.TestRunner EqualsDemoTest
 * @version $Id$
 */
public class EqualsDemoTest {

	/** an object being tested */
	EqualsDemo d1;
	/** another object being tested */
	EqualsDemo d2;

	/** init() method */
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
