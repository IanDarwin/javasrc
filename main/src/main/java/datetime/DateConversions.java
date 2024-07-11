package datetime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Tutorial/Example of LocalDateTime ZoneId Conversions
 */
public class DateConversions {
	public static void main(String[] args) {
		
		// tag::main[]
		// Convert "a billion seconds of Unix" to a local date/time
		Instant epochSec = Instant.ofEpochSecond(1000000000L);
		ZoneId zId = ZoneId.systemDefault();
		ZonedDateTime then = ZonedDateTime.ofInstant(epochSec, zId);
		System.out.println("The epoch was a billion seconds old on " + then);
		
		// Convert a date/time to Epoch seconds
		long epochSecond = ZonedDateTime.now().toInstant().getEpochSecond();
		System.out.println("Current epoch seconds = " + epochSecond);
		
		ZonedDateTime here = ZonedDateTime.now();
		ZonedDateTime there = here.withZoneSameInstant(ZoneId.of("Canada/Pacific"));
		System.out.printf("When it's %s here, it's %s in Vancouver%n", here, there);
		// end::main[]
	}
}
