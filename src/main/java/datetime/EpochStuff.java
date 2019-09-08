package datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Tutorial/Example of LocalDate Instant epoch calculation
 */
public class EpochStuff {

	public static void main(String[] args) {
		ZoneId zone = ZoneId.systemDefault();
		Instant bot = Instant.ofEpochSecond(0L); // begin of time
		System.out.println("Unix Epoch began " + bot);
		Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
		System.out.println("Now it's " + LocalDateTime.ofInstant(now, zone));
		
		int year = LocalDate.now().getYear();
		int epochDay = ((int)((year - 1970) * 365.25));
		System.out.println("Start of this year = " + LocalDate.ofEpochDay(epochDay));
	}

}
