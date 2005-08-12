package wildmat;

import java.io.*;

/*
 *  Simple shell-style pattern matching for ?, \, [], and * characters.
 *  Might not be robust in face of malformed patterns; e.g., "foo[a-"
 *  could cause an exception to be thrown.
 * @author C version by Rich $alz, mirror!rs, Wed Nov 26 19:03:17 EST 1986.
 * @author Java by Ian Darwin
 */
public class WildMat {

	/*
	 * wildmat -- perform shell-style wildcard matching.
	 * @param s - string to look in
	 * @param p - pattern to look for.
	 */
	public static boolean wildmat(String str, String patt)
	{
		boolean 	 matched;
		boolean 	 reverse;
		int last, p = 0, s = 0;

		for ( ; p<patt.length(); s++, p++)
		switch (patt.charAt(p)) {
			case '\\':
				/* Literal match with following character; fall through. */
				p++;
			default:
				if (str.charAt(s) != patt.charAt(p))
					return(false);
				continue;
			case '?':
				/* Match anything. */
				if (str.charAt(s) == '\0')
					return(false);
				continue;
			case '*':
				/* Trailing star matches everything. */
				return(++p<patt.length() ? 
					Star(str.substring(s), patt.substring(p)) :
					true);
			case '[':
			/* [^....] means inverse character class. */
				reverse = patt.charAt(p+1) == '^';
				if (reverse)
					p++;
				for (last = 0400, matched = false; 
					++p<patt.length() && patt.charAt(p) != ']';
					last = patt.charAt(p))

					/* This next line required a good C compiler. */
					if (patt.charAt(p) == '-' ? 
						((str.charAt(s) <= patt.charAt(++p)) && 
							(str.charAt(s) >= last)) :
						(str.charAt(s) == patt.charAt(p))) {
						matched = true;
				}
				if (matched == reverse)
					return false;
				continue;
			// End of cases.
		}

		return(str.charAt(s) == '\0');
	}

	/** tail match for "*"
	 */
	private static boolean Star(String s, String p)
	{
		while (wildmat(s, p) == false)
	//if (++s == s.length())
			return(false);
		return(true);
	}

	/** Simple test program. Pattern on command line (quoted!),
	 * or run regression test with no arguments.
	 */
	public static void main(String args[]) throws IOException
	{
		if (args.length == 0) {
			regress();
		}

		String	 pattern = args[0];

		BufferedReader stdin = new BufferedReader(
			new InputStreamReader(System.in));

		String line;
		while (true) {
			System.out.print("Enter text:  ");
			if ((line = stdin.readLine()) == null)
				System.exit(0);
			if (line.length() == 0)
				break;
			System.out.println(wildmat(line, pattern)?"match":"no match");
		}
	}

	public static void regress() {
		doTest("a b c", "b", true);
		doTest("a b c", "b*c", true);
	}

	protected static void doTest(String str, String patt, boolean exp) {
		System.out.println("doTest("+str+","+patt +") [expect " + exp + "] " +
			(wildmat(str, patt) == exp ? "OK" : "FAIL"));
	}
}
