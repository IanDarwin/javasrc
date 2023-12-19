package datetime;

import java.time.*;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Calculate the number of working days left in a term (such as a school term),
 * correctly accounting for weekdays and holidays.
 * @author Adapted from StackOverflow answer by Ravindra Ranwala
 * https://stackoverflow.com/questions/4600034/calculate-number-of-weekdays-between-two-dates-in-java
 * Added holiday etc. processing.
 */
public class WeekDaysLeft {
	public static void main(String[] args) { 
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.of(2023, 6, 30);
		// startDate = endDate.minusDays(1); // Calibration test: should print '1'.
		Set<DayOfWeek> weekendDays = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
		// See https://www.scdsb.on.ca/calendar (Primary student variant)
		Set<LocalDate> holidays = Set.of(
			LocalDate.of(2023,4, 7), // Good Friday
			LocalDate.of(2023,4,10), // Easter Monday
			LocalDate.of(2023,4,28), // PA Day
			LocalDate.of(2023,5,22), // Victoria Day
			LocalDate.of(2023,6, 2), // PA Day
			LocalDate.of(2023,6,30)  // PA Day
			);
		Set<LocalDate> marchBreak = new HashSet<>();
		for (int i = 13; i <= 17; i++) 
			marchBreak.add(LocalDate.of(2023, 03, i));
		final long weekDaysBetween = startDate.datesUntil(endDate)
			.filter(d -> !weekendDays.contains(d.getDayOfWeek()))
			.filter(d -> !holidays.contains(d))
			.filter(d -> !marchBreak.contains(d))
			.count();
		System.out.println(weekDaysBetween);
	}
}
