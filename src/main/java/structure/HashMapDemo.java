import java.util.*;

/**
 * Demonstrate the Hashtable class
 */
public class HashDemo {
	public static void main(String argv[]) {

		// Construct and load it.
		Hashtable h = new Hashtable();
		h.put("Adobe", "Mountain View, CA");
		h.put("IBM", "White Plains, NJ");
		h.put("Learning Tree", "Los Angeles, CA");
		h.put("Microsoft", "Redmond, WA");
		h.put("Netscape", "Mountain View, CA");
		h.put("Sun", "Mountain View, CA");

		// Two versions of the "retrieval" phase.
		// Version 1: get one pair's value given its key
		// (presumably the key would really come from user input):
		String s = (String)h.get("Learning Tree");
		System.out.println("Location: " + s);

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
