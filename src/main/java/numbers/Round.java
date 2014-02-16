package numbers;

/**
 * Demonstrate our own version of round().
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @author Alex Stangl - simplified the algorithm
 */
// BEGIN main
public class Round {
	/** We round a number up if its fraction exceeds this threshold. */
	public static final double THRESHOLD = 0.54;

	/* 
	 * Round floating values to integers.
	 * @return the closest int to the argument.
	 * @param d A non-negative values to be rounded.
	 */
	public static int round(double d) {
		return (int)Math.floor(d + 1.0 - THRESHOLD);
	}
	
	public static void main(String[] argv) {
		for (double d = 0.1; d<=1.0; d+=0.05) {
			System.out.println("My way:  " + d + "-> " + round(d));
			System.out.println("Math way:" + d + "-> " + Math.round(d));
		}
	}
}
// END main
