package i18n;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/** Change the default locale */
// tag::main[]
public class SetLocale {
	public static void main(String[] args) {

		switch (args.length) {
		case 0:
			Locale.setDefault(Locale.FRANCE);
			break;
		case 1:
			throw new IllegalArgumentException();
		case 2:
			Locale.setDefault(Locale.of(args[0], args[1]));
			break;
		default:
			System.out.println("Usage: SetLocale [language [country]]");
			// FALLTHROUGH
		}

		DateFormat df = DateFormat.getInstance();
		NumberFormat nf = NumberFormat.getInstance();

		System.out.println(df.format(new Date()));
		System.out.println(nf.format(123.4567));
	}
}
// end::main[]
