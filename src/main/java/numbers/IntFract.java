/**
 * Multiply a decimal fraction, not using floating point
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class IntFract {
	public static void main(String[] argv) {
		//+
		int a = 100;
		int b = a*5/7;
		System.out.println("5/7 of " + a + " is " + b);
		// Just for fun, do it again in floating point.
		final double FRACT = 0.7142857132857;
		int c = (int)(100*FRACT);
		System.out.println(FRACT + " of " + a + " is " + c);
		//-
	}
}
