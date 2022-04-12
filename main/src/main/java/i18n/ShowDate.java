package i18n;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class ShowDate {
	public static void main(String[] args) {
		DateFormat df = DateFormat.getDateTimeInstance(
			DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.CANADA_FRENCH);
		System.out.println(df.format(new Date()));
	}
}
