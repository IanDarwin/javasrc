package oo;

/** Demonstrate how finalize() methods and shutdownHooks interact
 *  with calls to System.exit().
 */
// tag::main[]
public class ShutdownDemo {
	public static void main(String[] args) throws Exception {

		// Create an Object with a finalize() method - Bad idea!
		// (no longer even showing this code!)
		Object f = new Object();

		// Add a shutdownHook to the JVM
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println("Running Shutdown Hook");
			}
		});

		if (args.length == 1 && args[0].equals("-f")) {
			System.gc();
		}

		System.out.println("Calling System.exit(), with f = " + f);
		System.exit(0);
	}
}
// end::main[]
