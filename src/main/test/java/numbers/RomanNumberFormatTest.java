package numbers;

import junit.framework.TestCase;

public class RomanNumberFormatTest extends TestCase {

	RomanNumberFormat nf;

	protected void setUp() throws Exception {
		super.setUp();
		nf = new RomanNumberFormat();
	}

	Object[][] data = {
			{ 1, "I" },
			{ 42, "XLII" },
			{ 678, "DCLXXVIII" },
			{ 1999, "MCMXCIX" },
			{ 2000, "MM" },
			{ 2001, "MMI" },
			{ 3999, "MMMCMXCIX" },
	};

	public final void testManyFormatLong() {
		try {
			nf.format(0);
			fail("Romans did not use Zero");
		} catch (NumberFormatException e) {
			// OK
		}
		for (Object[] o : data) {
			assertEquals(o[1], nf.format(o[0]));
		}
		try {
			nf.format(4000);
			fail("Failed to reject number >= 4000");
		} catch (NumberFormatException e) {
			// OK
		}
	}

	public void testParseObject() {
		try {
			nf.parseObject("XIV", null);
			fail("Did not refuse to parseObject");
		} catch (IllegalArgumentException e) {
			// OK
		}
	}

	public void testAgain() {
		Integer num1 = nf.parseObject("LCX", null);
		System.out.println(num1);
		Integer num2 = nf.parseObject("XX", null);
		System.out.println(num2);
		int newVal = num1.intValue() * num2.intValue();
		System.out.println(newVal);
		String newString = nf.format(newVal);
		System.out.println(newString);
	}
}
