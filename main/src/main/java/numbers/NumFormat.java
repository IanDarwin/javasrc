package numbers;

import java.text.NumberFormat;
import java.util.Locale;

/** 
 * Java has a series of Formatting classes that
 * format various items in locale-appropriate ways
 * (10000 becomes 10,000 or 10.000 or whatever, as needed
 * in various locales.
 *
 * This program shows some simple ways of using it; for more,
 * see JavaDoc on java.util.Locale and java.text.NumberFormat.
 */
public class NumFormat {
	public static void main(String[] av) {
		int data[] = { 100, 1000, 10000, 1000000 };
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		for (int n : data) {
			System.out.println(n + "\t" + nf.format(n));
		}
	}
}
      
