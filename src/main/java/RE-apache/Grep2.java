import com.darwinsys.regexp.RE;				// my RE package
import com.darwinsys.regexp.RESyntaxException;	// my RE package
// import org.apache.regexp.RE;					// Apache regexp
// import org.apache.regexp.RESyntaxException;		// Apache regexp
import java.io.*;
import java.util.*;

//+
/** A command-line grep-like program. Some options, and takes a pattern
 * and an arbitrary list of text files.
 */
public class Grep2 {
	/** The pattern we're looking for */
	protected RE pattern;
	/** The Reader for the current file */
    protected BufferedReader d;
	/** Are we to only count lines, instead of printing? */
	protected boolean countOnly = false;
	/** Are we to ignore case? */
	protected boolean ignoreCase = false;
	/** Are we to suppress print of filenames? */
	protected boolean dontPrintFileName = false;
	/** Are we to only list names of files that match? */
	protected boolean listOnly = false;
	/** are we to print line numbers? */
	protected boolean numbered = false;
	/** Are we to be silent bout errors? */
	protected boolean silent = false;
	/** are we to print only lines that DONT match? */
	protected boolean inVert = false;

	/** Construct a Grep object for each pattern, and run it
	 * on all input files listed in argv.
	 */
	public static void main(String[] argv) {

		if (argv.length < 1) {
		    System.err.println("Usage: Grep pattern [filename]");
		    System.exit(1);
		}
		String pattern = null;

		GetOpt go = new GetOpt("cf:hilnsv");
		BitSet args = new BitSet();

		char c;
		while ((c = go.getopt(argv)) != 0) {
			switch(c) {
				case 'c':
					args.set('C');
					break;
				case 'f':
					try {
						BufferedReader b = new BufferedReader(new FileReader(go.optarg()));
						pattern = b.readLine();
						b.close();
					} catch (IOException e) {
						System.err.println("Can't read pattern file " + go.optarg());
						System.exit(1);
					}
					break;
				case 'h':
					args.set('H');
					break;
				case 'i':
					args.set('I');
					break;
				case 'l':
					args.set('L');
					break;
				case 'n':
					args.set('N');
					break;
				case 's':
					args.set('S');
					break;
				case 'v':
					args.set('V');
					break;
			}
		}

		if (pattern == null)
			pattern = argv[0];

		Grep2 pg = new Grep2(pattern, args);

		if (argv.length == 1)
			pg.process(new InputStreamReader(System.in), "(standard input");
		else
			for (int i=1; i<argv.length; i++) {
				try {
					pg.process(new FileReader(argv[i]), argv[i]);
				} catch(Exception e) {
					System.err.println(e);
				}
			}
	}

	public Grep2(String arg, BitSet args) {
		// compile the regular expression
		pattern = new RE(arg);
		if (args.get('C'))
			countOnly = true;
		if (args.get('H'))
			dontPrintFileName = true;
		if (args.get('I'))
			ignoreCase = true;
		if (args.get('L'))
			listOnly = true;
		if (args.get('N'))
			numbered = true;
		if (args.get('S'))
			silent = true;
		if (args.get('V'))
			inVert = true;
	}
        
	/** Do the work of scanning one file
	 * @param	patt	RE	Regular Expression object
	 * @param	ifile	Reader	Reader object already open
	 * @param	fileName String	Name of the input file
	 */
	public void process(Reader ifile, String fileName) {

		String line;
		int matches = 0;

		try {
			d = new BufferedReader(ifile);
		    
			while ((line = d.readLine()) != null) {
				if (pattern.isMatch(line, ignoreCase)) {
					if (countOnly)
						matches++;
					else {
					if (!dontPrintFileName)
						System.out.print(fileName + ": ");
					System.out.println(line);
					}
				} else if (inVert) {
					System.out.println(line);
				}
			}
			if (countOnly)
				System.out.println(matches + " matches in " + fileName);
			d.close();
		} catch (IOException e) { System.err.println(e); }
	}
}
//-
