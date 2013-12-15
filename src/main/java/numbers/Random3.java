package numbers;

import java.util.*;

/** Demonstrate the better way of getting random numbers,
 * using java.util.Random.next*().
 */
public class Random3 {
	public static void main(String[] argv) {
		// java.util.Random methods are non-static, do need to construct Math
		// BEGIN main
		Random r = new Random();
		for (int i=0; i<10; i++)
		System.out.println("A gaussian random double is " + r.nextGaussian());
		// END main
	}
}
