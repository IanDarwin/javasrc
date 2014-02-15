package io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;

/** GetMark -- get marked lines.
 * <p>
 * GetMark is a general tool for including/excluding parts of a file.
 * It can be used, for example, to extract parts of a file for use
 * in documentation, or to delete parts of a file such as the working
 * part of a solution.
 * <p>
 * The marks that it looks for are simple, and can be left in the
 * master source (they never print). The mark //+ (as looked for
 * with line.trim().equals("//+) in Java) begins printing, and the
 * opposite mark //- stops printing.
 * <p>
 * So, for a course exercise, you would develop the working
 * solution and comment it neatly, and add a //- mark after the TODO
 * comments but before the working solution, and a //+ mark after it.
 * For example:
 * </p><pre>
 * 	public methodA() {
 * 		// TODO:
 * 		// Look up the object to be invoked.
 * 		// Use a Lookup Name of "ex31object"
 * 
 * 		//- main
 * 		Object o = Naming.lookup("ex31object");
 * 		//+ main
 * 
 * 		// TODO #2
 * 		// Downcast the looked up object using the IIOP portability
 * 
 * 		//- main
 * 		Ex31Object obj = (Ex31Object)PortableRemoteObject.narrow(
 * 			o, Ex31Object.class);
 * 		//+ main
 * 	}
 * </pre><p>
 * When run through GetMark in "exclude" mode, the above will produce:
 * </p><pre>
 *  public methodA() {
 *      // TODO:
 *      // Look up the object to be invoked.
 *      // Use a Lookup Name of "ex31object"
 * 
 * 
 *      // TODO #2
 *      // Downcast the looked up object using the IIOP portability
 *
 *  }
 * </pre><p>
 * You could use this in a script:
 * </p><pre>
 * for f in *.java
 * do
 *    echo $f
 *    java GetMark $f &gt; ../solutions/$f
 * done
 * </pre><p>
 * For an example of using GetMark for extraction
 * (GetMark first appeared in my 
 * <a href="http://javacook.darwinsys.com/">Java Cookbook</a>),
 * see the comments in the code for GetMark itself.
 * <p>
 * In this version, the mode (include or extract) and the strings for
 * the marks are hard-coded; ideally they would come
 * from a Properties or Preferences object and/or from the command line.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class GetMark {
	/** the default starting mark. */
	public final String START_MARK = "//+";
	/** the default ending mark. */
	public final String END_MARK = "//-";
	/** Set this to TRUE for running in "exclude" mode (e.g., for
	 * building exercises from solutions) and to FALSE for running
	 * in "extract" mode (e.g., writing a book and omitting the
	 * imports and "public class" stuff).
	 */
	public final static boolean START = true;
	/** True if we are currently inside marks. */
	protected boolean printing = START;
	/** True if you want line numbers */
	protected final boolean number = false;

	/** Get Marked parts of one file, given an open LineNumberReader.
	 * This is the main operation of this class, and can be used
	 * inside other programs or from the main() wrapper.
	 */
	public void process(String fileName,
		LineNumberReader is,
		PrintStream out) {
		int nLines = 0;
		try {
			String inputLine;

			while ((inputLine = is.readLine()) != null) {
				if (inputLine.trim().equals(START_MARK)) {
					if (printing)
						// These go to stderr, so you can redirect the output
						System.err.println("ERROR: START INSIDE START, " +
							fileName + ':' + is.getLineNumber());
					printing = true;
				} else if (inputLine.trim().equals(END_MARK)) {
					if (!printing)
						System.err.println("ERROR: STOP WHILE STOPPED, " +
							fileName + ':' + is.getLineNumber());
					printing = false;
				} else if (printing) {
					if (number) {
						out.print(nLines);
						out.print(": ");
					}
					out.println(inputLine);
					++nLines;
				}
			}
			is.close();
			out.flush(); // Must not close - caller may still need it.
			if (nLines == 0)
				System.err.println("ERROR: No marks in " + fileName +
					"; no output generated!");
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
// END main

	/** This simple main program looks after filenames and
	 * opening files and such like for you, when GetMark is being
	 * used standalone.
	 * XXX TODO options parsing, allow include/exclude, number, etc.
	 * to be set from the command line.
	 */
	public static void main(String[] av) {
		GetMark o = new GetMark();
		PrintStream pw = new PrintStream(System.out);
	if (av.length == 0) {
		o.process("standard input", new LineNumberReader(
			new InputStreamReader(System.in)), pw);
		} else {
			for (int i=0; i<av.length; i++)
				try {
					o.process(av[i],
						new LineNumberReader(new FileReader(av[i])), pw);
				} catch (FileNotFoundException e) {
					System.err.println(e);
				}
        }
    }
}
