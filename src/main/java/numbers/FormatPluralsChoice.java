import java.text.*;

/**
 * Format a plural correctly, using a ChoiceFormat.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class FormatPlurals0 {
	//+
	static double[] limits = { 0, 1, 2 };
	static String[] formats = { "items", "item", "items"};
	static ChoiceFormat myFormat = new ChoiceFormat(limits, formats);

	public static void main(String[] argv) {
		report(0);
		report(1);
		report(2);
	}
	public static void report(int n) {
		System.out.println("We used " + n + " " + myFormat.format(n));
	}
	//-
}
