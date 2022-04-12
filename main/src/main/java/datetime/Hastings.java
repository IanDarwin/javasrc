package datetime;

import java.time.LocalDate;
import java.time.Period;

public class Hastings {
	public static void main(String[] args) {

		LocalDate now = LocalDate.now();
		// LocalDate nextWeek = now.plusDays(7);    // This day of next week
		LocalDate hastings = LocalDate.of(1066, 10, 14);

		Period ago = Period.between(hastings, now);
		System.out.printf(
			"The Battle of Hastings was fought %d years and %d months ago\n",
			ago.getYears(), ago.getMonths());
	}
}
