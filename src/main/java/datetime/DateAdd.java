package datetime;

// BEGIN main
import java.time.LocalDate;
import java.time.Period;

/** DateAdd -- compute the difference between two dates
 * (e.g., today and 700 days from now).
 */
public class DateAdd {
	public static void main(String[] av) {
		/** Today's date */
		LocalDate now =  LocalDate.now();

		Period p = Period.ofDays(700);
		LocalDate then = now.plus(p);

		System.out.printf("Seven hundred days from %s is %s%n", now, then);
	}
}
// END main
