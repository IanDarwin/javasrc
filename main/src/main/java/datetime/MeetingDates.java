package datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class MeetingDates {

/**
 * Compute dates of recurring meeting
 * This version is hard-coded to be 3rd Wednesday of each month.
 */
	private static int weekOfMonth = 3;
	private static DayOfWeek dayOfWeek = DayOfWeek.WEDNESDAY;

	// tag::method[]
	public static LocalDate getNextMeeting(
			int weekOfMonth,
			DayOfWeek dayOfWeek,
			int meetingsAway) {
		LocalDate now = LocalDate.now();
		LocalDate thisMeeting = now.with(
			TemporalAdjusters.dayOfWeekInMonth(weekOfMonth,dayOfWeek));
		// Has the meeting already happened this month?
		if (thisMeeting.isBefore(now)) {
			// start from next month
			meetingsAway++;
		}
		if (meetingsAway > 0) {
			thisMeeting = thisMeeting.plusMonths(meetingsAway).
				with(TemporalAdjusters.dayOfWeekInMonth(weekOfMonth,dayOfWeek));
		}
		return thisMeeting;
	}
	// end::method[]

	// tag::main[]
	public static void main(String[] args) {
		DateTimeFormatter dfm = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
		for (int monthAway = 0; monthAway <= 2; monthAway++) {
			LocalDate dt = getNextMeeting(weekOfMonth, dayOfWeek, monthAway);
			System.out.println(dt.format(dfm));
		}
	}
	// end::main[]
}

