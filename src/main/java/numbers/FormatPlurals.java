package numbers;

/**
 * Format a plural correctly, by hand.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class FormatPlurals {
	public static void main(String[] argv) {
		report(0);
		report(1);
		report(2);
	}

	/** report -- using conditional operator */
	public static void report(int n) {
		System.out.println("We used " + n + " item" + (n==1?"":"s"));
	}
}
// END main
