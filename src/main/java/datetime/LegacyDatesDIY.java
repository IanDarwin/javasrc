package datetime;

import java.text.Format;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

// BEGIN main
public class LegacyDatesDIY {
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
		
		// Timezone
		TimeZone timeZone = TimeZone.getTimeZone("EST");
		ZoneId zoneId = timeZone.toZoneId();
		System.out.println("EST - > " + zoneId);
		
		// Convert new DateTimeFormatter to old java.util.Format, but it
		// will only format things that implement TemporalAccessor, e.g., new API
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");
		Format legacyFormat = dateTimeFormatter.toFormat();
		System.out.println("Formatted: " +legacyFormat.format(convertedDate2));
	}
}
