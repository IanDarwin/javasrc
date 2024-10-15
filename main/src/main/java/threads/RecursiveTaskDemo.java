package threads;

import java.io.Serial;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

// tag::main[]
/**
 * Demonstrate the Fork-Join Framework to average a large array.
 * Running this on a multi-core machine as e.g., 
 * $ time java threads.RecursiveTaskDemo
 * shows that the CPU time is always greater than the elapsed time,
 * indicating that we are making use of multiple cores.
 * That said, it is a somewhat contrived demo.
 *
 * Use RecursiveTask<T> where, as in this example, each call returns
 * a value that represents the computation for its subset of the overall task.
 * @see RecursiveActionDemo when each computation does not return a value,
 * e.g., when each is just working on some section of a large array.
 */
public class RecursiveTaskDemo extends RecursiveTask<Long> {

	@Serial
	private static final long serialVersionUID = 74277437329822011L;

	static final int N = 10000000;
	final static int THRESHOLD = 500;

	int[] data;
	int start, length;
	
	public static void main(String[] args) {
		int[] source = new int[N];
		loadData(source);
		RecursiveTaskDemo fb = new RecursiveTaskDemo(source, 0, source.length);
		ForkJoinPool pool = new ForkJoinPool();
		long before = System.currentTimeMillis();
		pool.invoke(fb);
		long after = System.currentTimeMillis();
		long total = fb.getRawResult();
		long avg = total / N;
		System.out.println("Average: " + avg);
		System.out.println("Time :" + (after - before) + " mSec");
		pool.close();
	}

	static void loadData(int[] data) {
		Random r = new Random();
		for (int i = 0; i < data.length; i++) {
			data[i] = r.nextInt();
		}
	}
	
	public RecursiveTaskDemo(int[] data, int start, int length) {
	    this.data = data;
	    this.start = start;
	    this.length = length;
	}

	@Override
	protected Long compute() {
		if (length <= THRESHOLD) { // Compute Directly
			long total = 0;
			for (int i = start; i < start + length; i++) {
				total += data[i];
			}
			return total;
		} else {			        // Divide and Conquer    
			int split = length / 2;
			RecursiveTaskDemo t1 =
				new RecursiveTaskDemo(data, start,         split);
			t1.fork();
			RecursiveTaskDemo t2 =
				new RecursiveTaskDemo(data, start + split, length - split);
			return t2.compute() + t1.join();
		}
	}
}
// end::main[]
