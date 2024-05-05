package regex;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test the simple regex-based email validation.
 */
class EmailValidatorTest {

	private final static String[] goodTests = {
		"a@b.ca",
		"bernard_mugabe@illicit.gov.ke",
		"i.f.d.@darwin-systems.ca"
	};

	private final static String[] badTests = {
		"erewhon",
		"ian@2leftfeet.com",	// cannot begin with a number
		"ian@home",				// no dot
		"ian@home.notathome",		// TLD too long
	};

	@Test
	void goodTests() {
		for (String test : goodTests) {
			assertTrue(EmailValidator.valid(test), test);
		}
	}

	@Test
	void badTests() {
		for (String test : badTests) {
			assertFalse(EmailValidator.valid(test), test);
		}
	}
}
