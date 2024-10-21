package datetime;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Tutorial/Example of LocalDate and DateTimeFormatter formatting
 */
// tag::main[]
public class DateTimeFormatterDemo {
	public static void main(String[] args) {
		
		// Format a date ISO8601-like but with slashes instead of dashes
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/LL/dd");
		System.out.println(df.format(LocalDate.now()));
		
		// Format a ZonedDateTime without timezone information
		DateTimeFormatter nTZ =
			DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a");
		System.out.println(ZonedDateTime.now().format(nTZ));
	}
}
// end::main[]
