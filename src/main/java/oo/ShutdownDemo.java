package oo;

/** Demonstrate how finalize() methods and shutdownHooks interact
 *  with calls to System.exit().
 */
public class ShutdownDemo {
	public static void main(String[] args) throws Exception {

		// Create an Object with a finalize() method.
		Object f = new Object() {
			public void finalize() {
				System.out.println( "Running finalize()");
			}
		};

		// Add a shutdownHook to the JVM
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Running Shutdown Hook");
			}
		});

		// Unless the user puts -f (for "free") on the command line,
		// call System.exit while holding a reference to 
		// Object f, which can therefore not be finalized().

		if (args.length == 1 && args[0].equals("-f")) {
			f = null;
			System.gc();
		}

		System.out.println("Calling System.exit()");
		System.exit(0);
	}
}
