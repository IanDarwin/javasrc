package datetime;

// tag::main[]
import java.time.LocalDate;
import java.time.Period;

/**
 * Tutorial/Example of LocalDate date difference subtraction
 */
public class DateDiff {

	public static void main(String[] args) {
		/** The date at the end of the last century */
		LocalDate endof20thCentury = LocalDate.of(2000, 12, 31);
		LocalDate now = LocalDate.now();
		if (now.getYear() > 2100) {
			System.out.println("The 21st century is over!");
			return;
		}
		
		Period diff = Period.between(endof20thCentury, now);
		
		System.out.printf("The 21st century (up to %s) is %s old%n", now, diff);
		System.out.printf(
				"The 21st century is %d years, %d months and %d days old",
				diff.getYears(), diff.getMonths(), diff.getDays());
	}
}
// end::main[]
