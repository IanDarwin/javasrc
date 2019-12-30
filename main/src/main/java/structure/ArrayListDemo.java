package structure;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList Demo.
 */
// tag::main[]
public class ArrayListDemo {
	public static void main(String[] argv) {
		List<LocalDate> editions = new ArrayList<>();

		// Add lots of elements to the ArrayList...
		editions.add(LocalDate.of(2001, 06, 01));
		editions.add(LocalDate.of(2004, 06, 01));
		editions.add(LocalDate.of(2014, 06, 20));

		// Use old-style 'for' loop to get index number.
		System.out.println("Retrieving by index:");
		for (int i = 0; i<editions.size(); i++) {
			System.out.printf("Edition %d was %s\n", i + 1, editions.get(i));
		}
		// Use normal 'for' loop for simpler access
		System.out.println("Retrieving by Iterable:");
		for (LocalDate dt : editions) {
			System.out.println("Edition " + dt);
		}
		
	}
}
// end::main[]
