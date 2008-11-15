package strings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Fmt - format text (like Berkeley UNIX fmt).
 */
public class Fmt {
	/** The maximum column width */
	public static final int COLWIDTH=72;
	/** The file that we read and format */
	BufferedReader in;

	/** If files present, format each, else format the standard input. */
	public static void main(String[] av) throws IOException {
		if (av.length == 0)
			new Fmt(System.in).format();
		else for (int i=0; i<av.length; i++)
			new Fmt(av[i]).format();
	}

	/** Construct a Formatter given an open Reader */
	public Fmt(BufferedReader file) throws IOException {
		in = file;
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
		StringBuilder out = new StringBuilder();
		while ((line = in.readLine()) != null) {
			if (line.length() == 0) {	// null line
				System.out.println(out);	// end current line
				System.out.println();	// output blank line
				out.setLength(0);
			} else {
				// otherwise it's text, so format it.
				StringTokenizer st = new StringTokenizer(line);
				while (st.hasMoreTokens()) {
					String word = st.nextToken();

					// If this word would go past the margin,
					// first dump out anything previous.
					if (out.length() + word.length() > COLWIDTH) {
						System.out.println(out);
						out.setLength(0);
					}
					out.append(word).append(' ');
				}
			}
		}
		if (out.length() > 0) {
			System.out.println(out);
		} else {
			System.out.println();
		}
	}
}
