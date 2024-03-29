package datetimeold;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/** The "best" way to format a date/time is to use
 * the DateFormat.getInstance() method, which will
 * be localized automatically.
 */
public class DateFormatBest {
	public static void main(String[] args) {
		Date today = new Date();

		DateFormat df = DateFormat.getInstance();
		System.out.println(df.format(today));

		DateFormat df_fr = 
			DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);
		System.out.println(df_fr.format(today));
	}
}
