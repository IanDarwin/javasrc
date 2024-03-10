package threads;

/**
 * Another demonstration of ThreadLocal, a mechanism that provides per-thread copies of
 * some reference, to facilitate thread-safe code.
 * Like ThreadLocalDemo but using aggregation instead of subclassing the ThreadLocal.
 */
public class ThreadLocalDemo3 extends Thread {

	ThreadLocalDemo3(String name) {
		super.setName(name);
	}

	/**
	 * This ThreadLocal holds the Client reference for each Thread.
	 * Make ThreadLocal instance static, to show that it is not an instance variable
	 * but is derived from Thread.currentThead() regardless of static/instance access
	 */
	private static ThreadLocal<Client> myClient = new ThreadLocal<Client>();

	// IRL this method would do something useful.
	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() +
			" sees client " + getClient());
	}

	synchronized Client getClient() {
		if (myClient.get() == null) {
			myClient.set(new Client(++clientNum));
		}
		return myClient.get();
	}

	public static void main(String[] args) {
		new ThreadLocalDemo3("Demo 1").start();
		new ThreadLocalDemo3("Demo 2").start();
		Thread.yield();
		// Following always gives null as we don't have an initValue() method.
		System.out.println("Main program sees client " + myClient.get());
	}

	/** A serial number for clients */
	private static int clientNum = 0;

	/** Simple data class; real life clients would have more fields! */
	private static record Client(int clientNum) { }
}
