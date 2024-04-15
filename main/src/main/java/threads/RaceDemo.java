package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceDemo {
	static ExecutorService threadPool = Executors.newFixedThreadPool(10);
	static final int HOWMANY = 100,
	/** A value that cannot be put in by foo() */
	POISON = -42;
	static int[] data = new int[HOWMANY];
	static int ix;

	static void foo() {
		System.out.println("RaceDemo.foo(): ix = " +ix);
		data[ix++] = ix;
	}

	// The statement in foo() is actually several steps:
	// 1 read ix
	// 2 add one to the value read in step 1
	// 3 write that back to ix
	// 4 save ix at data[the ix we read in step 1]
	//
	// What happens if the Thread Scheduler 'ticks' in between
	// steps 2 and 3? We might end up with this in ix:
	// [0] | [1]    | [2]
	// | 2 | POISON | 3

	public static void main(String[] args) throws Exception {
		for (int i = 0; i < HOWMANY; i++) {
			data[i] = POISON;
		}
		for (int i = 0; i < HOWMANY; i++) {
			threadPool.submit(() -> foo());
		}
		threadPool.awaitTermination(2, TimeUnit.SECONDS);
		threadPool.shutdown();
		for (int i = 0; i < HOWMANY; i++) {
			if (data[i] == POISON)
			throw new Exception("Race lost at " + i);
		}
		System.out.println("No races lost -- this time");
	}
}
