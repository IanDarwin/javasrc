package io;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

/**
 * Test the trsly-nmd IndentContLineReader
 */
public class IndentContLineReaderTest {

	protected final static String sampleTxt = 
		"From: ian today now\n" +
		"Received: by foo.bar.com\n" +
		"	at 12:34:56 January 1, 2000\n" +
		"X-Silly-Headers: Too Many\n" +
		"This line should be line 5.\n" +
		"Test more indented line continues from line 6:\n" +
		"    space indented.\n" +
		"	tab indented;\n" +
		"\n" +
		"This is line 10\n" + 
		"the start of a hypothetical mail/news message, \n" +
		"that is, it follows a null line.\n" +
		"	Let us see how it fares if indented.\n" +
		" also space-indented.\n" +
		"\n" +
		"How about text ending without a newline?";

	@Test
	public void testTwoLiner() throws IOException {
		String input = "Received: by foo.bar.com\n" +
				" at 12:34:56 January 1, 2000\n";
		String expected = "Received: by foo.bar.com at 12:34:56 January 1, 2000";
		try (IndentContLineReader is = new IndentContLineReader(
	        new StringReader(input))) {
		    assertEquals("2 liner", expected, is.readLine());
		}
	}
	
	/** This is really a demo method, not a test, sorry. */
	public void showReadingAMailMessageWithHeaders() throws IOException {
		// BEGIN main
		IndentContLineReader is = new IndentContLineReader(
			new StringReader(sampleTxt));
		String aLine;
		// Print Mail/News Header
		System.out.println("----- Message Header -----");
		while ((aLine = is.readLine()) != null && aLine.length() > 0) {
			System.out.println(is.getLineNumber() + ": " + aLine);
		}
		// Make "is" behave like normal BufferedReader
		is.setContinuationMode(false);
		System.out.println();
		// Print Message Body
		System.out.println("----- Message Body -----");
		while ((aLine = is.readLine()) != null) {
			System.out.println(is.getLineNumber() + ": " + aLine);
		}
		is.close();
		// END main
	}
}
