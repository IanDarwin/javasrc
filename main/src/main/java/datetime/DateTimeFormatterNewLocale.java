// package datetime;

import java.time.*;
import java.time.format.*;
import java.util.Locale;

// tag::main[]
void main() {
	var df = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
	var now = LocalDateTime.now();
	System.out.println(df.format(now));
	var newdf = df.withLocale(Locale.UK); // new instance with updated Locale info
	System.out.println(newdf.format(now));
}
// end::main[]
