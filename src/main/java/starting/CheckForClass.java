package starting;

/** Test for presence of class named in argv[0] on classpath at runtime.
 * This uses Reflection (see chapter "Introspection, or a Class named Class")
 * to find out if the given class (named on the command line) is present
 * in the user's classpath at runtime. The class should not be "import"ed
 * because we don't want to check it at compile time, only at runtime.
 * @return 0 if OK, 1 if class not found, 2 if used incorrectly.
 */
public class CheckForClass {
	public static void main(String[] argv) {
		if (argv.length != 1) {
			System.err.println("Usage: TestForClass className");
			System.exit(2);
		}
		try {
			String className = argv[0];
			Class.forName(className);
			System.exit(0);		// It worked.
		} catch (ClassNotFoundException e) {
			// No diagnostic message printed: this is for use in
			// batch files to set the exit status only.
			System.exit(1);		// Class not found.
		}
	}
}
