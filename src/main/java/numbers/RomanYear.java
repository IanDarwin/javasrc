import java.util.*;

/** Print the current year in Roman Numerals */
public class RomanYear {
	public static void main(String[] argv) {
		int i;
		for (i=0; i<argv.length && !argv[i].equals("-"); i++) {
			System.out.print(argv[i]);	// e.g., "Copyright"
			System.out.print(' ');
		}
		RomanNumberFormat rf = new RomanNumberFormat();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		for (++i; i<argv.length; i++) {
			System.out.print(' ');
			if (argv[i].equals("-"))
				System.out.print(rf.format(year));
			else
				System.out.print(argv[i]);	// text
		}
		System.out.println();
	}
}
