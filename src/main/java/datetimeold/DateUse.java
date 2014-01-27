package datetimeold;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/** Trivial class to show use of Date & Calendar objects */
public class DateUse {

	/** Typical main method ("main program") declaration */
	public static void main(String[] av) {

		Locale l1 = new Locale("en", "US"),
			l2 = new Locale("es", "ES");

		// Create a Date object for May 5, 1986
		Calendar c = Calendar.getInstance();
		c.set(1986, 04, 05);		// May 5, 1986
		Date d1 = c.getTime();

		// Create a Date object for today.
		Date d2 = new Date();		// today

		DateFormat df_us = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM, DateFormat.MEDIUM, l1),
			df_sp = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM, DateFormat.MEDIUM, l2);
		System.out.println("Date d1 for US is " + df_us.format(d1));
		System.out.println("Date d1 for Spain is " + df_sp.format(d1));
		System.out.println("Date d2 is " + df_us.format(d2));
	}
}
