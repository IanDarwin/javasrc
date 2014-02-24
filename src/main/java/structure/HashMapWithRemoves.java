package structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Demonstrate the HashMap class, and an Iterator, with concurrent remove
 */
public class HashMapWithRemoves {

	public static void main(String[] argv) {

		// Construct and load the hash. This simulates loading a
		// database or reading from a file, or wherever the data is.

		Map<String,String> map = new HashMap<String,String>();

		// The hash maps from company name to address.
		// In real life this might map to an Address object...
		map.put("Adobe", "Mountain View, CA");
		map.put("IBM", "White Plains, NY");
		map.put("Learning Tree", "Los Angeles, CA");
		map.put("Microsoft", "Redmond, WA");
		map.put("Netscape", "Mountain View, CA");
		map.put("O'Reilly", "Sebastopol, CA");
		map.put("Sun", "Mountain View, CA");

		// BEGIN SafeRemoval
		// Version 2: get ALL the keys and values 
		// with concurrent modification
		Iterator<String> it = map.keySet( ).iterator( );
		while (it.hasNext( )) {
			String key = it.next( );
			if (key.equals("Sun")) {
				it.remove();
				continue;
			}
			System.out.println("Company " + key + "; " +
				"Address " + map.get(key));
		}
		// END SafeRemoval
	}
}
