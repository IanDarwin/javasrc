import java.util.*;

/** Demonstrate two ways of getting random numbers,
 * using java.lang.Math.Random() and java.util.Random.nextDouble().
 */
public class Randoms {
	public static void main(String argv[]) {
		// java.lang.Math.random() is static, don't need to construct Math
		System.out.println("A random from java.lang.Math is " + Math.random());
		// java.util.Random methods are non-static, do need to construct Math
		Random r = new Random();
		System.out.println("A random from java.util.Random is " + r.nextDouble());
	}
}
