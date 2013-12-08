package numbers;

/**
 * Demonstrate our own version round().
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class Round {
	/** We round a number up if its fraction exceeds this threshold. */
	public static final double THRESHOLD = 0.54;
	/* 
	 * Round floating values to integers.
	 * @Return the closest int to the argument.
	 * @param d A non-negative values to be rounded.
	 */
	static int round(double d) {
		if (d < 0) {
			throw new IllegalArgumentException("Value must be non-negative");
		}
		int di = (int)Math.floor(d);	// integral value below (or ==) d
		if ((d - di) > THRESHOLD) {
			return di + 1;
		} else {
			return di;
		}
	}
	
	public static void main(String[] argv) {
		for (double d = 0.1; d<=1.0; d+=0.01) {
			System.out.println("My way:  " + d + "-> " + round(d));
			System.out.println("Math way:" + d + "-> " + Math.round(d));
		}
	}
}
