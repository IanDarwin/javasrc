package strings;

import java.io.*;
import java.util.*;

/**
 * Fmt - format text (like Berkeley UNIX fmt, with a few troff commands).
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
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
	/** The current output column. */
	protected int col = 0;

	public static void main(String[] av) throws IOException {
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
		new Command("br") { void action() { spaceLine(0); } },
		new Command("bp") { void action() { put("\f"); /*formfeed*/} },
		new Command("fi") { void action() { mode = MODE_FI; } },
		new Command("nf") { void action() { mode = MODE_NF; spaceLine(0);} },
		new Command("sp") { void action() { spaceLine(1); } },
	};

	/** Format the File contained in a constructed Fmt object */
	public void format() throws IOException {
		String w, f;
		col = 0;
outer:
		while ((w = in.readLine()) != null) {
			if (w.length() == 0) {	// null line
				spaceLine(1);
				continue;
			}
			if (w.startsWith(".")) {// troff command, handle it.
				for (int i=0; i<commands.length; i++) {
					Command v = commands[i];
					if (v.cmdName.equals(w.substring(1, 3))) {
						v.action();
						continue outer;
					} 
				}
				// else an unrecognized troff command
				if (col>0) putln();	// flush
				putln(w);
				col = 0;
				continue;
			}

			// otherwise it's text, so deal with it.
			if (mode == MODE_NF) {
				putln(w);
				continue;
			}

			// else, we have to format it.
			StringTokenizer st = new StringTokenizer(w);
			while (st.hasMoreTokens()) {
				f = st.nextToken();

				if (col + f.length() > COLWIDTH) {
					putln();
					col = 0;
				}
				put(f + " ");
				col += f.length() + 1;
			}
		}
		if (col>0) putln();
		in.close();
	}

	/* Break the current line, and output nLines blank lines */
	void spaceLine(int nLines) {
		if (col>0) {
			putln();	// output blank line
			col = 0;
		}
		for (int i=0; i<nLines; i++)
			putln();
	}

	/** Put a string to the output, with a newline, i.e., output a newline */
	protected void putln(String s) {
		System.out.println(s);
	}

	/** Put the null string to the output as a line */
	protected void putln() {
		System.out.println();
	}

	/** Put a string to the output */
	protected void put(String s) {
		System.out.print(s);
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
