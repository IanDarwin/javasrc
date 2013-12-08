package regex;

import java.util.regex.*;

/**
 * Show case control using RE class.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class CaseMatch {
	public static void main(String[] argv) {
		String pattern = "^q[^u]\\d+\\.";
		String input = "QA777. is the next flight. It is on time.";

		Pattern reCaseInsens = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Pattern reCaseSens = Pattern.compile(pattern);

		boolean found;
		Matcher m;
		m = reCaseInsens.matcher(input);	// will match any case
		found = m.lookingAt();				// will match any case
		System.out.println("IGNORE_CASE match " + found);

		m = reCaseSens.matcher(input);	// Get matcher w/o case-insens flag
		found = m.lookingAt();		// will match case-sensitively
		System.out.println("MATCH_NORMAL match was " + found);

	}
}
