// tag::main[]
package structure;

import com.darwinsys.util.ArrayIterator;

public class ArrayIteratorDemo {
	
	private final static String[] names = {
		"rose", "petunia", "tulip"
	};
	
	public static void main(String[] args) {
		ArrayIterator<String> arrayIterator = new ArrayIterator<>(names);

		System.out.println("Java 5, 6 way");
		for (String s : arrayIterator) {
			System.out.println(s);
		}
		
		System.out.println("Java 8 ways");
		arrayIterator.forEach(s->System.out.println(s));
		arrayIterator.forEach(System.out::println);
	}
}
// end::main[]
