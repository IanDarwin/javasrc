package datetime;

import java.util.List;
import java.util.Locale;
import java.time.*;
import java.time.format.*;

// tag::main[]
public class DateTimeFormatterLocalized {

	public static void main(String[] args) { 

		var dt = ZonedDateTime.now();

		for (Locale l : List.of(Locale.CANADA, Locale.FRANCE, Locale.UK, Locale.TAIWAN)) {
			Locale.setDefault(l);
			DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
			System.out.printf("In Locale %s, date is %s\n",Locale.getDefault(), f.format(dt));
		}
	}
}
// end::main[]
