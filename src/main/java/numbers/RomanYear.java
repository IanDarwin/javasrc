import java.util.*;

/** Print the current year in Roman Numerals */
public class RomanYear {
	public static void main(String argv[]) {
		int i;
		for (i=0; i<argv.length && !argv[i].equals("-"); i++) {
			System.out.print(argv[i]);	// e.g., "Copyright"
			System.out.print(' ');
		}
		RomanNumberFormat rf = new RomanNumberFormat();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		System.out.print(rf.format(year));
		for (++i; i<argv.length; i++) {
			System.out.print(' ');
			System.out.print(argv[i]);	// e.g., "Acme Enterprises"
		}
		System.out.println();
	}
}
