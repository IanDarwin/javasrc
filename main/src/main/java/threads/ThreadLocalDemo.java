package threads;

/**
 * Demonstrate use of ThreadLocal, a mechanism that provides per-thread copies of
 * some reference, to facilitate thread-safe code.
 * ThreadLocal is a standard Java class.
 * There are two basic ways of putting the value into a ThreadLocal:
 * <ol>
 * 	<li>Subclass ThreadLocal and have initialValue() generate the value;
 * 	<li>Instantiate ThreadLocal and use put() to put the value in.
 * </ol>
 * Both methods are good. This uses the first method.
 */
public class ThreadLocalDemo extends Thread {

	ThreadLocalDemo(String name) {
		super.setName(name);
	}

// tag::main[]
	/** This ThreadLocal holds the Client reference for each Thread.
	 * Make ThreadLocal instance static, to show that it is not an instance variable
	 * but is derived from Thread.currentThead() regardless of static/instance access
	 */
	private static ThreadLocal<Client> myClient = new ThreadLocal<Client>() {
		// initialValue() is called magically when you first call get().
		@Override
		protected synchronized Client initialValue() {
			return new Client(++clientNum);
		}
	};
	// IRL this method would do something useful.
	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() +
			" sees client " + myClient.get());
	}

	public static void main(String[] args) {
		new ThreadLocalDemo("demo 1").start();
		new ThreadLocalDemo("demo 2").start();
		Thread.yield();
		System.out.println("Main program sees client " + myClient.get());
	}

	/** A serial number for clients */
	private static int clientNum = 0;

	/** Simple data class, in real life clients would have more fields! */
	private static record Client(int clientNum) { }
// end::main[]
}
