/**
 * Log to arbitrary base
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class LogBase {
	//+
	public static double log_base(double base, double value) {
		return Math.log(value) / Math.log(base);
	}
	//-
	public static void main(String argv[]) {
		double d = log_base(10, 10000);
		System.out.println("log10(10000) = " + d);
	}
}
