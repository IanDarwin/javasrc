package strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Fmt - format text (like Berkeley UNIX fmt).
 */
// BEGIN main
public class Fmt {
	/** The maximum column width */
	public static final int COLWIDTH=72;
	/** The file that we read and format */
	final BufferedReader in;
	/** Where the output goes */
	PrintWriter out;

	/** If files present, format each, else format the standard input. */
	public static void main(String[] av) throws IOException {
		if (av.length == 0)
			new Fmt(System.in).format();
		else for (String name : av) {
			new Fmt(name).format();
		}
	}
	
	public Fmt(BufferedReader inFile, PrintWriter outFile) {
		this.in = inFile;
		this.out = outFile;
	}
	
	public Fmt(PrintWriter out) {
		this(new BufferedReader(new InputStreamReader(System.in)), out);
	}

	/** Construct a Formatter given an open Reader */
	public Fmt(BufferedReader file) throws IOException {
		this(file, new PrintWriter(System.out));
	}
	
	/** Construct a Formatter given a filename */
	public Fmt(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}

	/** Construct a Formatter given an open Stream */
	public Fmt(InputStream file) throws IOException {
		this(new BufferedReader(new InputStreamReader(file)));
	}

	/** Format the File contained in a constructed Fmt object */
	public void format() throws IOException {
		String line;
		StringBuilder outBuf = new StringBuilder();
		while ((line = in.readLine()) != null) {
			if (line.length() == 0) {	// null line
				out.println(outBuf);	// end current line
				out.println();	// output blank line
				outBuf.setLength(0);
			} else {
				// otherwise it's text, so format it.
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					String word = st.nextToken();

					// If this word would go past the margin,
					// first dump out anything previous.
					if (outBuf.length() + word.length() > COLWIDTH) {
						out.println(outBuf);
						outBuf.setLength(0);
					}
					outBuf.append(word).append(' ');
				}
			}
		}
		if (outBuf.length() > 0) {
			out.println(outBuf);
		} else {
			out.println();
		}
	}
}
// END main
