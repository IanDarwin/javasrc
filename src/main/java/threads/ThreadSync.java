/** 
 * Threaded demo application, as a Threads subclass.
 *
 * @author	Ian Darwin
 * @version	1.0
 */
public class ThreadSync extends Thread {
	String mesg;
	int count;

	/** Run does the work: print a message, "count" number of times */ 
	public void run() {
		while (count-- > 0) {
			println(mesg);
			try {
				Thread.sleep(100);	// 100 msec
			} catch (InterruptedException e) {
				return;
			}
		}
		println(mesg + " all done.");
	}

	/* The output routine has been synchronized to interlock the Threads */
	synchronized void println(String s) {
		System.out.println(s);
	}

	/**
	 * Construct a ThreadSync object.
	 * @param	String m	Message to display
	 * @param	int n		How many times to display it
	 */
	public ThreadSync(String m, int n) {
		count = n;
		mesg  = m;
		setName(m + " runner Thread");
	}

	/**
	 * Main program, test driver for ThreadSync class.
	 */
	public static void main(String[] argv) {
		new ThreadSync("Hello from X", 10).start();
		new ThreadSync("Hello from Y", 15).start();
	}
}
