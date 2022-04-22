package i18n;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;

/** Format some values using the default locale */
public class UseDefaultLocale {
	public static void main(String[] args) {

		DateFormat df = DateFormat.getInstance();
		NumberFormat nf = NumberFormat.getInstance();

		System.out.println(df.format(new Date()));
		System.out.println(nf.format(123.4567));
	}
}
