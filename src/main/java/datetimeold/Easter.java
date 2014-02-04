package datetimeold;

import java.util.Calendar;
import java.util.GregorianCalendar;

/** Easter - compute the day on which Easter falls.
 *
 * In the Christian religion, Easter is possibly the most important holiday
 * of the year, so getting its date <I>just so</I> is worthwhile.
 *
 * @author: Ian F. Darwin, http://www.darwinsys.com/,
 * based on a detailed algorithm in Knuth, vol 1, pg 155.
 *
 * @Version: $Id$
 * Written in C, Toronto, 1988. Java version 1996.
 *
 * @Note: It's not proven correct, although it gets the right answer for 
 * years around the present.
 */
public class Easter {

	/* Compute the day of the year that Easter falls on.
	 * Step names E1 E2 etc., are direct references to Knuth, Vol 1, p 155.
	 * @exception IllegalArgumentexception If the year is before 1582 (since the
	 * 		algorithm only works on the Gregorian calendar).
	 */
	public static final Calendar findHolyDay(int year) {
		if (year <= 1582) {
			throw new IllegalArgumentException("Algorithm invalid before April 1583");
		}
		int golden, century, x, z, d, epact, n;

		golden = (year % 19) + 1;	/* E1: metonic cycle */
		century = (year / 100) + 1;	/* E2: e.g. 1984 was in 20th C */
		x = (3*century/4) - 12;		/* E3: leap year correction */
		z = ((8*century+5) / 25) -5; 	/* E3: sync with moon's orbit */
		d = (5*year/4) - x - 10;
		epact = (11*golden + 20 + z - x) % 30; /* E5: epact */
		if ((epact == 25 && golden > 11) || epact == 24)
			epact++;
		n = 44 - epact;
		n += 30 * (n < 21?1:0);		/* E6: */
		n += 7 - ((d+n)%7);
		if (n>31)			/* E7: */
			return new GregorianCalendar(year, 4-1, n-31);	/* April */
		else
			return  new GregorianCalendar(year, 3-1, n);	/* March */
	}

	/** Main program, when used as a standalone application */
	public static void main(String[] argv) {

		if (argv.length == 0) {
			int thisYear = new GregorianCalendar().get(Calendar.YEAR);
			Calendar c = Easter.findHolyDay(thisYear);
			System.out.println( c.getTime());
		} else {
			for (String arg : argv) {
				int year = 0;
				try {
					year = Integer.parseInt(arg);
					System.out.println(Easter.findHolyDay(year).getTime());
				} catch (IllegalArgumentException e) {
					System.err.println("Year " + arg + " invalid (" + e.getMessage() + ").");
				}
			}
		}
	}
}
