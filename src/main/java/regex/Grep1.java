import java.util.regex.*;
import java.io.*;

/** A command-line grep-like program. No options, but takes a pattern
 * and an arbitrary list of text files.
 */
public class Grep1 {
	/** The pattern we're looking for */
	protected RE pattern;
	/** The Reader for the current file */
    protected BufferedReader d;

	/** Construct a Grep object for each pattern, and run it
	 * on all input files listed in argv.
	 */
	public static void main(String[] argv) throws Exception {

		if (argv.length < 1) {
		    System.err.println("Usage: Grep1 pattern [filename]");
		    System.exit(1);
		}

		Grep1 pg = new Grep1(argv[0]);

		if (argv.length == 1)
			pg.process(new InputStreamReader(System.in), "(standard input)", false);
		else
			for (int i=1; i<argv.length; i++) {
				pg.process(new FileReader(argv[i]), argv[i], true);
			}
	}

	public Grep1(String arg) throws PatternSyntaxException {
		// compile the regular expression
		pattern = new RE(arg);
	}
        
	/** Do the work of scanning one file
	 * @param ifile Reader Reader object already open
	 * @param fileName String Name of the input file
	 * @param printFileName Boolean - true to print filename
	 * before lines that match.
	 */
	public void process(
		Reader ifile, String fileName, boolean printFileName) {

		String inputLine;

		try {
			d = new BufferedReader(ifile);
		    
			while ((inputLine = d.readLine()) != null) {
				if (pattern.match(inputLine)) {
					if (printFileName)
						System.out.print(fileName + ": ");
					System.out.println(inputLine);
				}
			}
			d.close();
		} catch (IOException e) { System.err.println(e); }
	}
}
