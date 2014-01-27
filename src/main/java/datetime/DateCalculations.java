package datetime;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAdjuster;

public class DateCalculations {

	public static void main(String[] args) {
		
		// Find payday (last Friday of this month)
		OffsetDateTime now = OffsetDateTime.now();
		OffsetDateTime payDay =
		        now.with(TemporalAdjuster.lastInMonth(DayOfWeek.FRIDAY));
		System.out.println("This month's payday is Friday " + 
		        payDay.getMonth() + " " +
				payDay.getDayOfMonth());	
	}
}
