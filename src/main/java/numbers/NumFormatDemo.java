package numbers;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/*
 * Format a number our way and the default way.
 */
public class NumFormatDemo {
	// tag::part[]1
	/** A number to format */
	public static final double intlNumber = 1024.25;
	/** Another number to format */
	public static final double ourNumber = 100.2345678;
	// end::part[]1

	/** The main (and only) method in this class. */
	public static void main(String[] av) { 

		// tag::part[]2
		NumberFormat defForm = NumberFormat.getInstance();
		NumberFormat ourForm = new DecimalFormat("##0.##");
		// toPattern() will reveal the combination of #0., etc
		// that this particular Locale uses to format with!
		System.out.println("defForm's pattern is " +
			((DecimalFormat)defForm).toPattern());
		System.out.println(intlNumber + " formats as " +
			defForm.format(intlNumber));
		System.out.println(ourNumber + " formats as " +
			ourForm.format(ourNumber));
		System.out.println(ourNumber + " formats as " +
			defForm.format(ourNumber) + " using the default format");
		// end::part[]2
	}
}
