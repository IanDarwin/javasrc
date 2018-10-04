package structure;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/** Iterator used to walk through a Vector.
 */
public class VectorIterator {
	public static void main(String[] argv) {
		List<Date> v = new Vector<>();
		StructureDemo source = new StructureDemo(15);

		// Add lots of elements to the Vector...
		v.add(source.getDate());
		v.add(source.getDate());
		v.add(source.getDate());

		// Process the data structure using an iterator.
		int i = 0;
		Iterator<Date> it = v.iterator();

		// Remaining part of the code does not know or care
		// if the data is an an array, a List, or whatever.
		while (it.hasNext()) {
			Object o = it.next();
			System.out.println("Element " + i++ + " = " + o);
		}
	}
}
