import java.util.*;

/** ArrayList to array */
public class ToArray {
	public static void main(String[] args) {
		ArrayList al = new ArrayList();
		al.add("Blobbo");
		al.add("Cracked");
		al.add("Dumbo");
		// al.add(new Date());	// Don't mix and match!

		// Convert a collection to Object[], which can store objects
		// of any type.
		Object[] ol = al.toArray();
		System.out.println("Array of Object has length " + ol.length);

		// This would throw an ArrayStoreException if the line
		// "al.add(new Date())" above were uncommented.
		String[] sl = (String[]) al.toArray(new String[0]);
		System.out.println("Array of String has length " + ol.length);
	}
}
