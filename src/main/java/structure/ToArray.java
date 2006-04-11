package structure;

import java.util.ArrayList;
import java.util.List;

/** List to array */
public class ToArray {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Blobbo");
		list.add("Cracked");
		list.add("Dumbo");
		// list.add(new Date());	// Can't mix and match in error - won't compile.

		// Convert a collection to Object[], which can store objects
		// of any type.
		Object[] ol = list.toArray();
		System.out.println("Array of Object has length " + ol.length);

		String[] sl = (String[]) list.toArray(new String[0]);
		System.out.println("Array of String has length " + sl.length);
	}
}
