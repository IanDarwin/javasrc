package datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

/**
 * Convert a localtime from one timezone to another.
 * @author Ian Darwin
 */
// tag::main[]
public class TimeTranslate {

	static String from = "Toronto", to = "Kolkata";
	static ZoneId fromZone = ZoneId.of("America/" + from),
				toZone = ZoneId.of("Asia/" + to);
	static final String DATE_FORMAT = "yyyy-MM-dd hh:mm a";

	public static void main(String[] args) {
		LocalDateTime when = null;
		// XXX Check for -r and reverse args
		if (args.length == 0) {
			when = LocalDateTime.now();                                        
		} else {
			String time = args[0];
			LocalTime localTime = LocalTime.parse(time);
			when = LocalDateTime.of(LocalDate.now(), localTime);               
		}
		ZonedDateTime fromZoned = when.atZone(fromZone);
		ZonedDateTime toZoned = fromZoned.withZoneSameInstant(toZone);
		DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT);
		System.out.printf("Time %s in %s is %s in %s\n", when.format(df), from, toZoned.format(df), to);
	}
}
// end::main[]
