package testing;

import junit.framework.TestCase;


/** Demonstrate how you might use JUNit 3.8 to test the java.lang.Integer 
 * class (this is not to say that Sun doesn't test before they ship it - they do!!).
 */
public class IntegerTest extends TestCase {
	public void testDecode() throws Exception {
		int ret;
		ret = Integer.decode("-42").intValue();
		assertEquals(-42, ret);
		ret = Integer.decode("-0x42").intValue();
		assertEquals(-66, ret);
		try {
			Integer.decode("one two three");
			fail("Did not throw expected NumberFormatException");
		} catch (NumberFormatException e) {
			System.out.println("Caught expected exception");
		}
	}
}
