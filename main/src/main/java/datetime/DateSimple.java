package datetime;

import java.time.LocalDate;

/**
 * Tutorial/Example of LocalDate with specific date (year-month-day)
 */
public class DateSimple {

	public static void main(String[] args) {
		
		System.out.println(LocalDate.now());
		
		System.out.println(LocalDate.of(1991,04,24));		
	}
}
