import java.util.regex.*;

/**
 * CanonEqDemo - show use of Pattern.CANON_EQ, by comparing two ways of
 * entering the Spanish word for "equal" and see if they are equal.
 * @version $Id$
 */
public class CanonEqDemo {
	public static void main(String[] args) {
		String pattStr = "\u00e9gal";		// Žgal
		String input   = "e\u00b4gal";	// e«gal
		Pattern pattern = Pattern.compile(pattStr, Pattern.CANON_EQ);
		if (pattern.matcher(input).matches()) {
			System.out.println(pattStr + " matches input " + input);
		} else {
			System.out.println(pattStr + " does not match input " + input);
		}
		
	}
}
