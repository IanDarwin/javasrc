package datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateConversions {
	public static void main(String[] args) {
		
		// Convert a number of Seconds since the Epoch, to a local date/time
		Instant epochSec = Instant.ofEpochSecond(1000000000L);
		ZoneId zId = ZoneId.of("Canada/Eastern");
		LocalDateTime then = LocalDateTime.ofInstant(epochSec, zId);
		System.out.println("The epoch was a billion seconds old on " + then);
		
		LocalDateTime now = LocalDateTime.now();
		ZonedDateTime there = now.atZone(ZoneId.of("Canada/Pacific"));
		System.out.printf("When it's %s here, it's %s in Vancouver%n", now, there);
	}
}