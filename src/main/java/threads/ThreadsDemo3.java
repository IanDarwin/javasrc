package threads;

/** 
 * Threaded demo application, using inner class as Runnable.
 * @author	Ian Darwin
 */
// BEGIN main
public class ThreadsDemo3 {
	private Thread t;
	private int count;

	/**
	 * Main program, test driver for ThreadsDemo3 class.
	 */
	public static void main(String[] argv) {
		new ThreadsDemo3("Hello from X", 10);
		new ThreadsDemo3("Hello from Y", 15);
	}

	/**
	 * Construct a ThreadDemo object
	 * @param m Message to display
	 * @param n How many times to display it
	 */
	public ThreadsDemo3(final String mesg, int n) {
		count = n;
		t = new Thread(new Runnable() {
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
		});
		t.setName(mesg + " runner Thread");
		t.start();
	}
}
// END main
