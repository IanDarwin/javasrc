package numbers;

import java.util.*;

/** Print the current year in Roman Numerals */
// BEGIN main
public class RomanYear {

	public static void main(String[] argv) {

		RomanNumberFormat rf = new RomanNumberFormat();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		// If no arguments, just print the year.
		if (argv.length == 0) {
			System.out.println(rf.format(year));
			return;
		}
		
		// Else a micro-formatter: replace "-" arg with year, else print.
		for (int i=0; i<argv.length; i++) {
			if (argv[i].equals("-"))
				System.out.print(rf.format(year));
			else
				System.out.print(argv[i]);	// e.g., "Copyright"
			System.out.print(' ');
		}
		System.out.println();
	}
}
// END main
