package com.darwinsys.spdf;

import java.io.*;
import java.util.*;

/** Represent one Page of a PDF file. */
public class Page extends PDFObject {
	protected ArrayList objects = new ArrayList();

	public Page(PDF m) {
		super(m);
	}

	public void add(PDFObject po) {
		objects.add(po);
	}

	protected void print() {
		// do something.
	}
}
