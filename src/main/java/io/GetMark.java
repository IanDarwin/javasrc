import java.io.*;

/** GetMark -- get marked lines.
 * In this version, the marks are hard-coded; ideally they would come
 * from a FileProperties object.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */

public class GetMark {
	//+
	/** the default starting mark. */
	public final String startMark = "//+";
	/** the default ending mark. */
	public final String endMark = "//-";
	/** True if we are currently inside marks. */
	protected boolean printing = false;
	//-

    /** Get Marked parts of one file, given an open LineNumberReader.
	 */
    public void process(String fileName,
		LineNumberReader is,
		PrintStream out) {
		//+
		int nLines = 0;
		try {
			String inputLine;
			// Number of chars representing no indent
			final int NOINDENT = 0;
			// Number of chars to strip off to remove indentation
			int indent = NOINDENT;

			while ((inputLine = is.readLine()) != null) {
				if (inputLine.trim().equals(startMark)) {
					if (printing)
						System.err.println("ERROR: START INSIDE START, " +
							fileName + : + is.getLineNumber());
					printing = true;
					indent = NOINDENT;
				} else if (inputLine.trim().equals(endMark)) {
					if (!printing)
						System.err.println("ERROR: STOP WHILE STOPPED, " +
							fileName + : + is.getLineNumber());
					printing = false;
				} else if (printing) {
					if (indent < inputLine.length() && indent == NOINDENT) {
						while (Character.isWhitespace(inputLine.charAt(indent)))
							++indent;
					}
					if (indent == NOINDENT || inputLine.length() == 0)
						out.println(inputLine);
					else
						out.println(inputLine.substring(indent));
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
	 * opening files and such like for you.
	 */
    public static void main(String av[]) {
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
