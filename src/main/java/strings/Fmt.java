package strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/**
 * Fmt - format text (like Berkeley UNIX fmt).
 * N.B. This file (and its test) will move from javasrc to darwinsys-api,
 * and be simplified.
 */
// BEGIN main
public class Fmt {
	/** The maximum column width */
	public static final int COLWIDTH=72;
	/** The file that we read and format */
	final BufferedReader in;
	/** Where the output goes */
	PrintWriter out;

	/** If files present, format each one, else format the standard input. */
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
		format(in.lines(), out);
	}
	
	/** Format a Stream of lines, e.g., bufReader.lines() */
	public static void format(Stream<String> s, PrintWriter out) {
		StringBuilder outBuf = new StringBuilder();
		s.forEachOrdered((line -> {
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
		}));
		if (outBuf.length() > 0) {
			out.println(outBuf);
		} else {
			out.println();
		}
	}


}
// END main
