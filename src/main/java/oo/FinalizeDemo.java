package oo;

/**
 * Show a finalize() method in operation. Since we both call the cleanup() routine
 * from main and from finalize(), it should print "Cleanup called" twice on a
 * reasonably standard JVM.
 */
public class FinalizeDemo {

	public static void main(String[] args) throws Throwable {
		FinalizeDemo o = new FinalizeDemo();
		o.cleanup();
		o = null;
		System.gc();
		Thread.sleep(500);
	}

	/**
	 * May be called either by main and from GC (or both!)
	 */
	void cleanup() {
		System.out.println("Cleanup called");
		// free up resources...
	}

	protected void finalize() throws Throwable {
		try {
			cleanup();
		} finally {
			// Called from finally so it will get 
			// called even if cleanup() throws an Exception.
			super.finalize();
		}
	}
}
