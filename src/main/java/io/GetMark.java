import java.io.*;

/** GetMark -- get marked lines.
 * This can be used either as a standalone utility via the included
 * "main" program wrapper, or inside other classes.
 * <p>
 * In this version, the marks are hard-coded; ideally they would come
 * from a Properties or Preferences object.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class GetMark {
	/** the default starting mark. */
	public final String startMark = "//+";
	/** the default ending mark. */
	public final String endMark = "//-";
	/** Set this to TRUE for running in "exclude" mode (e.g., for
	 * building exercises from solutions) and to FALSE for running
	 * in "extract" mode (e.g., writing a book and ommittin the
	 * imports and "public class" stuff).
	 */
	public final static boolean START = true;
	/** True if we are currently inside marks. */
	protected boolean printing = START;
	/** True if you want line numbers */
	protected final boolean number = false;
	//-
	/* This part should be excluded! */
	int foo = 42;
	//+

    /** Get Marked parts of one file, given an open LineNumberReader.
	 */
    public void process(String fileName,
		LineNumberReader is,
		PrintStream out) {
		//+
		int nLines = 0;
		try {
			String inputLine;

			while ((inputLine = is.readLine()) != null) {
				if (inputLine.trim().equals(startMark)) {
					if (printing)
						// These go to stderr, so you can redirect the output
						System.err.println("ERROR: START INSIDE START, " +
							fileName + ':' + is.getLineNumber());
					printing = true;
				} else if (inputLine.trim().equals(endMark)) {
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
		//-
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

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
