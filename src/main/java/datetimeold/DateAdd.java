package datetimeold;

import java.util.*;

/** DateAdd -- compute the difference between two dates
 * (today and 700 days ago).
 */
public class DateAdd {
	public static void main(String[] av) {
		/** Today's date */
		Date now = new Date();

		long t = now.getTime();

		t -= 700L*24*60*60*1000;

		Date then = new Date(t);

		System.out.println("Seven hundred days ago was " + then);
	}
}
