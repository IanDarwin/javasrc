package datetime;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// BEGIN main
public class DateFormatter {
	public static void main(String[] args) {
		
		// Format a date ISO8601-like but with slashes instead of dashes
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/LL/dd");
		System.out.println(df.format(LocalDate.now()));
		
		// Parse a String to a date using the same formatter
		System.out.println(LocalDate.parse("2014/04/01", df));
		
		// Format a Date and Time without timezone information
		DateTimeFormatter nTZ =
			DateTimeFormatter.ofPattern("d MMMM, yyyy h:mm a");
		System.out.println(ZonedDateTime.now().format(nTZ));
	}
}
// END main
