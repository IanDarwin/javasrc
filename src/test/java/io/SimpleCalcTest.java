package io;

import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;

import junit.framework.TestCase;

public class SimpleCalcTest extends TestCase {

	final String TEST = "// This file is a test of the SimpleCalc class\n" +
		"2 4 + = 3 / =	// should print 6, 2\n" +
		"22 7 / =		// should print 3.141592857...\n";

	final String EXPECT = "6.0\n" +
		"2.0\n" +
		"3.142857142857143\n";

	public void testIt() throws Exception {
		BufferedReader is = new BufferedReader(
			new StringReader(TEST));
		StringWriter fluffy = new StringWriter();
		PrintWriter ps = new PrintWriter(fluffy);

		SimpleCalcStreamTok sc = new SimpleCalcStreamTok(is, ps);
		sc.doCalc();
		assertEquals(EXPECT, fluffy.getBuffer().toString().replaceAll("\\\r", ""));
	}
}
