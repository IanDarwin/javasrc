package threads;

/** 
 * Illustrate the following quotation:<blockquote>
 * "Threads are re-entrant. A given thread can acquire the same
 * monitor several times..."
 * </blockquote>
 *
 * @author	Ian Darwin
 */
public class Reentrant extends Thread {
	int count = 5;

	/** Run does the work: print a message, "count" number of times */ 
	public synchronized void run() {
		while (count-- > 0) {
			System.out.println("Count = " + count);
			run();
		}
	}

	/**
	 * Main program, test driver for ThreadSync class.
	 */
	public static void main(String[] argv) {
		Thread t = new Reentrant();
		t.start();
		try {
			t.join();
		} catch (InterruptedException ex) {
			//
		}
		System.out.println("All done.");
	}
}
