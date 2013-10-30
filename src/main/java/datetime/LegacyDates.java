package datetime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class LegacyDates {
	public static void main(String[] args) {

		Date legacyDate = new Date();
		System.out.println(legacyDate);

		ZoneOffset zoneOffset1 = ZoneOffset.of("-0400");

		// using the long integer-based methods
		long longTime = legacyDate.getTime();
		LocalDateTime convertedDate1 = LocalDateTime.ofEpochSecond(
				longTime / 1000, (int) ((longTime % 1000) * 1000), zoneOffset1);
		System.out.println(convertedDate1);

		// Using individual values
		LocalDateTime convertedDate2 = LocalDateTime.of(
				legacyDate.getYear() + 1900,
				legacyDate.getMonth() + 1, legacyDate.getDate(),
				legacyDate.getHours(), legacyDate.getMinutes(),
				legacyDate.getSeconds());
		System.out.println(convertedDate2);
	}
}
