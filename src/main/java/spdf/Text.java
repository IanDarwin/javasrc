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
		StringBuffer sb = new StringBuffer();
		sb.append("BT\n");
		sb.append("/F1 10 Tf\n");
		sb.append(x);
		sb.append(' ');
		sb.append(y);
		sb.append(" Td (");
		sb.append(text);	// TODO must substitute escaped characters
		sb.append(") Tj\n");
		sb.append("ET\n");
		master.addXref();
		startObj();
		master.println("<< /Length " + sb.length() + " >>");
		master.println("stream");
		master.print(sb);
		master.println("endstream");
		endObj();
	}

	/* trivial text test */
	public static void main(String argv[]) {
		PrintWriter pout = new PrintWriter(System.out);
		new Text(new PDF(pout), 100, 300, "Hello World").print();
	}
}
