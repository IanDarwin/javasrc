import java.util.Formatter;
import java.util.Date;
import java.util.Calendar;

/** Demonstrate some usage patterns and format-code examples 
 * of the Formatter class (new in J2SE 1.5).
 */
public class FormatterDates {
	public static void main(String[] args) {

		// Format number as dates e.g., 2004-06-28
		System.out.printf("%1$4d-%2$02d-%3$2d%n", 2004, 6, 28);

		// Format fields directly from a Date object: multiple fields from "1$"
		// (hard-coded formatting for Date not advisable; see I18N chapter)
		Date today = Calendar.getInstance().getTime();
		System.out.printf("Today is %1$tB %1$td, %1$tY%n", today);	// e.g., July 4, 2004
	}
}
