package structure;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

/**
 * Test the MyMap class.
 */
public class MyMapTest {

	private static boolean verbose = false;

	private static final int NUM_ENTRIES = 7;
	
	private Map<String,String> map;
	
	@Before
	public void setup() {

		// Construct and load the hash. This simulates loading a
		// database or reading from a file, or wherever the data is.

		map = new MyMap<String,String>();
		
		assertEquals("initially empty", 0, map.size());	

		// The hash maps from company name to address.
		// In real life this might map to an Address object...
		// If you change this, keep size NUM_ENTRIES the same!
		map.put("Adobe", "Mountain View, CA");
		map.put("Learning Tree", "Los Angeles, CA");
		map.put("IBM", "White Plains, NY");
		map.put("Netscape", "Mountain View, CA");
		map.put("Microsoft", "Redmond, WA");
		map.put("Sun", "Mountain View, CA");
		map.put("O'Reilly", "Sebastopol, CA");
		
		assertEquals("simple size", NUM_ENTRIES, map.size());	
	}
	
	@Test
	public void testRetrieval() {

		// Two versions of the "retrieval" phase.
		// Version 1: get one pair's value given its key
		// (presumably the key would really come from user input):
		String queryString = "O'Reilly";
		System.out.println("You asked about " + queryString + ".");
		String resultString = (String)map.get(queryString);
		System.out.println("They are located in: " + resultString);
		assertEquals("get test", "Sebastopol, CA", resultString);

	}
	
	@Test
	public void testSequential() {
		// Version 2: get ALL the keys and pairs 
		// (maybe to print a report, or to save to disk)
		Iterator<String> k = map.keySet().iterator();
		while (k.hasNext()) {
			String key = (String) k.next();
			final String value = (String) map.get(key);
			if (verbose)
				System.out.println("Key " + key + "; Value " + value);
		}		
	}
	
	@Test
	public void testPutInserts() {
		map.put("new crud", "new value");
		assertEquals(NUM_ENTRIES + 1, map.size());
		assertEquals("new value", map.get("new crud"));
	}
	
	@Test
	public void testPutReplaces(){
		final String MYTOWN = "MyTown, CA";
		map.put("Sun", MYTOWN);
	    final String newLocation = (String)map.get("Sun");
		assertEquals(MYTOWN, newLocation);
		// Ensure value didn't get in as a Key (such a bug once existed)
		assertNull(map.get(MYTOWN));
		assertEquals("multiple put didn't change size",
			NUM_ENTRIES, map.size());
	}
	
	@Test
	public void testEntrySet() {

		// Step 3 - try out the entrySet() method.
		final Set<Entry<String, String>> es = map.entrySet();
		// System.out.println("entrySet() returns " + es.size() + " Map.Entry's");
		assertEquals("entrySet test", NUM_ENTRIES, es.size());
	}
	
	@Test
	public void testClear() {
		map.clear();
		assertEquals("clear test", 0, map.size());
	}

	@Test
	// map.put("Netscape", "Mountain View, CA");
	// map.put("Microsoft", "Redmond, WA");
	public void testContains() {
		assertTrue(map.containsKey("Netscape"));
		assertTrue(map.containsValue("Mountain View, CA"));
		assertFalse(map.containsKey("Redmond, WA"));
		assertFalse(map.containsValue("Netscape"));
	}
}
