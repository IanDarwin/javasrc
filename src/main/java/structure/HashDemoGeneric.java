package structure;

import java.util.HashMap;

public class HashDemoGeneric {
	public static void main(String[] args) {
		HashMap<Integer,String> map = new HashMap<Integer,String>();

		map.put(1, "Ian");
		map.put(42, "Scott");
		map.put(123, "Somebody else");

		String name = map.get(42);
		System.out.println(name);
	}
}
