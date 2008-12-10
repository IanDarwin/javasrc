package regex;

import java.util.regex.Pattern;

/**
 * Simple regex validation of email addresses like user@host.tld.
 * Refer to http://www.ietf.org/rfc/rfc1034.txt, section 2.3.1, read in the context of
 * Section 2.3, where use of "must" ordains that 2.3.1 SHALL apply to all host/domain names
 * used publicly on the Internet.
 */
public class EmailValidator {

	private final static String PATTERN =
		"[\\w._-]+@([a-zA-Z][\\w-]*\\.)+\\w{2,4}$";
	private final static Pattern patt = Pattern.compile(PATTERN);

	public static boolean valid(String email) {
		return patt.matcher(email).matches();
	}
}
