/**
 * Demonstrate our own version round().
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Round {
	//+
	/** We round a number up if its fraction exceeds this threshold. */
	public static final double THRESHOLD = 0.54;
	/* Return the closest long to the argument.
	 * ERROR CHECKING OMITTED.
	 */
	static long round(double d) {
		long di = (long)Math.floor(d);	// integral value below (or ==) d
		if ((d - di) > THRESHOLD)
			return di + 1;
		else return di;
	}
	//-
	public static void main(String[] argv) {
		for (double d = 0.1; d<=1.0; d+=0.01)
			System.out.println(d + "-> " + round(d));
	}
}
