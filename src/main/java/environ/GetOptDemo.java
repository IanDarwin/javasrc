/** Simple demonstration of GetOpt. Accept the '-n' and '-o outfile'
 * options as shown for sort, and also -h for help.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class GetOptDemo {
	public static void main(String[] args) {
		GetOpt go = new GetOpt("hno:");
		boolean numeric_option = false;
		String outFileName = "(standard output)";
		char c;
		while ((c =go.getopt(args)) != 0) {
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
		System.out.print("Output: " + outFileName + ' ');
		System.out.print("; Input: ");
		if (go.optind() == args.length) {
			doFile("(standard input)");
		} else for (int i=go.optind(); i<args.length; i++)
			doFile(args[i]);
		System.out.println();
	}

	/** Stub for providing help on usage
	 * You can write a longer help than this, certainly.
	 */
	static void doHelp(int returnValue) {
		System.err.println("Usage: GetOptDemo [-h]-[n][-o outfile] file ...");
		System.exit(returnValue);
	}
	/** Stub to demonstrate processine one file. */
	static void doFile(String fileName) {
		System.out.print(fileName + ' ');
	}
}
