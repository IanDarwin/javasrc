package oo;

import junit.framework.*;

public class Clone1Test extends TestCase {

	Clone1 c;

	public void setUp() { 
		c = new Clone1();
		c.x = 100;
		c.y = 200;
	}

	Clone1 d;

	/** Test that clone() method returns OK. */
	public void testClone() {
		d = (Clone1)c.clone();
		// If we get here it didn't throw an exception.
		System.out.println("c=" + c);
		System.out.println("d=" + d);
		/* Test that data gets cloned correctly */
		assertEquals(c.x, d.x);
		assertEquals(c.y, d.y);
	}

	/** Constructor needed by junit */
	public Clone1Test(String name) { super(name); }
}
