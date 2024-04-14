package datetime;

// tag::main[]
import java.time.LocalDate;

/** DateAdd -- compute the difference between two dates
 * (e.g., today and 700 days from now).
 */
public class DateAdd {
	public static void main(String[] av) {
		/** Today's date */
		LocalDate now =  LocalDate.now();

		// Period p = Period.ofDays(700);
		LocalDate then = now.plusDays(700);

		System.out.printf("Seven hundred days from %s is %s%n", now, then);
	}
}
// end::main[]
