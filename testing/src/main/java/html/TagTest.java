package com.darwinsys.html;

import static org.junit.Assert.*;

import java.io.CharArrayWriter;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;

public class TagTest {

	private CharArrayWriter sout;
	private PrintWriter pout;

	@Before
	public void setUp() throws Exception {
		sout = new CharArrayWriter();
		pout = new PrintWriter(sout);
	}

	@Test
	public void testDoTag() {
		Tag.dotag(pout, "Xyz");
		String string = sout.toString();
		assertEquals("<Xyz></Xyz>", string);
	}
	
	@Test
	public void testDoBodyTag() {
		Tag.doTag(pout, "Xyz", "a body is here");
		String string = sout.toString();
		assertEquals("<Xyz>a body is here</Xyz>", string);
	}

}
