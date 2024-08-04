package threads;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Simple demo of the Java5 Executor FixedThreadPool; start a number of threads, 
 * some of which are slow, and eventually the Executor will implement throttling 
 * because we set an upper bound on the number of threads it is to allow running.
 */
public class ExecutorDemo {

	private static final ExecutorService myThreadPool = Executors.newVirtualThreadPerTaskExecutor();
	private static final Random random = new Random();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			// Start a new thread running
			myThreadPool.execute(new DemoRunnable(i));
			try {
				Thread.sleep(2 * 1000);
			} catch (InterruptedException e) {
				// nothing to do
			}
		}
		myThreadPool.shutdown();	// drain then stop
	}

	/**
	 * The "task" that will be run in the thread pool.
	 */
	static class DemoRunnable implements Runnable {
		int number;
		int nSeconds;

		DemoRunnable(int number) {
			this.number = number;
			nSeconds = random.nextInt(15);
		}

		@Override
		public String toString() {
			var t = Thread.currentThread();
			return t.getName() + "; nSeconds = " + nSeconds;
		}

		public void run() {
			Thread.currentThread().setName("Demo#" + number);
			System.out.println("Starting " + this);
			try {
				Thread.sleep(nSeconds * 1000);
			} catch (InterruptedException e) {
				// nothing to do
			}
			System.out.println("Finished " + this);
		}
	}
}

