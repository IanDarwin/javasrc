package structure;

import java.util.Arrays;

/** Demonstrate use of SubstringComparator.
 */
// BEGIN main
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
	}
	
	static void dump(String[] args, String title) {
		System.out.println(title);
		for (String s : args)
			System.out.println(s);
	}
}
// END main
