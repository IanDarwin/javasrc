package numbers;

import java.text.*;
import java.util.*;

/** JDK1.1 introduced a series of Formatting classes that
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
		for (int i = 0; i < data.length; ++i) {
			System.out.println(data[i] + "\t" + nf.format(data[i]));
		}
	}
}
      
