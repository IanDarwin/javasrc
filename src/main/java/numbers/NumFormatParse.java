package numbers;

import java.text.NumberFormat;
import java.text.ParseException;

/*
 * Parse a number using a NumberFormat.
 */
public class NumFormatParse {
	// BEGIN
	/** A number to parse */
	public static final String input = "4096.251";
	// END

	/** The main (and only) method in this class. */
	public static void main(String[] av) { 

		// BEGIN
		NumberFormat defForm = NumberFormat.getInstance();

		try {
			Number d = defForm.parse(input);
			System.out.println(input + 
				" parses as " + d +
				" and formats as " + defForm.format(d));
		} catch (ParseException pe) {
			System.err.println(input + "not parseable!");
		}
		// END
	}
}
