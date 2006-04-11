package structure;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Demonstrate the Hashtable class, and an Enumeration.
 * @see HashMapDemo, for the newer HashMap class.
 */
public class HashtableDemo {

	public static void main(String[] argv) {

		// Construct and load the hash. This simulates loading a
		// database or reading from a file, or wherever the data is.

		Hashtable<String,String> h = new Hashtable<String,String>();

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
		Enumeration k = h.keys();
		while (k.hasMoreElements()) {
			String key = (String) k.nextElement();
			System.out.println("Key " + key + "; Value " +
				(String) h.get(key));
		}
	}
}
