package structure;

import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

/**
 * Show how to select a random value from a properties file.
 * This is more efficient than using Collections.toArray()
 * because this way only access the first 'n' references
 * while the toArray() copies all the references at least once.
 */
public class RandomProps {
	public static final String EMPTY_PROPERTIES_MESSAGE = "(empty properties)";
	static Random r = new Random();

	public static void main(String[] args) {
		Properties p = System.getProperties();
		getRandomString(p);
	}

	/**
	 * Select a randomly-chosen Value from the Properties
	 * @param p
	 */
	public static String getRandomString(Properties p) {
		final int max = p.size();
		if (max == 0)
			return EMPTY_PROPERTIES_MESSAGE;
		int n = r.nextInt(max);
		final Iterator it = p.values().iterator();
		String o = null;
		int i = 0;
		do {
			o = (String) it.next();
		} while (++i < n);
		return o;
	}
}
