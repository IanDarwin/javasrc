/** 
 * Threaded demo program, using Runnable, and main keeps running too,
 * hence the name ThreadsTwo.
 *
 * @author	Ian Darwin
 *
 * @version	1.0
 */
public class ThreadsTwo implements Runnable {
	String mesg;
	Thread t;
	int count;

	/**
	 * Main program, test driver for ThreadsTwo class.
	 */
	public static void main(String argv[]) {
		new ThreadsTwo("Hello from X", 10);
		for (int i=0; i<10; i++) 
			putMsg("Hello from main");
	}

	/**
	 * Construct a ThreadTwo object
	 *
	 * @param	String m	Message to display
	 * @param	int n		How many times to display it
	 */
	public ThreadsTwo(String m, int n) {
		count = n;
		mesg  = m;
		t = new Thread(this);
		t.start();
	}

	/** Run does the work. We override the run() method in Runnable. */ 
	public void run() {
		while (count-- > 0)
			putMsg(mesg);
		System.out.println(mesg + " thread all done.");
	}

	public static void putMsg(String s) {
		System.out.println(s);
		try {
			Thread.sleep(100);	// 100 msec
		} catch (InterruptedException e) {
			return;
		}
	}
}
