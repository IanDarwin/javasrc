package numbers;

import java.time.LocalDate;
import com.darwinsys.numbers.RomanNumberFormat;

/** Print the current year in Roman Numerals */
// tag::main[]
public class RomanYear {

	public static void main(String[] argv) {

		RomanNumberFormat rf = new RomanNumberFormat();
		int year = LocalDate.now().getYear();

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
// end::main[]
