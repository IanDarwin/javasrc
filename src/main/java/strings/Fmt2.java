import java.io.*;
import java.util.*;

/**
 * Fmt - format text (like Berkeley UNIX fmt).
 */
public class Fmt2 extends Fmt {
	/** The maximum column width */
	public static final int COLWIDTH=72;
	/** The constant for formatted mode */
	protected final int MODE_FI = 1;
	/** The constant for UNformatted mode */
	protected final int MODE_NF = 0;
	/** The current formatting mode */
	protected int mode = MODE_FI;

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

	/** The Array of commands */
	Command[] commands = {
		new Command("br") { void action() { skipLine(); } },
		new Command("bp") { void action() { put("\f"); /*formfeed*/} },
		new Command("fi") { void action() { mode = MODE_FI; } },
		new Command("nf") { void action() { mode = MODE_NF; } },
		new Command("sp") { void action() { skipLine(); } },
	};

	/** Format the File contained in a constructed Fmt object */
	public void format() throws IOException {
		String w, f;
		int col = 0;
outer:
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
				for (int i=0; i<commands.length; i++) {
					Command v = commands[i];
					if (v.cmdName.equals(w.substring(1, 2))) {
						v.action();
						continue outer;
					} 
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
	}

	/** Put a string to the output, with a newline */
	protected void putln(String s) {
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

/** A Command is a formatter command: it has a name and an action. */
abstract class Command {
	String cmdName;
	abstract void  action();
	Command(String s) {
		cmdName = s;
	}
}
