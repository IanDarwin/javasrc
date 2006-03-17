package structure;

import java.util.ArrayList;
import java.util.Date;

/**
 * ArrayList Demo.
 */
public class ArrayListDemo {
	public static void main(String[] argv) {
		ArrayList<Date> al = new ArrayList<Date>();

		// Create a source of Objects
		StructureDemo source = new StructureDemo(15);

		// Add lots of elements to the ArrayList...
		al.add(source.getDate());
		al.add(source.getDate());
		al.add(source.getDate());

		// Print them out using a for loop.
		System.out.println("Retrieving by index:");
		for (int i = 0; i<al.size(); i++) {
			System.out.println("Element " + i + " = " + al.get(i));
		}
	}
}
