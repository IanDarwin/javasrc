package strings;

/** Simple Comparator for the common case of sortings Strings
 * in a case-insensitive way.
 * NOT NEEDED IN JAVA2; use String.CASE_INSENSITIVE_ORDER.
 */
public class StringIgnoreCaseComparator implements java.util.Comparator {
	public int compare(Object o1, Object o2) {
		String s1 = o1.toString().toLowerCase();
		String s2 = o2.toString().toLowerCase();
		return s1.compareTo(s2);
	}
}
