package i18n;

import java.util.Locale;

public class ListLocales {
	public static void main(String[] args) {
		// BEGIN main
		Locale[] list = Locale.getAvailableLocales();
		for (Locale loc : list) {
			System.out.println(loc);
		}
		// END main
	}
}
