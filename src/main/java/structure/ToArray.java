import java.util.*;

/** List to array */
public class ToArray {
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("Blobbo");
		list.add("Cracked");
		list.add("Dumbo");
		// list.add(new Date());	// Don't mix and match!

		// Convert a collection to Object[], which can store objects
		// of any type.
		Object[] ol = list.toArray();
		System.out.println("Array of Object has length " + ol.length);

		// This would throw an ArrayStoreException if the line
		// "list.add(new Date())" above were uncommented.
		String[] sl = (String[]) list.toArray(new String[0]);
		System.out.println("Array of String has length " + sl.length);
	}
}
