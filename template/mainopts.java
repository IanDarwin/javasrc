/** Template main program using GetOpt.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class mainopts {
	public static void main(String argv[]) {
		String argChars = "o:hv";
		GetOpt go = new GetOpt("ho:v");

		char c;
		while ((c =go.getopt(argv)) != 0) {
			switch(c) {
				case 'h':
					System.out.println("HELP!!");
					break;
				case 'o':
					System.out.print("-o Option " + go.optarg());
					break;
				case 'v':
					verbose = 1;
					break;
				default:
					System.err.println("Unknown option char " + ((char)c));
			}
		}
		for (int i=go.optind(); i<argv.length; i++)
			System.out.println("Filename-like arg " + args[i]);
	}
}
