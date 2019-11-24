package threads;

/**
 * The fastest way to a Threading deadlock
 */
public class InstantDeadlock {
	public static void main(String[] args) throws Exception {
		Thread.currentThread().join();
	}
}
