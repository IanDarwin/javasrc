import java.lang.reflect.*;

/**
 * Time the main method of another class, for performance tuning.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Time {
	//+
	public static void main(String argv[]) throws Exception {
		// Instantiate target class, from argv[0]
		Class c = Class.forName(argv[0]);

		// Find its (hopefully static) main method.
		Class[] classes = { argv.getClass() };
		Method main = c.getMethod("main", classes);

		// Make new argv array, dropping class name
		String nargv[] = new String[argv.length - 1];
		System.arraycopy(argv, 1, nargv, 0, nargv.length);

		Object[] nargs = { nargv };

		System.err.println("Starting class " + c);

		// Get current time
		long t0 = System.currentTimeMillis();

		// Run the main program
		main.invoke(null, nargs);

		// Get new time, and compute usage
		long t1 = System.currentTimeMillis();
		long runTime = t1 - t0;

		System.err.println(
			 "runTime="  + QuickTimeFormat.msToSecs(runTime));
	}
	//-
}
