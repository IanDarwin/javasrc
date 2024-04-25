package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

class UndentTest {

	/*
	 * Test normal lines, lines with fewer, and with more, than "n" spaces.
	 */
	@Test
	void basic() throws Exception {
		String data = 
			"        Line one\n" +
			"        Line two\n" +
			"     Line 3\n" +
			"         Line 4\n" +
			"        \n";
		String expect = 
			"Line one\n" +
			"Line two\n" +
			"Line 3\n" +
			" Line 4\n" +
			"\n";
		System.out.println("Start basic test");
		assertEquals(expect, innerTest(8, data).replaceAll("\\\r", ""));
	}

	private String innerTest(int n, String data) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		PrintStream oldOut = System.out;
		try {
			System.setOut(new PrintStream(bo));
			new Undent(n).process(new BufferedReader(new StringReader(data)));
			bo.close();
			return bo.toString();
		} finally {
			System.setOut(oldOut);
		}
	}

	/*
	 * Test method for 'strings.Undent.process(BufferedReader)'
	 */
	@Test
	void shortLines() throws Exception {
		String data = 
			"     Line 3\n" +
			" \n" +
			"\n";
		String expect = 
			"Line 3\n" +
			"\n" +
			"\n";
		System.out.println("Start short lines test");
		assertEquals(expect, innerTest(8, data).replaceAll("\\\r", ""));
	}
}
