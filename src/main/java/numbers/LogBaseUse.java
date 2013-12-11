package numbers;

/**
 * Log to arbitrary base
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class LogBaseUse {
	// BEGIN main
	public static void main(String[] argv) {
		double d = LogBase.log_base(10, 10000);
		System.out.println("log10(10000) = " + d);
	}
	// END main
}
