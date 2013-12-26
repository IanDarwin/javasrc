package structure;

import java.util.Properties;

/**
 * Demonstrate Properties reading/writing.
 * @see HashTableDemo, for the older Hashtable.
 */
// BEGIN main
public class PropsCompanies {

	public static void main(String[] argv) throws java.io.IOException {

		Properties props = new Properties();

		// Get my data
		props.put("Adobe", "Mountain View, CA");
		props.put("IBM", "White Plains, NY");
		props.put("Learning Tree", "Los Angeles, CA");
		props.put("Microsoft", "Redmond, WA");
		props.put("Netscape", "Mountain View, CA");
		props.put("O'Reilly", "Sebastopol, CA");
		props.put("Sun", "Mountain View, CA");

		// Now load additional properties
		props.load(System.in);

		// List merged properties, using System.out
		props.list(System.out);
	}
}
// END main
