package com.darwinsys.spdf;

import java.io.*;
import java.util.*;

/** Represent one Text object in a PDF file. */
public class Text extends PDFObject {
	protected int x, y;
	protected String text;

	public Text(PDF m, int x, int y, String s) {
		super(m);
		this.x = x;
		this.y = y;
		text = s;
	}

	public void print() {
		throw new IllegalStateException("print() called on a Text obj");
	}

	public void print(StringBuffer sb) {
		sb.append(x);
		sb.append(' ');
		sb.append(y);
		sb.append(" m (");
		sb.append(text);	// TODO must substitute escaped characters
		sb.append(") Tj\n");
	}
}
