package structure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Demonstrate the HashMap class, and an Iterator, with concurrent remove
 */
public class HashMapWithRemoves {

	public static void main(String[] argv) {
		// tag::main[]

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

		// tag::SafeRemoval[]
		// Version 2: get ALL the keys and values 
		// with concurrent modification
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.equals("Sun") || key.equals("Netscape")) {
				it.remove();
				continue;
			}
			System.out.println("Company " + key + "; " +
				"Address " + map.get(key));
		}
		// end::SafeRemoval[]

		// tag::functional[]
		// Alternate to just do the removals, without explicit looping
		map.keySet().removeIf(key -> Set.of("Netscape", "Sun").contains(key));
		// or
		map .entrySet()
			.removeIf(entry -> Set.of("Netscape", "Sun")
				.contains(entry.getKey()));
		map.entrySet().forEach(System.out::println);
		// end::functional[]
		// end::main[]
	}
}
