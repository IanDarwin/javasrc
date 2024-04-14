package threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Another demonstration of ThreadLocal, a mechanism that provides per-thread copies of
 * some reference, to facilitate thread-safe code.
 * Same as ThreadLocalDemo but using AtomicInteger instead of incrementing
 * a static int variable in a protected initValue() method - faster.
 */
public class ThreadLocalDemo2 extends Thread {

	ThreadLocalDemo2(String name) {
		super.setName(name);
	}

	/**
	 * This ThreadLocal holds the Client reference for each Thread.
	 * Make ThreadLocal instance static, to show that it is not an instance variable
	 * but is derived from Thread.currentThead() regardless of static/instance access
	 */
	private static ThreadLocal<Client> myClient = new ThreadLocal<Client>() {
		// initialValue() is called magically when you first call get().
		@Override
		protected Client initialValue() {
			return new Client(clientNum.incrementAndGet());
		}
	};

	// IRL this method would do something useful.
	@Override
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() +
			" sees client " + myClient.get());
	}

	public static void main(String[] args) {
		new ThreadLocalDemo2("Demo 1").start();
		new ThreadLocalDemo2("Demo 2").start();
		Thread.yield();
		System.out.println("Main program sees client " + myClient.get());
	}

	/** A serial number for clients */
	private static final AtomicInteger clientNum = new AtomicInteger(0);

	/** Simple data class; real life clients would have more fields! */
	private static record Client(int clientNum) { }
}
