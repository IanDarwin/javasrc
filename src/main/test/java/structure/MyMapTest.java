package structure;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Test the MyMap class.
 */
public class MyMapTest {

	public static void main(String[] argv) {

		// Construct and load the hash. This simulates loading a
		// database or reading from a file, or wherever the data is.

		Map map = new MyMap();

		// The hash maps from company name to address.
		// In real life this might map to an Address object...
		map.put("Adobe", "Mountain View, CA");
		map.put("Learning Tree", "Los Angeles, CA");
		map.put("IBM", "White Plains, NY");
		map.put("Netscape", "Mountain View, CA");
		map.put("Microsoft", "Redmond, WA");
		map.put("Sun", "Mountain View, CA");
		map.put("O'Reilly", "Sebastopol, CA");

		// Two versions of the "retrieval" phase.
		// Version 1: get one pair's value given its key
		// (presumably the key would really come from user input):
		String queryString = "O'Reilly";
		System.out.println("You asked about " + queryString + ".");
		String resultString = (String)map.get(queryString);
		System.out.println("They are located in: " + resultString);
		System.out.println();

		// Version 2: get ALL the keys and pairs 
		// (maybe to print a report, or to save to disk)
		Iterator k = map.keySet().iterator();
		while (k.hasNext()) {
			String key = (String) k.next();
			System.out.println("Key " + key + "; Value " +
				(String) map.get(key));
		}

		// Step 3 - try out the entrySet() method.
		Set es = map.entrySet();
		System.out.println("entrySet() returns " + es.size() + " Map.Entry's");
	}
}
