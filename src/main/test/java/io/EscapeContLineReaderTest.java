package io;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

public class EscapeContLineReaderTest extends TestCase {
	final static String testData = 
		"Some lines of text to test the LineReader class.\n" +
		"This second line is continued with backslash.\\\n" +
		"This is a backslash continuation.\\\n" +
		"This is the end\n" +
		"This line should be the third output line.\n" +
		"EXPECT THE NEXT LINE TO THROW AN IOException\n" +
		"This tests for line ending in \\";
	
	final static String[] expect = {
		"Some lines of text to test the LineReader class.", 
		"This second line is continued with backslash. This is a backslash continuation. This is the end",
		"This line should be the third output line.", 
		"EXPECT THE NEXT LINE TO THROW AN IOException",
		"This tests for line ending in \\"
	};

	public void test1() {
		EscapeContLineReader is = new EscapeContLineReader(
			new StringReader(testData));
		String aLine;
		int i = 0;
		try {
			while ((aLine = is.readLine()) != null) {
				System.out.println(is.getLineNumber() + ": " + aLine);
				assertEquals(expect[i++], aLine);
			}
			is.close();
			fail("Did not throw expected IOException on last line of testData");
		} catch (IOException e) {
			if (i == expect.length - 1) {
				System.out.println("Caught expected exception on last line");
			} else {
				fail("Caught IOException on wrong line of file");
			}
		}
	}
}
