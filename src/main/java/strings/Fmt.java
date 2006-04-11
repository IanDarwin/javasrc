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

	/** Construct a Formatter given a filename */
	public Fmt(String fname) throws IOException {
		in = new BufferedReader(new FileReader(fname));
	}

	/** Construct a Formatter given an open Stream */
	public Fmt(InputStream file) throws IOException {
		in = new BufferedReader(new InputStreamReader(file));
	}

	/** Format the File contained in a constructed Fmt object */
	public void format() throws IOException {
		String w, f;
		int col = 0;
		while ((w = in.readLine()) != null) {
			if (w.length() == 0) {	// null line
				System.out.print("\n");		// end current line
				if (col>0) {
					System.out.print("\n");	// output blank line
					col = 0;
				}
				continue;
			}

			// otherwise it's text, so format it.
			StringTokenizer st = new StringTokenizer(w);
			while (st.hasMoreTokens()) {
				f = st.nextToken();

				if (col + f.length() > COLWIDTH) {
					System.out.print("\n");
					col = 0;
				}
				System.out.print(f + " ");
				col += f.length() + 1;
			}
		}
		if (col>0) System.out.print("\n");
		in.close();
	}
}
