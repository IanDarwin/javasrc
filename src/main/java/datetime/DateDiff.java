import java.util.Date;

/** DateDiff -- compute the difference between two dates.
 */
public class DateDiff {
	public static void main(String av[]) {
		//+ 
		/** The ending date. This value
		 * doubles as a Y2K countdown time.
		 */
		Date d1 = new Date(99,11,31,23,59);	// Ignore Deprecation
		/** Today's date */
		Date d2 = new Date();

		// Get seconds from each, and subtract.
		long diff = d1.getTime() - d2.getTime();

		System.out.println("Difference between " + d2 + "\n" +
			"\tand " + d1 + " is " +
			(diff / (1000*60*60*24)) +
			" days.");
		//-
	}
}
