package structure;

import java.util.*;

// BEGIN main
public class ArrayListGenericDemo {
	public static void main(String[] args) {
		ArrayList<String> data = new ArrayList<String>();
		data.add("hello");
		data.add("goodbye");

		// data.add(new Date()); This won't compile!

		for (String s : data) {
			System.out.println(s);
		}
	}
}
// END main
