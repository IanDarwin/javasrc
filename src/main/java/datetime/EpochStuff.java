package datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class EpochStuff {

	public static void main(String[] args) {
		ZoneId zone = ZoneId.systemDefault();
		Instant bot = Instant.ofEpochSecond(0L); // begin of time
		System.out.println(bot);
		Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
		System.out.println(LocalDateTime.ofInstant(now, zone));
		int epochDay = ((int)((2013 - 1970) * 365.25));
		System.out.println(LocalDate.ofEpochDay(epochDay));
	}

}
