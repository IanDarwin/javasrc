package lang;

import java.util.concurrent.*;

/** Java's memory model allows the JVM to re-order operations (e.g., for
 * optimization purposes) so long as the thread performing those operations
 * gets the same result - without regard for other threads that might share
 * that data! Unlikely in this simple isolated case, but imagine it inside
 * a large *and long-running* application.
 */
public class HappensBefore {
	static ExecutorService threadPool = Executors.newSingleThreadExecutor();
	static boolean ready;
	static int val;
	public static void main(String[] args) {
		threadPool.submit( () -> {
			while (!ready) {
				Thread.yield();
			}
			System.out.println(val);	// Will it print 0 or 3 or ...?
		
		});
		val = 3;		// subject to reordering
		ready = true;	// subject to reordering
		threadPool.shutdown();
	}
}
	
