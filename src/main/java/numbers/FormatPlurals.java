/**
 * Format a plural correctly.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class FormatPlurals {
	public static void main(String argv[]) {
		report(0);
		report(1);
		report(2);
	}
	public static void report(int n) {
		System.out.println("We used " + n + " item" + (n==1?"":"s"));
	}
}
