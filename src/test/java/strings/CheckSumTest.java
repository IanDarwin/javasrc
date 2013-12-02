package strings;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Test;
import static org.junit.Assert.*;

public class CheckSumTest {
	
	@Test
	public void testCheckSumEmptyString() {
		assertEquals(0, CheckSum.process(new BufferedReader(new StringReader(""))));
	}

	@Test
	public void testCheckSumOneCharString() {
		assertEquals(0x42, CheckSum.process(new BufferedReader(new StringReader("\u0042"))));
	}

	@Test
	public void testCheckSumNormalString() {
		int expected = 2874;
		String input = "Hello world\nOh, and goodbye, too!";
		StringReader chr = new StringReader(input);
		BufferedReader is = new BufferedReader(chr);
		int actual = CheckSum.process(is);
		assertEquals("cksum test", expected, actual);
	}
}
