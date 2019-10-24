package lang;

/** Show use of Argv to get an integer value from command line */
public class ArgvNumber {
	/** Main program */
	public static void main(String[] argv) {
		int number = 0;

		System.out.println("The number of words in argv is " + argv.length);

		if (argv.length == 0) {
			number = 1234;
		} else if (argv.length == 1) {
			try {
				number = Integer.parseInt(argv[0]);
			} catch(NumberFormatException e) {
				System.err.println("Number " + argv[0] + " invalid (" + e.getMessage() + ").");
				System.exit(1);
			}
		} else {
			System.err.println("usage: UseArgv number");
			System.exit(1);
		}

		System.out.println("OK, number is " + number);
	}
}
