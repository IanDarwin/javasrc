package testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringTest {
	@Test
	public void testSubstringAtFront() {
		String origin = "Forescore and seven years ago";
		String expected = "Fore";
		String actual = origin.substring(0,4);
		assertEquals(actual, expected);
	}
}

