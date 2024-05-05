package datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * "Your kids are traveling as Unaccompanied Minors on a trans-Atlantic flight from YYZ
 * that takes 5 hours 10 minutes from the actual time of departure. Your in-laws
 * need one hour to get to LHR and find parking. What time should
 * you phone them to leave for the airport?"
 * @author Ian Darwin, based on a related calculation by Chris Mawata.
 */
// tag::main[]
public class FlightArrivalTimeCalc {

	static Duration driveTime = Duration.ofHours(1);

	public static void main(String[] args) {
		LocalDateTime when = null;
		if (args.length == 0) {
			when = LocalDateTime.now();                                        // <1>
		} else {
			String time = args[0];
			LocalTime localTime = LocalTime.parse(time);
			when = LocalDateTime.of(LocalDate.now(), localTime);               // <1>
		}
		calculateArrivalTime(when);
	}

	public static ZonedDateTime calculateArrivalTime(LocalDateTime takeOffTime) {
		ZoneId torontoZone = ZoneId.of("America/Toronto"),
				londonZone = ZoneId.of("Europe/London");
		ZonedDateTime takeOffTimeZoned =
			ZonedDateTime.of(takeOffTime, torontoZone);                        // <2>
		Duration flightTime =
			Duration.ofHours(5).plus(10, ChronoUnit.MINUTES);                  // <3>
		ZonedDateTime arrivalTimeUnZoned = takeOffTimeZoned.plus(flightTime);  // <4>
		ZonedDateTime arrivalTimeZoned =
			arrivalTimeUnZoned.toInstant().atZone(londonZone);                 // <5>
		ZonedDateTime phoneTimeHere = arrivalTimeUnZoned.minus(driveTime);     // <6>

		System.out.println("Flight departure time " + takeOffTimeZoned);
		System.out.println("Flight expected length: " + flightTime);
		System.out.println(
			"Flight arrives there at " + arrivalTimeZoned + " London time.");
		System.out.println("You should phone at " + phoneTimeHere + " Toronto time");
		return arrivalTimeZoned;
	}
}
// end::main[]
