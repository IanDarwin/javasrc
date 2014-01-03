package structure;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

/** Iterator used to walk through a Vector.
 */
public class VectorIterator {
	public static void main(String[] argv) {
		Vector<Date> v = new Vector<>();
		StructureDemo source = new StructureDemo(15);

		// Add lots of elements to the Vector...
		v.addElement(source.getDate());
		v.addElement(source.getDate());
		v.addElement(source.getDate());

		// Process the data structure using an iterator.
		int i = 0;
		Iterator it = v.iterator();

		// Remaining part of the code does not know or care
		// if the data is an an array, a Vector, or whatever.
		while (it.hasNext()) {
			Object o = it.next();
			System.out.println("Element " + i++ + " = " + o);
		}
	}
}
