package testing;

import junit.framework.TestCase;

/** 
 * Demonstrate how you might use JUNit 3.8 to test the java.lang.Integer 
 * class (this is not to say that Sun doesn't test before they ship it - they do!!).
 * DO NOT MODERNIZE TO JUNIT 4 - this is meant to show the legacy way!
 */
public class IntegerTest38 extends TestCase {

	static { // JUnit 3.x didn't have an explicit class-wide setup method
		System.out.println("IntegerTest: In demo Class setup method");
    }

	public void setUp() {
		System.out.println("IntegerTest: In demo Instance setup method");
	}
	
	public void testAdd() {
		assertEquals(4, 2 + 2);
	}

	public void testDecode() throws Exception {
		int ret;
		ret = Integer.decode("-42").intValue();
		assertEquals(-42, ret);
		ret = Integer.decode("-0x42").intValue();
		assertEquals(-66, ret);
		try {
			Integer.decode("one two three");
			fail("IntegerTest: Did not throw expected NumberFormatException");
		} catch (NumberFormatException e) {
			System.out.println("IntegerTest: Caught expected exception");
		}
	}
}
