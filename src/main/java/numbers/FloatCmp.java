/**
 * Floating-oint comparisons.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class FloatCmp {
	public static void main(String[] argv) {
	//+
		double d1 = Double.NaN;
		double d2 = Double.NaN;
		if (d1 == d2)
			System.err.println("Comparing two NaNs incorrectly returns true.");
		if (!new Double(d1).equals(new Double(d2)))
			System.err.println("Double(NaN).equal(NaN) incorrectly returns false.");
	//-
	}
}
