package numbers;

/**
 * Floating-point comparisons.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class FloatCmp {
	
	final static double EPSILON = 0.0000001;
	
	public static void main(String[] argv) {
		double da = 3 * .3333333333;
		double db = 0.99999992857;

		// Compare two numbers that are expected to be close.
		if (da == db) {
			System.out.println("Java considers " + da + "==" + db);
		// else compare with our own equals overload
		} else if (equals(da, db, 0.0000001)) {
			System.out.println("Equal within epsilon " + EPSILON);
		} else {
			System.out.println(da + " != " + db);
		}
		
		System.out.println("NaN prints as " + Double.NaN);

		// Show that comparing two NaNs is not a good idea:
		double nan1 = Double.NaN;
		double nan2 = Double.NaN;
		if (nan1 == nan2)
			System.out.println("Comparing two NaNs incorrectly returns true.");
		else
			System.out.println("Comparing two NaNs correctly reports false.");

		if (new Double(nan1).equals(new Double(nan2)))
			System.out.println("Double(NaN).equals(NaN) correctly returns true.");
		else
			System.out.println("Double(NaN).equals(NaN) incorrectly returns false.");
	}

	/** Compare two doubles within a given epsilon */
	public static boolean equals(double a, double b, double eps) {
		if (a==b) return true;
		// If the difference is less than epsilon, treat as equal.
		return Math.abs(a - b) < eps;
	}

	/** Compare two doubles, using default epsilon */
	public static boolean equals(double a, double b) {
		return equals(a, b, EPSILON);
	}
}
// END main
