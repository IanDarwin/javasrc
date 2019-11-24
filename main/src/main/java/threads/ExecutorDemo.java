package threads;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Simple demo of the Java5 Executor FixedThreadPool; start a number of threads, 
 * some of which are slow, and eventually the Executor will implement throttling 
 * because we set an upper bound on the number of threads it is to allow running.
 */
public class ExecutorDemo {

	private final Executor myThreadPool;

	private boolean done = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ExecutorDemo().runDemo();
	}

	public ExecutorDemo() {
		super();
		myThreadPool = Executors.newFixedThreadPool(5);
	}

	Random random = new Random();

	class DemoRunnable implements Runnable {
		int nSeconds;

		DemoRunnable() {
			nSeconds = random.nextInt(120);
		}

		@Override
		public String toString() {
			return super.toString() + "; nSeconds = " + nSeconds;
		}

		public void run() {
			System.out.println("Starting " + this);
			try {
				Thread.sleep(nSeconds * 1000);
			} catch (InterruptedException e) {
				// nothing to do
			}
			System.out.println("Stopping " + this);
		}
	}

	private void runDemo() {
		while (!done) {
			// Start a new thread running
			myThreadPool.execute(new DemoRunnable());
			try {
				Thread.sleep(5 * 1000);
			} catch (InterruptedException e) {
				// nothing to do
			}
		}
	}
}
