package i18n;

import java.util.Locale;

/** Print the default locale */
public class DefaultLocale {

	public static void main(String[] av) { 
		Locale l = Locale.getDefault();
		System.out.println("Today's Locale is " + l);
	}
}
