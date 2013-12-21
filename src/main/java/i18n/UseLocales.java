package i18n;

import java.text.*;
import java.util.*;

/** Use some locales
 * choices or -Duser.lang= or -Duser.region=.
 */
// BEGIN main
public class UseLocales {
	public static void main(String[] args) {

		Locale frLocale = Locale.FRANCE;	// predefined
		Locale ukLocale = new Locale("en", "UK");	// English, UK version

		DateFormat defaultDateFormatter = DateFormat.getDateInstance(
			DateFormat.MEDIUM);
		DateFormat frDateFormatter = DateFormat.getDateInstance(
			DateFormat.MEDIUM, frLocale);
		DateFormat ukDateFormatter = DateFormat.getDateInstance(
			DateFormat.MEDIUM, ukLocale);

		Date now = new Date();
		System.out.println("Default: " + ' ' +
			defaultDateFormatter.format(now));
		System.out.println(frLocale.getDisplayName() + ' ' +
			frDateFormatter.format(now));
		System.out.println(ukLocale.getDisplayName() + ' ' +
			ukDateFormatter.format(now));
	}
}
// END main
