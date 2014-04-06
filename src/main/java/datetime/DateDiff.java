package datetime;

// BEGIN main
import java.time.LocalDate;
import java.time.Period;

public class DateDiff {

	public static void main(String[] args) {
		/** The date at the end of the last century */
		LocalDate endofCentury = LocalDate.of(2000, 12, 31);
		LocalDate now = LocalDate.now();
		
		Period diff = Period.between(endofCentury, now);
		
		System.out.printf("The 21st century (up to %s) is %s old%n", now, diff);
		System.out.printf(
				"The 21st century is %d years, %d months and %d days old",
				diff.getYears(), diff.getMonths(), diff.getDays());
	}
}
// END main
