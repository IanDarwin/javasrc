/**
 * Floating-point comparisons.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class FloatCmp {
	final static final double EPSILON = 0.0000001;
	public static void main(String[] argv) {
		double da = 3 * .3333333333;
		double db = 0.99999992857;

		// Compare two numbers that are expected to be close.
		if (da == db) {
			System.out.println("Java considers " + da + "==" + db);
		// else compare with our own equals method
		} else if (equals(da, db, 0.0000001)) {
			System.out.println("True within epsilon " + EPSILON);
		} else {
			System.out.println(da + " != " + db);
		}

		// Show that comparing two NaNs is not a good idea:
		double d1 = Double.NaN;
		double d2 = Double.NaN;
		if (d1 == d2)
			System.err.println("Comparing two NaNs incorrectly returns true.");
		if (!new Double(d1).equals(new Double(d2)))
			System.err.println("Double(NaN).equal(NaN) incorrectly returns false.");
	}

	/** Compare two doubles within a given epsilon */
	public static boolean equals(double a, double b, double eps) {
		if (a==b) return true;
		// If the difference is less than epsilon, treat as equal.
		return Math.abs(a - b) < eps;
	}

	/** Compare two doubles, using default epsilon */
	public static boolean equals(double a, double b) {
		if (a==b) return true;
		// If the difference is less than epsilon, treat as equal.
		return Math.abs(a - b) < EPSILON * Math.max(Math.abs(a), Math.abs(b));

}
