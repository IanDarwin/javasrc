package unsafe;

import sun.misc.Unsafe;
import java.lang.reflect.*;

/**
 * Use the well-named Unsafe class to get the Unix "load average" if possible.
 */
// tag::main[]
public class LoadAverage {
	public static void main(String[] args) throws Exception { 
		Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		Unsafe unsafe = (Unsafe) f.get(null);
		int numElements = 3;
		double loadAverage[] = new double[numElements];
		unsafe.getLoadAverage(loadAverage, numElements);
		for (double d : loadAverage) {
			System.out.printf("%4.2f ", d);
		}
		System.out.println();
	}
}
// end::main[]
