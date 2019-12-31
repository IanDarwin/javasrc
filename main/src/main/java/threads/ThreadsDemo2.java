package threads;


/** 
 * Threaded demo application, version 2, using Runnable.
 * @author	Ian Darwin
 */
// tag::main[]
public class ThreadsDemo2 implements Runnable {
	private String message;
	private Thread t;
	private int count;

	/**
	 * Main program, test driver for ThreadsDemo2 class.
	 */
	public static void main(String[] argv) {
		new ThreadsDemo2("Hello from X", 10).start();
		new ThreadsDemo2("Hello from Y", 15).start();
	}

	/**
	 * Construct a ThreadsDemo2 object
	 * @param m Message to display
	 * @param n How many times to display it
	 */
	public ThreadsDemo2(String m, int n) {
		count = n;
		message  = m;
	}
	void start() {
		t = new Thread(this);
		t.setName(message + " runner Thread");
		t.start();
	}

	/** Run does the work. We override the run() method in Runnable. */ 
	public void run() {
		while (count-- > 0) {
			System.out.println(message);
			try {
				Thread.sleep(100);	// 100 msec
			} catch (InterruptedException e) {
				return;
			}
		}
		System.out.println(message + " thread all done.");
	}
}
// end::main[]
