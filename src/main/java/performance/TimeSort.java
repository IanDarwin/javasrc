package performance;

import java.util.Arrays;

/**
 * Time a bunch of sorting.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class TimeSort {
	public static void main(String[] argv) {
		final int N = 1024 * 1024;
		java.util.Random r = new java.util.Random();
		long t0 = System.currentTimeMillis();
		int data[] = new int[N];
		for  (int i=0; i<N; i++)
			data[i] = r.nextInt();
		long t1 = System.currentTimeMillis();
		Arrays.sort(data);
		long t2 = System.currentTimeMillis();
		long randTime = t1 - t0;
		long sortTime = t2 - t1;
		System.out.println(
			 "randTime="  + Double.toString(randTime/1000D) + 
			", sortTime=" + Double.toString(sortTime/1000D));
	}
}
