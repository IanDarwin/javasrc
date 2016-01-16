package oo;

import org.junit.*;
import static org.junit.Assert.*;

public class Clone1Test {

	Clone1 c;

	@Before
	public void setUp() { 
		c = new Clone1();
		c.x = 100;
		c.y = 200;
	}


	/** Test that clone() method returns OK. */
	@Test
	public void testClone() {
		Clone1 d = (Clone1)c.clone();
		// If we get here it didn't throw an exception.
		System.out.println("c=" + c);
		System.out.println("d=" + d);
		/* Test that data gets cloned correctly */
		assertEquals(c.x, d.x);
		assertEquals(c.y, d.y);
	}
}
