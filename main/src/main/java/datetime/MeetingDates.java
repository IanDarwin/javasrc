package datetime;

import java.time.*;
import java.time.format.*;
import java.time.temporal.*;

public class MeetingDates {

/**
 * Compute dates of recurring meeting
 * This version has hard-coded to be 3rd Wednesday of each month.
 */
	int weekOfMonth = 3;
	DayOfWeek dayOfWeek = DayOfWeek.WEDNESDAY;

	// tag::method[]
	public LocalDate getNextMeeting(int meetingsAway) {
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
		MeetingDates mp = new MeetingDates();
		DateTimeFormatter dfm = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
		for (int i = 0; i <= 2; i++) {
			LocalDate dt = mp.getNextMeeting(i);
			System.out.println(dt.format(dfm));
		}
	}
	// end::main[]
}

