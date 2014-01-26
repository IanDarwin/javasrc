package structure;

import java.util.Date;
import java.util.Vector;

/**
 * Simple demo of the Vector class.
 */
public class VectorDemo {
	public static void main(String[] argv) {
		Vector<Date> v = new Vector<>();

		// Create a source of Objects
		StructureDemo source = new StructureDemo(15);

		// Add lots of elements to the Vector...
		v.add(source.getDate());
		v.add(source.getDate());
		v.add(source.getDate());

		// First print them out using a for loop.
		System.out.println("Retrieving by index:");
		for (int i = 0; i<v.size(); i++) {
			System.out.println("Element " + i + " = " + v.get(i));
		}
	}
}
