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

	@Test(expected=NumberFormatException.class)
	public final void testManyFormatLong() {	
		nf.format(0);
		fail("Romans did not use Zero");
	}
	
	@Test(expected=NumberFormatException.class)
	public void testTooBig() {
		nf.format(4000);
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
