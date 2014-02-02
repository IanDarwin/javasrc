package threads;

/** 
 * Threaded demo application, using lambda block Runnable.
 * @author	Ian Darwin
 */
// BEGIN main
public class ThreadsDemo4 {
	private String mesg;
	private Thread t;
	private int count;

	/**
	 * Main program, test driver for ThreadsDemo4 class.
	 */
	public static void main(String[] argv) {
		new ThreadsDemo4("Hello from X", 10);
		new ThreadsDemo4("Hello from Y", 15);
	}

	/**
	 * Construct a ThreadDemo object
	 * @param m Message to display
	 * @param n How many times to display it
	 */
	public ThreadsDemo4(final String mesg, int n) {
		count = n;
		t = new Thread(() -> {
				while (count-- > 0) {
					System.out.println(mesg);
					try {
						Thread.sleep(100);	// 100 msec
					} catch (InterruptedException e) {
						return;
					}
				}
				System.out.println(mesg + " thread all done.");
			
		});
		t.setName(mesg + " runner Thread");
		t.start();
	}
}
// END main
