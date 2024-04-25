package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

class CheckSumTest {

	@Test
	void checkSumEmptyString() {
		assertEquals(0, CheckSum.process(new BufferedReader(new StringReader(""))));
	}

	@Test
	void checkSumOneCharString() {
		assertEquals(0x42, CheckSum.process(new BufferedReader(new StringReader("\u0042"))));
	}

	@Test
	void checkSumNormalString() {
		int expected = 2874;
		String input = "Hello world\nOh, and goodbye, too!";
		StringReader chr = new StringReader(input);
		BufferedReader is = new BufferedReader(chr);
		int actual = CheckSum.process(is);
		assertEquals(expected, actual, "cksum test");
	}
}
