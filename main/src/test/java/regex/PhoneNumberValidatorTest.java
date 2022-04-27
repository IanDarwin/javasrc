package regex;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test the simple regex-based phone validation.
 */
public class PhoneNumberValidatorTest {

	private final static String[] goodTests = {
		"777 777 7777",
		"+1 777 777 7777",
		"(777) 777 7777",
		"+1(777) 777 7777",
	};

	private final static String[] badTests = {
		"777--777-7777",		// double dash
		"777-777-77777",		// last too long
		// "(777 777-7777"		// XXX mismatched parens
		"777-777-7777 x42"		// Junk at end.
	};

	@Test
	public void goodTests() {
		for (String test : goodTests) {
			assertTrue(test, PhoneNumberValidator.valid(test));
		}
	}

	@Test
	public void badTests() {
		for (String test : badTests) {
			assertFalse(test, PhoneNumberValidator.valid(test));
		}
	}
}
