package threads;

/** 
 * Threaded demo application, using lambda block Runnable.
 * @author	Ian Darwin
 */
// tag::main[]
public class ThreadsDemo4 {
	private Thread t;
	private int count;
	private String message;

	/**
	 * Main program, test driver for ThreadsDemo4 class.
	 */
	public static void main(String[] argv) {
		new ThreadsDemo4("Hello from X", 10).start();
		new ThreadsDemo4("Hello from Y", 15).start();
	}

	/**
	 * Construct a ThreadDemo object
	 * @param message Message to display
	 * @param count How many times to display it
	 */
	public ThreadsDemo4(final String message, int count) {
		this.count = count;
		this.message = message;
	}
	void start() {
		t = new Thread(() -> {
				while (count-- > 0) {
					System.out.println(message);
					try {
						Thread.sleep(100);	// 100 msec
					} catch (InterruptedException e) {
						return;
					}
				}
				System.out.println(message + " thread all done.");
			
		});
		t.setName(message + " runner Thread");
		t.start();
	}
}
// end::main[]
