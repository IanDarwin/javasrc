/**
 * Show real values.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class StrictFP {
	public static strictfp void main(String[] argv) {
		double alpha = 0.3e200;
		double beta = 3.0e200;
		System.out.println(mulNotStrict(alpha, beta));
		System.out.println(mulStrictFP(alpha, beta));
	}

	static double mulNotStrict(double a, double b) {
		return a * b / 2;
	}
	static strictfp double mulStrictFP(double a, double b) {
		return a * b / 2;
	}
}
