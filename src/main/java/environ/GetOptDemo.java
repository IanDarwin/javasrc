/** Simple demonstration of GetOpt. Accept the '-n' and '-o outfile'
 * options as shown for sort, and also -h for help.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class GetOptDemo {
	public static void main(String args[]) {
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
		System.out.println("Options:");
		System.out.println("Numeric options:" + numeric_option);
		System.out.println("Output file: " + outFileName);
		if (go.optind() == args.length) {
			sortFile("(standard input)");
		} else for (int i=go.optind(); i<args.length; i++)
			sortFile(args[i]);
	}
	static void doHelp(int returnValue) {
		// You can write a longer help than this, certainly.
		System.out.println("Usage: GetOptDemo [-h]-[n][-o outfile] file ...");
		System.exit(returnValue);
	}
	static void sortFile(String fileName) {
		System.out.println("Would now sort file " + fileName);
	}
}
