package numbers;

import java.time.LocalDate;

public class RomanNumberSimple {
	public static void main(String[] args) {
		// BEGIN main
		RomanNumberFormat nf = new RomanNumberFormat();
		int year = LocalDate.now().getYear();
		System.out.println(year + " -> " + nf.format(year));
		// END main
	}
}
