package structure;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Poorly-named example showing that Vector being synchronized is not the only protection you need
 * It is NOT Vector that is borked, but the assumption that using it would solve all your problems :-)
 * Will usually print something like "BUSTED! Expected 200000 but actual 199997" on a low-end computer 
 * that is current in 2016 or 2017. 
 * Explain why it fails, and why it will eventually start to work correctly.
 */
public class VectorMisused {

	public static final int NTHREAD = 20;
	public static final int NLOOP = 10000;

	static final Vector<MyClass> v = new Vector<>();
	static final ExecutorService pool = Executors.newFixedThreadPool(NTHREAD);
	static final AtomicInteger count = new AtomicInteger(0);

	static class MyClass {
		int val = 0;
	}

	public static void main(String[] args) throws InterruptedException {

		v.add(new MyClass());

		for (int i = 0; i < NTHREAD; i++) {
			pool.submit(()->{
				for (int j = 0; j < NLOOP; j++) {
					MyClass m = v.getFirst();
					m.val++;
					count.incrementAndGet();
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(100, TimeUnit.SECONDS);
		int expected = count.get();
		int actual = v.getFirst().val;
		System.out.printf((expected==actual?"Surprised!":"BUSTED!") + " Expected %d but actual %d\n", count.get(), v.getFirst().val);
	}
}
