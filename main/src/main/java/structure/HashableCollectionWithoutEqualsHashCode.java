package structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Investigate what breaks when you put value objects that DON'T override
 * equals and hashCode into a hashable Collection.
 */
public class HashableCollectionWithoutEqualsHashCode {

	private static final String NAME_SANDY = "Sandy";

	/** Define the class used as Key in the map, so we can control
	 * its hashCode and equals methods.
	 */
	class Key {
		String name;
		public Key(String name) {
			super();
			this.name = name;
		}
		public String toString() {
			return name + "(" + hashCode() + ")";
		}
	}

	static final String TYPE_HURRICANE = "Hurricane";
	static final String TYPE_TROP_STORM = "Tropical Storm";

	public static void main(String[] argv) {
		new HashableCollectionWithoutEqualsHashCode().process();
	}

	void process() {

		Map<Key,String> map = new HashMap<Key,String>();

		// The same key put in twice should only appear once
		Key sandy = new Key(NAME_SANDY);
		String putOk = map.put(sandy, TYPE_HURRICANE);
		// map.put returns the previous value
		assertTrue(null == putOk, "On first add, the previous value should have been null");
		putOk = map.put(sandy, TYPE_HURRICANE);
		assertTrue(putOk == TYPE_HURRICANE, "Although only in map once, it should now be the previous value");
		assertTrue(map.size() == 1, "There should obviously be only one entry in the map here.");

		// Two value objects with the default equals and hashCode
		Key mandy = new Key(NAME_SANDY);
		// "sandy" and "mandy" are now distinct instances but with the same state; with a
		// proper equals method, sandy.equals(mandy) would be true.
		assertTrue(!sandy.equals(mandy), "but with default equals method, they differ");

		map.put(mandy, TYPE_HURRICANE);
		assertTrue(map.size() == 2, "We think there should be two copies in the map now");

		assertTrue(map.containsKey(mandy), "map should contain Key mandy");
		assertTrue(map.containsKey(sandy), "map should contain Key sandy");
		
		// What are we left with?
		for (Key key : map.keySet()) {
			System.out.println(key + " -> " + map.get(key));
		}
		
		// Even though the values are Strings, which implement equals/hashCode correctly...
		for (String s : map.values()) {
			System.out.println("Value " + s + " is a value");
		}
	}
	
	/** Tiny subset of JUnit, baked in to avoid dependency */
	void assertTrue(boolean cond, String mesg) {
		if (!cond) {
			throw new AssertionError(mesg);
		}
	}
}
