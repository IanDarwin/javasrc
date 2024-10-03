package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Reverse the order of two fields.
 * Input:
 * Adams, John Quincy
 * Output:
 * John Quincy Adams
 */
// tag::main[]
public class REmatchTwoFields {
	public static void main(String[] args) {
		String inputLine = "Adams, John Quincy";
		// Construct an RE with parens to "grab" lastname and firstname(s)
		Pattern p = Pattern.compile("(.*), (.*)");
		Matcher m = p.matcher(inputLine);
		if (!m.matches()) {
			throw new IllegalArgumentException("Bad input");
		}
		System.out.println("Numbered: " + m.group(2) + ' ' + m.group(1));

		// Same thing but with names:
		Pattern p2 = Pattern.compile("(?<last>.*), (?<first>.*)");
		m = p2.matcher(inputLine);
		if (!m.matches()) {
			throw new IllegalArgumentException("Bad input");
		}
		System.out.println("Named 1:  " + m.group("first") + " " + m.group("last"));
		System.out.println("Named 2:  " + m.replaceAll("${first} ${last}"));
	}
}
// end::main[]
