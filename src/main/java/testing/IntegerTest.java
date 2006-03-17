package testing;

import junit.framework.TestCase;

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
