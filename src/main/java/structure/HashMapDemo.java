package structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrate the HashMap class, and an Iterator.
 * @see HashTableDemo, for the older Hashtable.
 */
// BEGIN main
public class HashMapDemo {

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

		// Two versions of the "retrieval" phase.
		// Version 1: get one pair's value given its key
		// (presumably the key would really come from user input):
		String queryString = "O'Reilly";
		System.out.println("You asked about " + queryString + ".");
		String resultString = map.get(queryString);
		System.out.println("They are located in: " + resultString);
		System.out.println();

		// Version 2: get ALL the keys and values 
		// (maybe to print a report, or to save to disk)
		for( String key : map.keySet()) {
			System.out.println("Key " + key + 
				"; Value " + map.get(key));
		}
		
		// Version 3: Same but using a Map.Entry lambda
		map.entrySet().forEach(mE -> 
			System.out.println("Key + " + mE.getKey()+ 
				"; Value " +mE.getValue()));
	}
}
// END main
