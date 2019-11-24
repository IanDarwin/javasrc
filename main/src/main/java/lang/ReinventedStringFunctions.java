package lang;

/**
 * Most of the stuff in this class is a Really Bad Idea(tm);
 * it's just here to make sure it (mostly) compiles.
 * "Those who do not understand (your favorite tech) are destined
 * to reinvent it, poorly" -- apologies to Santayana.
 */
public class ReinventedStringFunctions {

	// This does not compile (unless the guts are commented out).
	int stringCompare0(String s1, String s2) {
		if (s1 == s2)
			return 0;
		// if (s1 < s2) return -1;
		// if (s1 > s2) return +1;
		return 0;
	}
	
	/** What happens if the strings are different lengths?
	 * Why not just use String.compareTo()?
	 */
	int stringCompare(String s1, String s2) {
		if (s1 == s2)
			return 0;
		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) < s2.charAt(i))
				return -1;
			if (s1.charAt(i) > s2.charAt(i))
				return +1;
		}
		return 0;
	}
}
