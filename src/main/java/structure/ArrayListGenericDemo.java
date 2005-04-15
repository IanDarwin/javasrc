package structure50;

import java.util.*;

public class ArrayListGenericDemo {
	public static void main(String[] args) {
		ArrayList<String> data = new ArrayList<String>();
		data.add("hello");
		data.add("goodbye");

		// data.add(new Date()); This won't compile!

		Iterator<String> it = data.iterator();
		while (it.hasNext()) {
			String s = it.next();
			System.out.println(s);
		}
	}
}
