import java.util.*;

/**
 * Demonstrate the HashMap class, and an Iterator.
 * @see HashTableDemo, for the older Hashtable.
 */
public class HashMapDemo {

	public static void main(String[] argv) {

		// Construct and load the hash. This simulates loading a
		// database or reading from a file, or wherever the data is.

		HashMap h = new HashMap();

		// The hash maps from company name to address.
		// In real life this might map to an Address object...
		h.put("Adobe", "Mountain View, CA");
		h.put("IBM", "White Plains, NY");
		h.put("Learning Tree", "Los Angeles, CA");
		h.put("Microsoft", "Redmond, WA");
		h.put("Netscape", "Mountain View, CA");
		h.put("O'Reilly", "Sebastopol, CA");
		h.put("Sun", "Mountain View, CA");

		// Two versions of the "retrieval" phase.
		// Version 1: get one pair's value given its key
		// (presumably the key would really come from user input):
		String queryString = "O'Reilly";
		System.out.println("You asked about " + queryString + ".");
		String resultString = (String)h.get(queryString);
		System.out.println("They are located in: " + resultString);
		System.out.println();

		// Version 2: get ALL the keys and pairs 
		// (maybe to print a report, or to save to disk)
		Iterator k = h.keySet().iterator();
		while (k.hasNext()) {
			String key = (String) k.next();
			System.out.println("Key " + key + "; Value " +
				(String) h.get(key));
		}
	}
}
