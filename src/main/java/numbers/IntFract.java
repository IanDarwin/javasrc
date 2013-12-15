package numbers;

/**
 * Multiply a decimal fraction, not using floating point
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class IntFract {
	public static void main(String[] argv) {
		// BEGIN main
		int a = 100;
		int b = a*5/7;
		System.out.println("5/7 of " + a + " is " + b);
		// Just for fun, do it again in floating point.
		final double FRACT = 0.7142857132857;
		int c = (int)(a*FRACT);
		System.out.println(FRACT + " of " + a + " is " + c);
		// END main
	}
}
