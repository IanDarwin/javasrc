package regex;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test the simple regex-based phone validation.
 */
class PhoneNumberValidatorTest {

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
	void goodTests() {
		for (String test : goodTests) {
			assertTrue(PhoneNumberValidator.valid(test), test);
		}
	}

	@Test
	void badTests() {
		for (String test : badTests) {
			assertFalse(PhoneNumberValidator.valid(test), test);
		}
	}
}
