package numbers;

import java.util.Calendar;

public class RomanNumberSimple {
	public static void main(String[] args) {
		RomanNumberFormat nf = new RomanNumberFormat();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		System.out.println(year + " -> " + nf.format(year));
	}
}
