package RE-apache;

import org.apache.regexp.*;

/**
 * Show case control using RE class.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class CaseMatch {
	public static void main(String[] argv) throws RESyntaxException {
		String pattern = "^q[^u]\\d+\\.";
		String input = "QA777. is the next flight. It is on time.";

		RE r = new RE(pattern, RE.MATCH_CASEINDEPENDENT);

		boolean found;
		found = r.match(input);			// will match any case
		System.out.println("MATCH_CASEINDEPENDENT match " + found);

		r.setMatchFlags(RE.MATCH_NORMAL);
		found = r.match(input);		// will match case-sensitively
		System.out.println("MATCH_NORMAL match was " + found);

	}
}
