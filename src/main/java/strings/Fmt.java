import java.io.*;
import java.util.*;

/**
 * Fmt - format text (like Berkeley UNIX fmt).
 */
public class Fmt {
	/** The maximum column width */
	public static final int COLWIDTH=72;
	/** The file that we read and format */
	BufferedReader in;

	public static void main(String av[]) throws IOException {
		if (av.length == 0)
			new Fmt("-").format();
		else for (int i=0; i<av.length; i++)
			new Fmt(av[i]).format();
	}
	/** Construct a Formatter given a filename, or "-" for stdin */
	public Fmt(String fname) throws IOException {
		if ("-".equals(fname))
			in = new BufferedReader(new InputStreamReader(System.in));
		else
			in = new BufferedReader(new FileReader(fname));
	}

	/** Format the File contained in a constructed Fmt object */
	public void format() throws IOException {
		if (in == null)
			throw new IllegalArgumentException("FMT: FileReader not initialized");
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
			if (w.startsWith(".")) {// troff command, send verbatim.
				if (col>0) System.out.print("\n");	// flush
				System.out.println(w);
				col = 0;
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
		in = null;		/* prevent tragic accidents */
	}
}
