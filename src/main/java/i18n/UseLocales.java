import java.text.*;
import java.util.*;

/** Use some locales
 * choices or -Duser.lang= or -Duser.region=.
 */
public class UseLocales {
	public static void main(String[] args) {

		Locale frLocale = Locale.FRANCE;	// predefined
		Locale ukLocale = new Locale("en", "UK");	// English, UK version

		DateFormat defaultDateFormatter = DateFormat.getDateInstance(
			DateFormat.MEDIUM);
		DateFormat spainDateFormatter = DateFormat.getDateInstance(
			DateFormat.MEDIUM, frLocale);
		DateFormat ukDateFormatter = DateFormat.getDateInstance(
			DateFormat.MEDIUM, ukLocale);

		Date now = new Date();
		System.out.println(defaultDateFormatter.format(now));
		System.out.println(spainDateFormatter.format(now));
		System.out.println(ukDateFormatter.format(now));
	}
}
