package structure;

import java.util.Comparator;

// BEGIN main
/** Comparator for comparing strings ignoring first character.
 */
public class SubstringComparator implements Comparator<String> {
	@Override
	public int compare(String s1, String s2) {
		s1 = s1.substring(1);
		s2 = s2.substring(1);
		return s1.compareTo(s2);
		// or, more concisely:
		// return s1.substring(1).compareTo(s2.substring(1));
	}
}
// END main
