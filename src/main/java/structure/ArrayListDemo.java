package structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ArrayList Demo.
 */
// tag::main[]
public class ArrayListDemo {
	public static void main(String[] argv) {
		List<Date> al = new ArrayList<>();

		// Create a source of Objects
		StructureDemo source = new StructureDemo(15);

		// Add lots of elements to the ArrayList...
		al.add(source.getDate());
		al.add(source.getDate());
		al.add(source.getDate());

		// Print them out using old-style for loop to index number.
		System.out.println("Retrieving by index:");
		for (int i = 0; i<al.size(); i++) {
			System.out.println("Element " + i + " = " + al.get(i));
		}
	}
}
// end::main[]
