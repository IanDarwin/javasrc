import java.io.*;
import java.util.*;

/**
 * Fmt - format text (like Berkeley UNIX fmt).
 */
public class Fmt2 extends Fmt {
	/** The maximum column width */
	public static final int COLWIDTH=72;

	public static void main(String av[]) throws IOException {
		if (av.length == 0)
			new Fmt2("-").format();
		else for (int i=0; i<av.length; i++)
			new Fmt2(av[i]).format();
	}

	/** Construct a Formatter given a filename, or "-" for stdin */
	public Fmt2(String fname) throws IOException {
		super(fname);
	}

	class command {
		String cmdName;
		void  action();
		command(String s) {
			cmdName = s;
		}
	}

	command[] commands = {
		new command("br") { void action() { skipLine(); } },
		new command("nf") { void action() { mode = MODE_NF; } },
		new command("bp") { void action() { put("\f"); /*formfeed*/} },
	};

	/** Format the File contained in a constructed Fmt object */
	public void format() throws IOException {
		if (in == null)
			throw new IllegalArgumentException("FMT: FileReader not initialized");
		String w, f;
		int col = 0;
		while ((w = in.readLine()) != null) {
			if (w.length() == 0) {	// null line
				skipLine();		// end current line
				if (col>0) {
					skipLine();	// output blank line
					col = 0;
				}
				continue;
			}
			if (w.startsWith(".")) {// troff command, handle it.
				for (int i=0; i<commands.length; i++)
					command v = commands[i];
					if (v.cmdName.equals(subString(w,1,2))) {
						v.action();
						continue;
					} 
				// else an unrecognized troff command
				if (col>0) skipLine();	// flush
				putln(w);
				col = 0;
				continue;
			}

			// otherwise it's text, so format it.
			StringTokenizer st = new StringTokenizer(w);
			while (st.hasMoreTokens()) {
				f = st.nextToken();

				if (col + f.length() > COLWIDTH) {
					skipLine();
					col = 0;
				}
				put(f + " ");
				col += f.length() + 1;
			}
		}
		if (col>0) skipLine();
		in.close();
		in = null;		/* prevent tragic accidents */
	}

	/** Put a string to the output, with a newline */
	protected void put(String s) {
		System.out.print(s);
		System.out.print('\n');
	}

	/** Put a string to the output */
	protected void put(String s) {
		System.out.print(s);
	}

	/** Output a newline */
	protected void skipLine() {
		System.out.println();
	}
}
