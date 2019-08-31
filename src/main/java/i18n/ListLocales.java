package i18n;

import java.util.Locale;

public class ListLocales {
	public static void main(String[] args) {
		// tag::main[]
		Locale[] list = Locale.getAvailableLocales();
		for (Locale loc : list) {
			System.out.println(loc);
		}
		// end::main[]
	}
}
