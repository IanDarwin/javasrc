import RE;
import java.io.*;

//+
/** A command-line grep-like program. No options, but takes a pattern
 * and an arbitrary list of text files.
 */
public class Grep1 {
	/** The pattern we're looking for */
	RE pattern;
	/** The Reader for the current file */
    BufferedReader d;

	/** Construct a Grep object for each pattern, and run it
	 * on all input files listed in argv.
	 */
	public static void main(String argv[]) {

		if (argv.length < 1) {
		    System.err.println("Usage: Grep pattern [filename]");
		    System.exit(1);
		}

		Grep1 pg = new Grep1(argv[0]);

		if (argv.length == 1)
			pg.process(new InputStreamReader(System.in), "(standard input", false);
		else
			for (int i=1; i<argv.length; i++) {
				try {
					pg.process(new FileReader(argv[i]), 
						argv[i], true);
				} catch(Exception e) {
					System.err.println(e);
				}
			}
	}

	public Grep1(String arg) {
		// compile the regular expression
		pattern = new RE(arg);
	}
        
	/** Do the work of scanning one file
	 * @param	patt	RE	Regular Expression object
	 * @param	ifile	Reader	Reader object already open
	 * @param	fileName String	Name of the input file
	 * @param	printFileName	Boolean - true to print filename
	 *		before lines that match.
	 */
	public void process(
		Reader ifile, String fileName, boolean printFileName) {

		String line;

		try {
			d = new BufferedReader(ifile);
		    
			while ((line = d.readLine()) != null) {
				if (pattern.isMatch(line)) {
					if (printFileName)
						System.out.print(fileName + ": ");
					System.out.println(line);
				}
			}
			d.close();
		} catch (IOException e) { System.err.println(e); }
	}
}
//-
