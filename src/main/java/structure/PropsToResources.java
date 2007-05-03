package structure;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Convert Java Properties file to some other format
 * @author Ian Darwin, http://www.darwinsys.com/
 */
public class PropsToResources {

	public static void main(String[] argv) throws Exception {

		Properties props = new Properties();

		// Get some data
		props.load(new FileInputStream("structure/PropsDemo.dat"));

		// Write properties
		System.out.println("Starting to write");

		// Get one copy of each key in case of duplicates
		final Set<Object> keySet = props.keySet();
		for (Object o : keySet) {
			String key = (String)o;
			String val = props.getProperty(key);
			dumpit(key, val);
		}
		System.out.println("All done. Isn't that nice?");
	}

	/**
	 * You can hack this method to output the keys/values in any format
	 */
	private static void dumpit(String key, String val) {
		System.out.printf("Key: %s, Value: %s%n", key, val);
	}
}
