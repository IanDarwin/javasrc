package strings;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreditCardDisguiseTest {

	@Test
	public void test() {
		assertEquals("XXXXXXXXXXXX1234",
				CreditCardDisguise.disguise("9876543210981234"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testFail() {
		CreditCardDisguise.disguise("12345");
	}

}
