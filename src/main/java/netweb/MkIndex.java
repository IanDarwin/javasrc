import java.io.*;
import java.util.*;

/** mkindex -- make an index.html for a Java Source directory
 *
 * REQUIRES JDK1.2 OR LATER FOR SORT!!
 *
 * Original version was an Awk script that used "ls" to get
 * the list of files, grep out .class and javadoc output files, |sort.
 *
 * Translated from awk to Java in October, 1997 to use java.io.File methods
 *
 * Revised June 1998 to use Collections.sort() instead of running unix sort.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @Version $Id$
 */
public class MkIndex {
	/** The output file that we create */
	public static final String OUTPUTFILE = "index.html";
	/** The main output stream */
	PrintWriter out;
	/** The background color for the page */
	public static final String BGCOLOR="#33ee33";

	/** Make an index */
	public static void main(String av[]) {
		MkIndex mi = new MkIndex();
		mi.open();		// open files
		mi.BEGIN();		// print HTML header
		mi.process();		// do bulk of work
		mi.END();		// print trailer.
		mi.close();		// close files
	}

	void open() {
		try {
			out = new PrintWriter(new FileWriter(OUTPUTFILE));
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/** Write the HTML headers */
	void BEGIN() {
		println("<HTML>");
		println("<HEAD>");
		println("    <META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">");
		println("    <META NAME=\"GENERATOR\" CONTENT=\"Java MkIndex\">");
		println("    <TITLE>Learning Tree Java Programming Online Source Examples</TITLE>");
		println("</HEAD>");
		println("<BODY BGCOLOR=\"" + BGCOLOR + "\">");
		println("<H1>Learning Tree Java Programming Online Source Examples</H1>");
		println("<P>The following files are online. Most are described");
		println("in the Course Notes by file name.");
		println("Files not so mentioned are experimental!</P>");
		println("<P>Most of these files are Java source code.");
		println("If you load an HTML file from here, the applets will not run!");
		println("The HTML files must be saved to disk and the applets compiled,");
		println("before you can run them!");
		println("<P>All files are Copyright &copy;: All rights reserved.");
		println("See the accompanying <A HREF=\"legal-notice.txt\">Legal Notice</A> for conditions of use");
		println("(may be used by students of Learning Tree Java Courses for educational purposes, and for commercial use if certain conditions are met).");
		println("</P>");
		println("<HR>");
	}

	/** Array of letters that exist
	 * XXX fold case here so don't get f and F as distinct entries!
	 */
	boolean[] exists = new boolean[255];

	/** Vector for temporary storage, and sorting */
	Vector vec = new Vector();

	/** Do the bulk of the work */
	void process() {

		System.out.println("Start PASS ONE -- from directory to Vector...");
		String[] fl = new File(".").list();
		for (int i=0; i<fl.length; i++) {
			String fn = fl[i];
			if (fn.equals(OUTPUTFILE)) { // we'll have no self-reference here!
				System.err.println("Ignoring " + OUTPUTFILE);
				continue;
			} else if (fn.endsWith(".bak")) {		// delete .bak files
				System.err.println("DELETING " + fn);
				new File(fn).delete();
				continue;
			} else if (fn.equals("CVS")) {		// Ignore CVS subdirectories
				continue;						// don't mention it
			} else if (fn.endsWith(".class")) {	// nag about .class files
				System.err.println("Ignoring " + fn);
				continue;
			} else if (new File(fn).isDirectory()) {
				vec.addElement(fn + "/");
			} else
				vec.addElement(fn);
			exists[fn.charAt(0)] = true;	// only after chances to continue
		}

		System.out.println("Writing the Alphabet Navigator...");
		for (char c = 'A'; c<='Z'; c++)
			if (exists[c])
				print("<A HREF=\"#" + c + "\">" + c + "</A> ");

		// ... (and the beginning of the HTML Unordered List...)
		println("<UL>");

		System.out.println("Sorting the Vector...");
		Collections.sort(vec, new StringIgnoreCaseComparator());

		System.out.println("Start PASS TWO -- from Vector to index.htm...");
		String fn;
		for (int i=0; i<vec.size(); i++) {
			fn = (String)vec.elementAt(i).toString();
			// println(fn);
			if (fn.endsWith("/")) {	// directory
				if (new File(fn + "index.html").exists())
					mkDirLink(fn+"index.html", fn);
				else if (new File(fn + "index.htm").exists())
						mkDirLink(fn+"index.htm", fn);
				else
					mkLink(fn, fn + " -- Directory");
			} else // file
				mkLink(fn, fn);
		}
		System.out.println("*** process - ALL DONE***");
	}

	/** Keep track of each letter for #links */
	boolean done[] = new boolean[255];

	void mkLink(String href, String descrip) {
		print("<LI>");
		char c = href.charAt(0);
		if (!done[c]) {
			print("<A NAME=\"" + c + "\">");
			done[c] = true;
		}
		println("<A HREF=\"" + href + "\">" + descrip + "</A>");
	}

	void mkDirLink(String index, String dir) {
		// XXX Open the index and look for TITLE lines!
		mkLink(index, dir + " -- Directory");
	}

	/** Write the trailers and a signature */
	void END() {
		System.out.println("Finishing the HTML");
		println("</UL>");
		println("<P>This file generated by ");
		print("<A HREF=\"MkIndex.java\">MkIndex</A>, a Java program, at ");
		println(new Date().toString());
		println("</P>");
		println("</BODY>");
		println("</HTML>");
	}

	/** Close open files */
	void close() {
		System.out.println("Closing output files...");
		if (out != null)
			out.close();
	}

	/** Convenience routine for out.print */
	void print(String s) {
		out.print(s);
	}

	/** Convenience routine for out.println */
	void println(String s) {
		out.println(s);
	}

}
