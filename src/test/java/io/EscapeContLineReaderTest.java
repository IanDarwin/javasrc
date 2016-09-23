package io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.*;

public class EscapeContLineReaderTest  {
	final static String testData = 
		"Some lines of text to test the LineReader class.\n" +
		"This second line is continued with backslash.\\\n" +
		"This is a backslash continuation.\\\n" +
		"This is the end\n" +
		"This line should be the third output line.\n";
	
	final static String[] expect = {
		"Some lines of text to test the LineReader class.", 
		"This second line is continued with backslash. This is a backslash continuation. This is the end",
		"This line should be the third output line.", 	
	};

	@Test
	public void test1() throws IOException {
		EscapeContLineReader is = new EscapeContLineReader(
			new StringReader(testData));
		String aLine;
		int i = 0;

			while ((aLine = is.readLine()) != null) {
				System.out.println(is.getLineNumber() + ": " + aLine);
				assertEquals(expect[i++], aLine);
			}
			is.close();
	}
	
	@Test(expected=IOException.class)
	public void testBad() throws Exception {
		String testData = "This tests for line ending in \\";
		EscapeContLineReader is = new EscapeContLineReader(
				new StringReader(testData));
		is.readLine();
		is.readLine();	// expect it to throw
		is.close();
	}
}
