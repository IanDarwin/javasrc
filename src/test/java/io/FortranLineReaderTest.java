package io;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

/**
 * @author ian
 */
public class FortranLineReaderTest extends TestCase {
	public static void main(String[] args) {
		//junit.textui.TestRunner.run(FortranLineReaderTest.suite());
	}

	protected final static String sampleTxt = 
		"C      This is a comment.\n" +
		"12345xdouble precision x = " +
		"      sqrt(3.14159)\n" +
		"      This should be non-continued.\n" +
		"  3   This is statement 3 and line 3.";

	public void testReading() throws IOException {
		FortranLineReader is = new FortranLineReader(
			new StringReader(sampleTxt));
		String aLine;
		while ((aLine = is.readLine()) != null) {
			if (is.hasStatementNumber())
				System.out.println("\tStatement number: " +
					is.getStatementNumber());
			System.out.println(is.getLineNumber() + ": " + aLine);
		}
		is.close();
	}
}
