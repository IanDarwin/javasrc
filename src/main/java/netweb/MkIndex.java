import java.io.*;
import java.util.*;
import com.darwinsys.util.*;

/** mkindex -- make an index.html for a Java Source directory
 *
 * REQUIRES JDK1.2 OR LATER FOR SORT!!
 *
 * Original version was an Awk script that used "ls" to get
 * the list of files, grep out .class and javadoc output files, |sort.
 *
 * Translated from awk to Java in October, 1997 to use java.io.File methods
 *
 * Revised June 1998 to use Collections.sort() instead of running external sort.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @Version $Id$
 */
public class MkIndex {
	/** The output file that we create */
	public static final String OUTPUTFILE = "index_byname.html";
	/** The main output stream */
	PrintWriter out;
	/** The background color for the page */
	public static final String BGCOLOR="#33ee33";
	/** The File object, for directory listing. */
	File dirFile;

	/** Make an index */
	public static void main(String[] args) throws IOException {
		MkIndex mi = new MkIndex();
		String inDir = args.length > 0 ? args[0] : ".";
		mi.open(inDir, OUTPUTFILE);		// open files
		mi.BEGIN();		// print HTML header
		mi.process();		// do bulk of work
		mi.END();		// print trailer.
		mi.close();		// close files
	}

	void open(String dir, String outFile) {
		dirFile = new File(dir);
		try {
			out = new PrintWriter(new FileWriter(outFile));
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/** Write the HTML headers */
	void BEGIN() throws IOException {
		println("<HTML>");
		println("<HEAD>");
		println("    <META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=iso-8859-1\">");
		println("    <META NAME=\"GENERATOR\" CONTENT=\"Java MkIndex\">");
		println("    <TITLE>Ian Darwin's Java Programming Online Source Examples</TITLE>");
		println("</HEAD>");
		println("<BODY BGCOLOR=\"" + BGCOLOR + "\">");
		println("<H1>Ian Darwin's Java Programming Online Source Examples</H1>");
		if (new File("about.html").exists()) {
			FileIO.copyFile("about.html", out, false);
		} else {
			println("<P>The following files are online.");
			println("Some of these files are still experimental!</P>");
			println("<P>Most of these files are Java source code.");
			println("If you load an HTML file from here, the applets will not run!");
			println("The HTML files must be saved to disk and the applets compiled,");
			println("before you can run them!");
		}
		println("<P>All files are Copyright &copy;: All rights reserved.");
		println("See the accompanying <A HREF=\"legal-notice.txt\">Legal Notice</A> for conditions of use.");
		println("May be used by readers of my Java Cookbook for educational purposes, and for commercial use if certain conditions are met.");
		println("</P>");
		println("<HR>");
	}

	/** Array of letters that exist
	 * XXX fold case here so dont get f and F as distinct entries!
	 * This only works for ASCII characters (8-bit chars).
	 */
	boolean[] exists = new boolean[255];

	/** Vector for temporary storage, and sorting */
	ArrayList vec = new ArrayList();

	/** Do the bulk of the work */
	void process() throws IOException {

		System.out.println("Start PASS ONE -- from directory to Vector...");
		String[] fl = dirFile.list();
		for (int i=0; i<fl.length; i++) {
			String fn = fl[i];
			if (fn.startsWith("index")) { // well have no self-reference here!
				System.err.println("Ignoring " + fn);
				continue;
			} else if (fn.endsWith(".bak")) {		// delete .bak files
				System.err.println("DELETING " + fn);
				new File(fn).delete();
				continue;
			} else if (fn.equals("CVS")) {		// Ignore CVS subdirectories
				continue;						// dont mention it
			} else if (fn.charAt(0) == '.') {	// UNIX dot-file
				continue;
			} else if (fn.endsWith(".class")) {	// nag about .class files
				System.err.println("Ignoring " + fn);
				continue;
			} else if (new File(fn).isDirectory()) {
				vec.add(fn + "/");
			} else
				vec.add(fn);
			exists[fn.charAt(0)] = true;	// only after chances to continue
		}

		System.out.println("Writing the Alphabet Navigator...");
		for (char c = 'A'; c<='Z'; c++)
			if (exists[c])
				print("<A HREF=\"#" + c + "\">" + c + "</A> ");

		// ... (and the beginning of the HTML Unordered List...)
		println("<UL>");

		System.out.println("Sorting the Vector...");
		Collections.sort(vec, String.CASE_INSENSITIVE_ORDER);

		System.out.println("Start PASS TWO -- from Vector to " +
			OUTPUTFILE + "...");
		String fn;
		Iterator it = vec.iterator();
		while (it.hasNext()) {
			fn = (String)it.next();
			// Need to make a link into this directory.
			// IF there is a descr.txt file, use it for the text
			// of the link, otherwise, use the directory name.
			// But, if there is an index.html or index.html file,
			// make the link to that file, else to the directory itself.
			if (fn.endsWith("/")) {	// directory
				String descr = null;
				if (new File(fn + "descr.txt").exists()) {
					descr = com.darwinsys.util.FileIO.readLine(fn + 
						"descr.txt");
				};
				if (new File(fn + "index.html").exists())
					mkDirLink(fn+"index.html", descr!=null?descr:fn);
				else if (new File(fn + "index.htm").exists())
						mkDirLink(fn+"index.htm", descr!=null?descr:fn);
				else
					mkLink(fn, descr!=null?descr:fn + " -- Directory");
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
