package starting;

/**
 * Simple echo/print client -- print command line arguments (argv) back
 * to the standard oubput.
 */
public class EchoArgv {
	public static void main(String[] args) {
		new EchoArgv().func(args);
	}
	private void func(String args[]) {
		for (int i=0; i<args.length; i++) {
			System.out.print(args[i] + " ");
		}
		System.out.println("");		// end with newline
	}
}
