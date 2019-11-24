package regex;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the simple regex-based email validation.
 */
public class EmailValidatorTest {

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
	public void goodTests() {
		for (String test : goodTests) {
			assertTrue(test, EmailValidator.valid(test));
		}
	}

	@Test
	public void badTests() {
		for (String test : badTests) {
			assertFalse(test, EmailValidator.valid(test));
		}
	}
}
