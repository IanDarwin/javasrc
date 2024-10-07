package threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Simple demo of the Java ThreadPools; run multiple threads.
 */
public class ThreadPoolDemo {

	private static final int HOWMANY = 5;
	private static boolean done = false;

	public static void main(String[] args) throws Exception {
		// tag::main[]
		final ExecutorService pool = Executors.newFixedThreadPool(HOWMANY);
		List<Future<Integer>> futures = new ArrayList<>(HOWMANY);
		for (int i = 0; i < HOWMANY; i++) {
			Future<Integer> f = pool.submit(new DemoRunnable(i));
			System.out.println("Got 'Future' of type " + f.getClass());
			futures.add(f);
		}
		Thread.sleep(3 * 1000);
		done = true;
		for (Future<Integer> f : futures) {
			System.out.println("Result " + f.get());
		}
		pool.shutdown();
	}

	static class DemoRunnable implements Callable<Integer> {
		int time, numRuns;
		DemoRunnable(int t) { time = t; }

		@Override public Integer call() {
			while (!done) {
				System.out.println("Running " + Thread.currentThread());
				++numRuns;
			}
			System.out.println("Stopping " + this);
			return numRuns;
		}
	}
	// end::main[]
}
