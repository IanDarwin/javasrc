package io;

import java.io.PrintStream;

/**
 * Multiple lines output from one method.
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class GoodNewline {
	// tag::main[]
	String myName;
	public static void main(String[] argv) {
		GoodNewline jack = new GoodNewline("Jack Adolphus Schmidt, III");
		jack.print(System.out);
	}

	protected void print(PrintStream out) {
		out.println(toString());	// classname and hashcode
		out.println(myName);		// print name  on next line
	}

	// end::main[]
	/* Constructor */
	public GoodNewline(String s) {
		myName = s;
	}
}
