import java.util.regex.*;
/**
 * CanonEqDemo - show use of Pattern.CANON_EQ, by comparing two ways of
 * entering the Spanish word for "equal" and see if they are equal.
 * 
 * @version $Id$
 */
public class CanonEqDemo {
	public static void main(String[] args) {
		String pattStr = "\u00e9gal"; // Žgal
		String[] input = {"e\u00b4gal", // e + Latin-1 "acute"
				"e'gal" // e + single quote
		};
		Pattern pattern = Pattern.compile(pattStr, Pattern.CANON_EQ);
		for (int i = 0; i < input.length; i++) {
			if (pattern.matcher(input[i]).matches()) {
				System.out.println(pattStr + " matches input " + input[i]);
			} else {
				System.out.println(pattStr + " does not match input " + input[i]);
			}
		}
	}
}
