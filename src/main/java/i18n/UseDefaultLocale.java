import java.text.*;
import java.util.*;

/** Format some values using the default locale */
public class UseDefaultLocale {
	public static void main(String[] args) {

		// List the locales
		// Locale[] ll = Locale.getAvailableLocales();
		// for (int i=0; i<ll.length; i++)
			// System.out.println(ll[i]);

		DateFormat df = DateFormat.getInstance();
		NumberFormat nf = NumberFormat.getInstance();

		System.out.println(df.format(new Date()));
		System.out.println(nf.format(123.4567));
	}
}
