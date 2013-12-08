package oo;

import junit.framework.*;

/** Some JUnit test cases for the Singleton demo.
 */
public class SingletonTest extends TestCase {

	Singleton d1, d2;

	/** setup method */
	public void setUp() {
		d1 = Singleton.getInstance();
		d2 = Singleton.getInstance();
	}

	public void testSingleness() { 
		assertTrue(d1 == d2);
	}

	public void testCorrectClass() {
		assertTrue(d1 instanceof Singleton);
	}

	public void testDemoMethod() {
		assertEquals(d1.demoMethod(), "demo");
	}
}
