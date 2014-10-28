package strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreditCardDisguiseTest {

	@Test
	public void testNormal() {
		assertEquals("XXXXXXXXXXXX1234",
				CreditCardDisguise.disguise("9876543210981234"));
	}
	
	@Test
	public void testBoundary() {
		assertEquals("XXXXXXXXXXXXXXXX5678",
				CreditCardDisguise.disguise("11112222333344445678"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTooShort() {
		CreditCardDisguise.disguise("12345");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testTooLong() {
		CreditCardDisguise.disguise("11112222333344445555+");
	}
}
