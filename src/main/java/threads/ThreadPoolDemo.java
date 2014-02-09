package threads;

import java.util.*;
import java.util.concurrent.*;

/**
 * Simple demo of the Java5 ThreadPool; run multiple threads.
 */
public class ThreadPoolDemo {

	private static boolean done = false;

	public static void main(String[] args) throws Throwable {
		final ExecutorService pool = Executors.newFixedThreadPool(5);
		List<Future<Integer>> futures = new ArrayList<>(5);
		for (int i = 0; i < 5; i++) {
			Future<Integer> f = pool.submit(new DemoRunnable(i));
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

		// @Override
		public Integer call() {
			while (!done) {
				try {
					Thread.sleep(500);
					System.out.println("Running " + this);
					++numRuns;
				} catch (InterruptedException e) {
					// nothing to do
				}
			}
			System.out.println("Stopping " + this);
			return numRuns;
		}
	}
}
