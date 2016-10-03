package netweb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.SortedMap;
import java.util.TreeMap;

import com.darwinsys.io.FileIO;

/** MkIndex -- make a static index.html for a Java Source directory
 * <p>
 * Works through one or more directories, finding all the java files,
 * and making links.
 *
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class MkIndex {

	private boolean verbose = false;

	/** Optional input file - about */
	public static final String ABOUT_FILE_NAME = "About.html";
	/** Optional input file - copyright / license info */
	public static final String COPYRIGHT_FILE_NAME = "Copyright.html";
	/** The output file that we create */
	public static final String OUTPUTFILE = "index-byname.html";

	/** The string for TITLE and H1 */
	public static final String TITLE =
		"Ian Darwin's Java Cookbook: Source Code: By Name";
	/** The main output stream */
	PrintWriter out;
	/** The background color for the page */
	public static final String BGCOLOR="#33ee33";

	/** Make an index */
	public static void main(String[] args) throws IOException {
		MkIndex mi = new MkIndex();
		mi.setWriter(new PrintWriter(new FileWriter(OUTPUTFILE)));
		mi.begin();		// print HTML header
		System.out.println("** Start Pass One **");
		if (args.length > 0) {
			for (int i=0; i<args.length; i++)
				mi.process(new File(args[i]));	// "We do ALL the work..."
		} else {
			mi.process(new File("."));
		}
		mi.writeNavigator();	// Write navigator
		mi.writeList();	// Write huge list of files
		mi.end();		// print trailer.
		mi.close();		// close files
	}

	void setWriter(PrintWriter aWriter) {
		out = aWriter;
	}

	/** Write the HTML headers */
	void begin() throws IOException {
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		println();

		println("<head>");
		println("	<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'></meta>");
		println("	<meta name='Generator' content='Java MkIndex'></meta>");
		println("	<link rel='stylesheet' type='text/css' href='/stylesheet.css' title='style'></link>");
		println("	<title>" + TITLE + "</title>");
		println("</head>");
		println();
		println("<body style=\"background:" + BGCOLOR + ";\">");
		println("<h1>" + TITLE + "</h1>");
		if (new File(ABOUT_FILE_NAME).exists()) {
			FileIO.copyFile(ABOUT_FILE_NAME, out, false);
		} else {
			println("<p>The following files are online.</p>");
		}
		if (new File(COPYRIGHT_FILE_NAME).exists()) {
			FileIO.copyFile(COPYRIGHT_FILE_NAME, out, false);
		} else {
			println("<p>All files Copyright (c): All rights reserved.");
		}
		println("<hr></hr>");
	}

	/** Array of letters that one or more files begin with. Should
	 * fold case here so don't get f and F as distinct entries!
	 * This only works for ASCII characters (8-bit chars).
	 */
	boolean[] exists = new boolean[255];

	/** List for temporary storage, and sorting */
	SortedMap<String,String> list = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);

	/** Return true if a filename should be ignored. */
	boolean ignorable(String name) {
		if (name.startsWith("index") ||
			name.endsWith(".class") ||
			name.endsWith(".jar") ||
			name.endsWith(".bak")) {
			if (verbose) {
				System.err.println("Ignoring " + name);
			}
			return true;
		} else if (name.equals("CVS")) {		// Ignore CVS subdirectories
			return true;						// don't mention it
		} else if (name.charAt(0) == '.' && name.length() > 1) {// UNIX dot-file
			return true;
		}
		return false;
	}

	/** Do the bulk of the work */
	void process(File file) throws IOException {

		String name = file.getName();
		if (ignorable(name)) {
			return;
		}

		if (file.isDirectory()) {
			System.out.println("Indexing directory " + name);
			File[] files = file.listFiles();
			for (int i=0; i<files.length; i++) {
				String fn = files[i].getName();
				process(new File(file, fn));
			}
		} else {
			// file to be processed.
			list.put(name, file.getPath());
			addToNavigator(name);
		}
	}

	private void addToNavigator(String name) {
		final char ch = name.toUpperCase().charAt(0);
		if (Character.isLetterOrDigit(ch)) {
			exists[ch] = true;
		}
	}

	void writeNavigator() throws IOException {

		System.out.println("Writing the Alphabet Navigator...");
		for (char c = 'A'; c<='Z'; c++)
			if (exists[c])
				print("<a href=\"#" + c + "\">" + c + "</a> ");
	}

	void writeList() throws IOException {

		System.out.println("Sorting the list...");
		
		System.out.println("Start PASS TWO -- from List to " +
			OUTPUTFILE + "...");
		// ... the beginning of the HTML Unordered List...
		println("<ul>");
		for (String fn : list.keySet()) {			
			String path = list.get(fn);
			// Need to make a link into this directory.
			// IF there is a descr.txt file, use it for the text
			// of the link, otherwise, use the directory name.
			// But, if there is an index.html (or index.htm) file,
			// make the link to that file, else to the directory itself.
			if (fn.endsWith("/")) {	// directory
				String descr = null;
				if (new File(fn + "descr.txt").exists()) {
					descr = FileIO.readLine(fn + "descr.txt");
				}
				if (new File(fn + "index.html").exists())
					mkDirLink(fn+"index.html", descr!=null?descr:fn);
				else if (new File(fn + "index.htm").exists())
						mkDirLink(fn+"index.htm", descr!=null?descr:fn);
				else
					mkLink(fn, descr!=null?descr:fn + " -- Directory");
			} else // file
				mkLink(fn, path);
		}
		System.out.println("*** process - ALL DONE***");
	}

	/** Keep track of each letter for #links */
	boolean done[] = new boolean[255];

	void mkLink(String name, String path) {
		print("<li>");
		char c = name.charAt(0);
		if (!done[c]) {
			print("<a id=\"" + c + "\"></a>");
			done[c] = true;
		}
		print("<a href=\"" + path + "\">" + name + "</a>");
		println("</li>");
	}

	void mkDirLink(String index, String dir) {
		// XXX Open the index and look for TITLE lines!
		println("<a href='" + index + "'>" + dir + "</a>");
	}

	/** Write the trailers and a signature */
	void end() {
		System.out.println("Finishing the HTML");
		println("</ul>");
		flush();
		println("<p>This file generated by ");
		print("<a href=\"MkIndex.java\">MkIndex</a>, a Java program, at ");
		println(Calendar.getInstance().getTime().toString());
		println("</p>");
		println("</body>");
		println("</html>");
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

	/** Convenience routine for out.println */
	void println() {
		out.println();
	}

	/** Convenience for out.flush(); */
	void flush() {
		out.flush();
	}
}
