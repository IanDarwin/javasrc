package com.darwinsys.spdf;

import java.io.*;
import java.util.*;

/** Represent one Page of a PDF file. */
public class Page extends PDFDict {
	protected ArrayList objects = new ArrayList();

	public Page(PDF m) {
		super(m);
		dict.put("Type", "/Page");
		dict.put("Parent", "4 0 R");
		dict.put("Resources", "<< /Font << /F1 8 0 R >> /ProcSet 7 0 R >>");
		dict.put("MediaBox", "[0 0 612 792]");
		dict.put("Contents", "6 0 R");
		
	}

	public void add(PDFObject po) {
		objects.add(po);
	}

	protected void print() {
		super.print();
		for (int i=0; i<objects.size(); i++) {
			((PDFObject)objects.get(i)).print();
		}
	}
}
