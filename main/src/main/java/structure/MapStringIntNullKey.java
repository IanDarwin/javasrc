package structure;

import java.util.HashMap;
import java.util.Map;

public class MapStringIntNullKey {
	public static void main(String[] args) { 
		Map<String,Integer> map = new HashMap<>();

		String key = null;

		// map.put(key, map.get(key)+1); // Will throw NPE in intValue

		map.put(key,0);				// But this will obviate it

		map.put(key, map.get(key)+1); // $5 ==> 0

		map.put(key, map.get(key)+1); // $6 ==> 1

		key = "Age 1";

		// map.put(key, map.get(key) + 1); // Will also throw NPE

		map.keySet().forEach(System.out::println);
	}
}


