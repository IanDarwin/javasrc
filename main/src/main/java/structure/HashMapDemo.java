package structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Demonstrate the HashMap class, and an Iterator.
 * @see HashTableDemo, for the older Hashtable.
 */
// tag::main[]
public class HashMapDemo {

	record Address(String city, String state){}

	public static void main(String[] argv) {

		// Construct and load the hash. This simulates loading a
		// database or reading from a file, or wherever the data is.

		Map<String,Address> map = new HashMap<>();

		// Populate it: the map maps from company name to Address.
		map.put("Adobe", new Address("Mountain View", "CA"));
		map.put("IBM", new Address("White Plains", "NY"));
		map.put("Learning Tree", new Address("Los Angeles", "CA"));
		map.put("Microsoft", new Address("Redmond", "WA"));
		map.put("O'Reilly", new Address("Sebastopol", "CA"));
		map.put("Rejminet Group", new Address("Toronto", "ON"));

		// Several versions of the "retrieval" phase.
		// Version 1: get one pair's value given its key
		// (presumably the key would really come from user input):
		System.out.println("Version 1");
		String queryString = "O'Reilly";
		System.out.println("You asked about " + queryString + ".");
		var result = map.get(queryString);
		System.out.println("They are located in: " + result);
		System.out.println();

		// Version 2: get ALL the keys and values 
		// (maybe to print a report, or to save to disk)
		System.out.println("Version 2");
		for( String key : map.keySet()) {
			System.out.println("Key " + key + 
				"; Value " + map.get(key));
		}
		System.out.println();
		
		// Version 3: Same but using a Map.Entry lambda
		System.out.println("Version 3");
		map.entrySet().forEach(mE -> 
			System.out.println("Key " + mE.getKey()+ 
				"; Value " +mE.getValue()));
		System.out.println();
	}
}
// end::main[]
