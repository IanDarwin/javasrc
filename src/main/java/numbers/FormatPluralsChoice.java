import java.text.*;

/**
 * Format a plural correctly, using a ChoiceFormat.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class FormatPluralsChoice extends FormatPlurals {
	static double[] limits = { 0, 1, 2 };
	static String[] formats = { "items", "item", "items"};
	static ChoiceFormat myFormat = new ChoiceFormat(limits, formats);

	public static void main(String[] argv) {
		report(0);	// inherited method
		report(1);
		report(2);
	}
}
