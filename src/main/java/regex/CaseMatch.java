import java.util.regex.*;

/**
 * Show case control using RE class.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class CaseMatch {
	public static void main(String[] argv) {
		String pattern = "^q[^u]\\d+\\.";
		String input = "QA777. is the next flight. It is on time.";

		Pattern r = Pattern.compile(pattern, Pattern.IGNORE_CASE);

		boolean found;
		Matcher m = r.matcher(input);	// will match any case
		found = r.find();				// will match any case
		System.out.println("IGNORE_CASE match " + found);

		r.setMatchFlags(RE.MATCH_NORMAL);
		found = r.match(input);		// will match case-sensitively
		System.out.println("MATCH_NORMAL match was " + found);

	}
}
