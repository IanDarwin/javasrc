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
}
