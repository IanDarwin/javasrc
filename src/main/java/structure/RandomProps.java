package structure;

import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

/**
 * Show how to select a random value from a properties file.
 */
public class RandomProps {
	public static final String EMPTY_PROPERTIES_MESSAGE = "(empty properties)";
	static Random r = new Random();

	public static void main(String[] args) {
		Properties p = System.getProperties();
		getRandomString(p);
	}

	/**
	 * Select a randomly-chosen Value from the Properties.
	 * This is more efficient - for the expected typical use of getting
	 * one randome string from a Properties - than using Collections.toArray()
	 * (or constructing a List from the Properties' KeySet)
	 * because this way only access the first 'n' references once,
	 * while the toArray() copies all the references at least once.
	 * However, if you needed to retrieve many randomly chosen properties
	 * from the same Properties object, then obviously
	 * changing this class to pass a Properties into its constructor and
	 * converting to a List or Array there would be more efficient
	 * @param p
	 */
	public static String getRandomString(Properties p) {
		final int max = p.size();
		if (max == 0)
			return EMPTY_PROPERTIES_MESSAGE;
		int n = r.nextInt(max);
		final Iterator<?> it = p.values().iterator();
		String o = null;
		int i = 0;
		do {
			o = (String) it.next();
		} while (++i < n);
		return o;
	}
}
