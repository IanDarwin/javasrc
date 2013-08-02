package numbers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RomanNumberFormatTest {

	RomanNumberFormat nf;

	@Before
	public void setUp() throws Exception {
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

	@Test(expected=NumberFormatException.class)
	public final void testManyFormatLong() {	
		nf.format(0);
		fail("Romans did not use Zero");
	}
	
	@Test
	public void testMore() {
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

	@Test
	public void testThereThereAndBackAgain() {
		Long num1 = (Long) nf.parseObject("LCX", null);
		assertEquals(new Long(160), num1);
		Long num2 = (Long) nf.parseObject("XX", null);
		assertEquals(new Long(20), num2);
		long newVal = num1.intValue() * num2.intValue();
		System.out.println(newVal);
		String newString = nf.format(newVal);
		System.out.println(newString);
		Long num3 = (Long)nf.parseObject(newString, null);
		assertEquals(new Long(newVal), num3);
	}
}
