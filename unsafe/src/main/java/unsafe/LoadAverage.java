package unsafe;

import sun.misc.Unsafe;
import java.lang.reflect.*;

/**
 * Use the well-named Unsafe class to get the Unix "load average" if possible.
 */
public class LoadAverage {
	public static void main(String[] args) throws Exception { 
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		int nelem = 3;
		double loadAvg[] = new double[nelem];
		unsafe.getLoadAverage(loadAvg, nelem);
		for (double d : loadAvg) {
			System.out.printf("%4.2f ", d);
		}
		System.out.println();
	}
}
