package threads;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Demonstrate the Fork-Join Framework to average a large array.
 * Running this on a multi-core machine as e.g., 
 * $ time java threads.RecursiveTaskDemo
 * shows that the CPU time is always greater than the elapsed time,
 * indicating that we are making use of multiple cores.
 * That said, it is a somewhat contrived demo.
 *
 * In using the Fork Join Framework, you have to subclass ForkJoinTask
 * and provide a compute() method that does small items of work AND
 * divides larger items into smaller ones and recurses.
 * There are two main base classes, RecursiveAction and RecursiveTask.
 * Use RecursiveAction where compute() doesn't need to return a value
 * (as for the common case where each call updates part of an array).
 * Use RecursiveTask<T> where, as in this example, each call returns
 * a value that represents the computation for its subset of the overall task.
 *
 * @author Ian Darwin
 */
public class RecursiveTaskDemo extends RecursiveTask<Long> {

	private static final long serialVersionUID = 3742774374013520116L;

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
			RecursiveTaskDemo t1 = new RecursiveTaskDemo(data, start,         split);
			t1.fork();
			RecursiveTaskDemo t2 = new RecursiveTaskDemo(data, start + split, length - split);
			return t2.compute() + t1.join();
		}
	}
}
