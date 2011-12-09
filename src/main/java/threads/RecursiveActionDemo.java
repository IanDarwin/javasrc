package threads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinDemo extends RecursiveAction {

	private static final long serialVersionUID = 3742774374013520116L;

	static int[] raw = { 19, 3, 0, -1, 57, 24, 65, Integer.MAX_VALUE, 42, 0, 3, 5 };
	static int[] sorted = null;
	
	int[] mSource;
	int[] mDest;
	int mLength;
	int mStart;
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
	
	public ForkJoinDemo(int[] src, int start, int length, int[] dst) {
	    mSource = src;
	    mStart = start;
	    mLength = length;
	    mDest = dst;
	  }

	@Override
	protected void compute() {
		System.out.println("ForkJoinDemo.compute()");
		if (mLength <= THRESHOLD) { // Compute Directly
			for (int i = mStart; i < mStart + mLength; i++) {
				mDest[i] = mSource[i] * mSource[i];
			}
		} else {			        // Divide and Conquer    
			int split = mLength / 2;
			invokeAll(new ForkJoinDemo(mSource, mStart,         split,           mDest),
					  new ForkJoinDemo(mSource, mStart + split, mLength - split, mDest));
		}
	}
}
