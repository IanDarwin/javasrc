package numbers;

import java.text.NumberFormat;

/*
 * Format a number our way and the default way.
 */
// tag::main[]
public class NumFormat2 {

	public static final double data[] = {
		0, 1, 22d/7, 100.2345678
	};

	public static void main(String[] av) { 
		// Get a format instance
		NumberFormat form = NumberFormat.getInstance();

		// Set it to look like 999.99[99]
		form.setMinimumIntegerDigits(3);
		form.setMinimumFractionDigits(2);
		form.setMaximumFractionDigits(4);

		// Now print using it.
		for (double d : data) {
			System.out.println(
				d + "\tformats as " + form.format(d));
		}
	}
}
// end::main[]
