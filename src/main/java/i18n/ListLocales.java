package i18n;

import java.util.Locale;

public class ListLocales {
	public static void main(String[] args) {
		Locale[] ll = Locale.getAvailableLocales();
		for (int i=0; i<ll.length; i++)
			System.out.println(ll[i]);
	}
}
