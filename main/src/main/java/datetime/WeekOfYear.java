package datetime;

import java.time.*;
import java.time.temporal.*;

/**
 * Compute the week-of-year for any date
 * @param args The year (4 digits), month and day.
 */
public class WeekOfYear {
	void main(String[] args) {
		int year = Integer.parseInt(args[0]),
		month = Integer.parseInt(args[1]),
		day = Integer.parseInt(args[2]);
		LocalDate date = LocalDate.of(year, month, day);
		int week = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
		System.out.println(STR."\{year}-\{month}-\{day} was in week \{week} of \{year}.");
	}
}
