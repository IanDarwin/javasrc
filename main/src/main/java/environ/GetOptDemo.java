package environ;

import com.darwinsys.lang.GetOpt;

/** Demonstrate the modern way of using GetOpt. This allows a subset of
 * <pre>UNIX sort options: sort -n -o outfile infile1 infile2</pre>
 * which means: sort numerically (-n), writing to file "outfile" (-o
 * outfile), sort from infile1 and infile2.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class GetOptDemo {
	public static void main(String[] args) {
		GetOpt go = new GetOpt("hno:");
		boolean numeric_option = false;
		String outFileName = "(standard output)";
		char c;
		while ((c = go.getopt(args)) != GetOpt.DONE) {
			switch(c) {
			case 'h':
				doHelp(0);
				break;
			case 'n':
				numeric_option = true;
				break;
			case 'o':
				outFileName = go.optarg();
				break;
			default:
				System.err.println("Unknown option character " + c);
				doHelp(1);
			}
		}
		System.out.print("Options: ");
		System.out.print("Numeric: " + numeric_option + ' ');
		System.out.print("Output: " + outFileName + "; ");
		System.out.print("Inputs: ");
		if (go.getOptInd() == args.length) {
			doFile("(standard input)");
		} else for (int i = go.getOptInd(); i < args.length; i++) {
			doFile(args[i]);
		}
		System.out.println();
	}

	/** Stub for providing help on usage
	 * You can write a longer help than this, certainly.
	 */
	static void doHelp(int returnValue) {
		System.err.println("Usage: GetOptDemo [-h][-n][-o outfile] file ...");
		System.exit(returnValue);
	}

	/** Stub to demonstrate processing one file. */
	static void doFile(String fileName) {
		System.out.print(fileName + ' ');
	}
}
