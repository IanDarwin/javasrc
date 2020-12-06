package datetime;

import java.time.*;

/**
 * Print a slipping schedule for adjusting to timezone changes, an hour per day,
 * before travel (or antipodal virtual meetings).
 * XXX Needs to read a config file to get all values (including labels)
 */
public class ScheduleSlider {
	private static boolean FORWARD = true;
	public static void main(String[] args) {
		LocalDate start = LocalDate.parse("2020-12-06"); 
		LocalDate end = LocalDate.parse("2020-12-18");
		LocalTime breakfast = LocalTime.of(07,00),
			lunch = LocalTime.of(12, 00),
			dinner = LocalTime.of(17, 00),
			bed = LocalTime.of(21, 30);
		String format = "%-10s  %-10s %s  %s  %s\n";
		final int hoursIncrement = FORWARD ? +1 : -1;
		System.out.printf(format, "Day", "Breakfast", "Lunch", "Dinner", "Bed");
		for (LocalDate day = start; day.compareTo(end) <= 0; day = day.plusDays(1)) {
			breakfast = breakfast.plusHours(hoursIncrement);
			lunch = lunch.plusHours(hoursIncrement);
			dinner = dinner.plusHours(hoursIncrement);
			bed = bed.plusHours(hoursIncrement);
			System.out.printf(format, day, breakfast, lunch, dinner, bed);
		}
	}
}
