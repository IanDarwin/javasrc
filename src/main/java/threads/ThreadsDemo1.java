/** 
 * Threaded demo application, as a Threads subclass.
 *
 * @author	Ian Darwin
 *
 * @version	1.0
 */
public class ThreadsDemo1 extends Thread {
	String mesg;
	int count;

	/** Run does the work: print a message, "count" number of times */ 
	public void run() {
		while (count-- > 0) {
			System.out.println(mesg);
			try {
				Thread.sleep(100);	// 100 msec
			} catch (InterruptedException e) {
				return;
			}
		}
		System.out.println(mesg + " all done.");
	}

	/**
	 * Construct a ThreadsDemo1 object.
	 * @param	String m	Message to display
	 * @param	int n		How many times to display it
	 */
	public ThreadsDemo1(String m, int n) {
		count = n;
		mesg  = m;
	}

	/**
	 * Main program, test driver for ThreadsDemo1 class.
	 */
	public static void main(String argv[]) {
		// could say: new ThreadsDemo1("Hello from X", 10).run();
		// could say: new ThreadsDemo1("Hello from Y", 15).run();
		new ThreadsDemo1("Hello from X", 10).start();
		new ThreadsDemo1("Hello from Y", 15).start();
	}
}
