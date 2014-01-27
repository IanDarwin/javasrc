package datetimeold;

import java.util.Calendar;
import java.util.Locale;

/** Get the months of the year in the current Locale; only works for Gregorian calendar! */
public class GetMonthNames {

	public static void main(String[] args) {
		final Calendar cal = Calendar.getInstance();
		for (int i = 0; i < 12; i++) {
			cal.set(Calendar.MONTH, i);
			System.out.println(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
		}
	}

}
