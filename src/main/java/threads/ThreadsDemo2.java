package threads;


/** 
 * Threaded demo application, version 2, using Runnable.
 * @author	Ian Darwin
 */
// BEGIN main
public class ThreadsDemo2 implements Runnable {
	private String mesg;
	private Thread t;
	private int count;

	/**
	 * Main program, test driver for ThreadsDemo2 class.
	 */
	public static void main(String[] argv) {
		new ThreadsDemo2("Hello from X", 10);
		new ThreadsDemo2("Hello from Y", 15);
	}

	/**
	 * Construct a ThreadsDemo2 object
	 * @param m Message to display
	 * @param n How many times to display it
	 */
	public ThreadsDemo2(String m, int n) {
		count = n;
		mesg  = m;
		t = new Thread(this);
		t.setName(m + " runner Thread");
		t.start();
	}

	/** Run does the work. We override the run() method in Runnable. */ 
	public void run() {
		while (count-- > 0) {
			System.out.println(mesg);
			try {
				Thread.sleep(100);	// 100 msec
			} catch (InterruptedException e) {
				return;
			}
		}
		System.out.println(mesg + " thread all done.");
	}
}
// END main
