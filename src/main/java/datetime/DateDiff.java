import java.util.*;

/** DateDiff -- compute the difference between two dates.
 */
public class DateDiff {
	public static void main(String[] av) {
		/** The ending date. This value
		 * doubles as a Y2K countdown time.
		 */
		Date d1 = new GregorianCalendar(1999,11,31,23,59).getTime();

		/** Today's date */
		Date d2 = new Date();

		// Get msec from each, and subtract.
		long diff = d2.getTime() - d1.getTime();

		System.out.println("Difference between " + d2 + "\n" +
			"\tand Y2K is " +
			(diff / (1000*60*60*24)) +
			" days.");
	}
}
