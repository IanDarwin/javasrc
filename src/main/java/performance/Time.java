package performance;

import java.lang.reflect.*;

/**
 * Time the main method of another class, for performance tuning.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class Time {
	public static void main(String[] argv) throws Exception {
		// Instantiate target class, from argv[0]
		Class<?> c = Class.forName(argv[0]);

		// Find its static main method (use our own argv as the signature).
		Class<?>[] classes = { argv.getClass() };
		Method main = c.getMethod("main", classes);

		// Make new argv array, dropping class name from front.
		// (Normally Java doesn't get the class name, but in 
		// this case the user puts the name of the class to time
		// as well as all its arguments...
		String nargv[] = new String[argv.length - 1];
		System.arraycopy(argv, 1, nargv, 0, nargv.length);

		Object[] nargs = { nargv };

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
}
// END main
