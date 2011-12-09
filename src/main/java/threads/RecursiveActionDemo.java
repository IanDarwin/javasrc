package threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinDemo extends RecursiveAction {

	private static final long serialVersionUID = 3742774374013520116L;

	static int[] raw = { 19, 3, 0, -1, 57, 24, 65, Integer.MAX_VALUE, 42, 0, 3, 5 };
	static int[] sorted = null;
	
	int[] source;
	int[] dest;
	int length;
	int start;
	final static int THRESHOLD = 4;
	
	public static void main(String[] args) {
		sorted = new int[raw.length];
		ForkJoinDemo fb = new ForkJoinDemo(raw, 0, raw.length, sorted);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(fb);
		System.out.print('[');
		for (int i : sorted) 
			System.out.print(i + ",");
		System.out.println(']');
	}
	
	public ForkJoinDemo(int[] src, int start, int length, int[] dest) {
	    this.source = src;
	    this.start = start;
	    this.length = length;
	    this.dest = dest;
	  }

	@Override
	protected void compute() {
		System.out.println("ForkJoinDemo.compute()");
		if (length <= THRESHOLD) { // Compute Directly
			for (int i = start; i < start + length; i++) {
				dest[i] = source[i] * source[i];
			}
		} else {			        // Divide and Conquer    
			int split = length / 2;
			invokeAll(new ForkJoinDemo(source, start,         split,           dest),
					  new ForkJoinDemo(source, start + split, length - split, dest));
		}
	}
}
