import java.util.regex.*;
import java.io.*;

/** A command-line grep-like program. No options, but takes a pattern
 * and an arbitrary list of text files.
 */
public class Grep1 {
	/** The pattern we're looking for */
	protected Pattern pattern;
	/** The Reader for the current file */
    protected BufferedReader d;

	/** Main will make a Grep object for the pattern, and run it
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

	/** Construct a Grep1 program */
	public Grep1(String patt) throws PatternSyntaxException {
		// compile the regular expression, with .* around so it will
		// match anywhere in the input line
		pattern = Pattern.compile(".*" + patt + ".*");
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
				Matcher m = pattern.matcher(inputLine);
				if (m.matches()) {
					if (printFileName) {
						System.out.print(fileName + ": ");
					}
					System.out.println(inputLine);
				}
			}
			d.close();
		} catch (IOException e) { System.err.println(e); }
	}
}
