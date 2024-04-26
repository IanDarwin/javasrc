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
		// Construct an RE with parens to "grab" field1 and field2
		Pattern r = Pattern.compile("(.*), (.*)");
		Matcher m = r.matcher(inputLine);
		if (!m.matches()) {
			throw new IllegalArgumentException("Bad input");
		}
		System.out.println(m.group(2) + ' ' + m.group(1));
	}
}
// end::main[]
