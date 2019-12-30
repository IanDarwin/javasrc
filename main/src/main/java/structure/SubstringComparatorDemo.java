package structure;

import java.util.Arrays;
import java.util.Comparator;

/** Demonstrate use of SubstringComparator.
 */
// tag::main[]
public class SubstringComparatorDemo {
	public static void main(String[] unused) {
		String[] strings = {
			"painful", 
			"mainly",
			"gaining",
			"raindrops"
		};
		Arrays.sort(strings);
		dump(strings, "Using Default Sort");
		Arrays.sort(strings, new SubstringComparator());
		dump(strings, "Using SubstringComparator");

		// tag::functional[]
		System.out.println("Functional approach:");
		Arrays.stream(strings)
			.sorted(Comparator.comparing(s->s.substring(1)))
			.forEach(System.out::println);
		// end::functional[]
	}
	
	static void dump(String[] args, String title) {
		System.out.println(title);
		for (String s : args)
			System.out.println(s);
	}
}
// end::main[]
