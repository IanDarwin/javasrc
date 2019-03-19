package lang;

/**
 * Demonstrate pre- and post-increment/decrement.
 * Currently only shows the steps used in a slide in LT471.
 */
public class PrePostIncrDecr {
	public static void main(String[] args) {
		System.out.println(" i  j  k");
		int i = 10;
		System.out.printf("%2d\n", i);
		int j = i++;
		System.out.printf("%2d %2d\n", i, j);
		int k = --j;
		System.out.printf("%2d %2d %2d\n", i, j, k);
	}
}
