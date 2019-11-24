package performance;

import java.lang.reflect.*;

/**
 * Time the main method of other classes, for performance tuning.
 * A variation of Time, which takes multiple class names and no args.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class TimeNoArgs {
	/** An empty argv array */
	protected static String nargv[] = {};
	/** An Object[] array wrapper for nargv */
	protected static Object[] nargs = { nargv };

	public static void main(String[] argv) throws Exception {
		for (int i=0; i<argv.length; i++)
			doit(argv[i]);
	}

	protected static void doit(String arg) throws Exception {
		// Instantiate target class, from arg
		Class<?> c = Class.forName(arg);

		// Find its static main method (use nargv as the signature).
		Class<?>[] classes = { nargv.getClass() };
		Method main = c.getMethod("main", classes);

		System.err.println("Starting class " + c);

		// About to start timing run. Important to not do anything
		// (even a println) that would be attributed to the program
		// being timed, from here until we've gotten ending time.

		// Get current (i.e., starting) time
		long t0 = System.currentTimeMillis();

		// Run the main program
		main.invoke(null, nargs);

		// Get ending time, and compute usage
		long t1 = System.currentTimeMillis();

		long runTime = t1 - t0;

		System.err.println(
			 "runTime="  + Double.toString(runTime/1000D));
	}
	//-
}
