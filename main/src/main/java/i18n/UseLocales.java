package i18n;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/** Use some locales; based on user's OS "settings"
 * choices or -Duser.lang= or -Duser.region=.
 */
// tag::main[]
public class UseLocales {
	public static void main(String[] args) {

		Locale frLocale = Locale.FRANCE;	// predefined
		Locale ukLocale = Locale.of("en", "UK");	// English, UK version

		DateTimeFormatter defaultDateFormatter = 
			DateTimeFormatter.ofLocalizedDateTime(
				FormatStyle.MEDIUM);
		DateTimeFormatter frDateFormatter = 
			DateTimeFormatter.ofLocalizedDateTime(
				FormatStyle.MEDIUM).localizedBy(frLocale);
		DateTimeFormatter ukDateFormatter = 
			DateTimeFormatter.ofLocalizedDateTime(
				FormatStyle.MEDIUM).localizedBy(ukLocale);

		LocalDateTime now = LocalDateTime.now();
		System.out.println("Default: " + ' ' +
			now.format(defaultDateFormatter));
		System.out.println(frLocale.getDisplayName() + ' ' +
			now.format(frDateFormatter));
		System.out.println(ukLocale.getDisplayName() + ' ' +
			now.format(ukDateFormatter));
	}
}
// end::main[]
