package structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Demonstrate the HashMap class, and an Iterator.
 * @see HashTableDemo, for the older Hashtable.
 */
public class MapEntrySetDemo {

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

		// List the entries using entrySet()
        Iterator<?> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<?,?> entry = (Entry<?, ?>) it.next();
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
	}
}
