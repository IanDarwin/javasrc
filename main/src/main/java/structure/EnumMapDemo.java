package structure;

import java.util.EnumMap;
import java.util.Map;

/** Simple demo of an EnumMap.
 * EnumMap is a Map specialized for use with enum keys,
 * and are therefore constructed with the class of the
 * enum they are to be used for; they use a Java array
 * internally for maximum performance.
 */
public class EnumMapDemo {
	enum Suite { 
		Clubs, Diamonds, Hearts, Spades
	}
	public static void main(String[] args) {
		Map<Suite, Object> map = new EnumMap<Suite,Object>(Suite.class);
		map.put(Suite.Clubs, 42);
		map.put(Suite.Diamonds, 56);
		for (Suite i : Suite.values()) {
			if (map.containsKey(i))
				System.out.println(i + " " + map.get(i));
		}
	}
}
