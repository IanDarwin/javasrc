package datetime;

import java.time.LocalDate;
import java.time.Period;

/** DateAdd -- compute the difference between two dates
 * (today and 700 days from now).
 */
public class DateAdd {
	public static void main(String[] av) {
		//+
		/** Today's date */
		LocalDate now =  LocalDate.now();

		Period p = Period.ofDays(700);
		LocalDate then = now.plus(p);

		System.out.println("Seven hundred days from now is " + then);
		//-
	}
}
