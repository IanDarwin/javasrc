package datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * "Your kids are traveling as Unaccompanied Minors on a trans-Atlantic flight
 * that takes 5 hours 10 minutes from departure. Your in-laws
 * need one hour to get to LHR and find parking. What time should
 * you phone them to leave for the airport?"
 * @author Ian Darwin, based on a related calculation by Chris Mawata.
 */
public class FlightArrivalTimeCalc {

	static Duration flightTime = 
			Duration.ofHours(5).plus(10, ChronoUnit.MINUTES);
	static Duration driveTime = Duration.ofHours(1);

	public static void main(String[] args) {
		LocalDateTime when = null;
		if (args.length == 0) {
			when = LocalDateTime.now();
		} else {
			String time = args[0];
			LocalTime lTime = LocalTime.parse(time);
			when = LocalDateTime.of(LocalDate.now(), lTime);
		}
		calulateArrivalTime(when);
	}

	public static void calulateArrivalTime(LocalDateTime takeOffTime) {
		ZoneId torontoZone = ZoneId.of("America/Toronto"),
				londonZone = ZoneId.of("Europe/London");
		ZonedDateTime takeOffTimeZoned = ZonedDateTime.of(takeOffTime, torontoZone);
		System.out.println("Flight departure time " + takeOffTimeZoned);
		ZonedDateTime arrivalTimeZoned = takeOffTimeZoned.plus(flightTime).toInstant().atZone(londonZone);
		ZonedDateTime phoneTimeHere = takeOffTimeZoned.plus(flightTime).minus(driveTime);
		System.out.println("Flight arrives there at " + arrivalTimeZoned);
		System.out.println("You should phone at " + phoneTimeHere);
	}
}
