import java.text.*;
import java.util.*;

/*
 * Format a number our way and the default way.
 */
public class NumFormatTest {
	//+
	/** A number to format */
	public static final double intlNumber = 1024.25;
	/** Another number to format */
	public static final double ourNumber = 100.23456;
	//-

	/** The main (and only) method in this class. */
	public static void main(String av[]) { 

		//+
		NumberFormat defForm = NumberFormat.getInstance();
		NumberFormat ourForm = new DecimalFormat("##.##");
		// toPattern() will reveal the combination of #0., etc
		// that this particular local uses to format with!
		System.out.println("defForm's pattern is " +
			((DecimalFormat)defForm).toPattern());
		System.out.println(defForm.format(intlNumber));

		// We also show using the default format to parse a number
		String input = "392.01";
		try {
			System.out.println(input + " -> " + defForm.parse(input));
		} catch (ParseException pe) {
			System.err.println(input + "not parseable!");
		}
	}
}
