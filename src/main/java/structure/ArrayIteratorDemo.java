// BEGIN main
package structure;

import com.darwinsys.util.ArrayIterator;

public class ArrayIteratorDemo {
	
	private final static String[] names = {
		"rose", "petunia", "tulip"
	};
	
	public static void main(String[] args) {
		ArrayIterator<String> arrayIterator = new ArrayIterator<>(names);

		// Java 5, 6 way
		for (String s : arrayIterator) {
			System.out.println(s);
		}
		
		// Java 8 way
		arrayIterator.forEach(s->System.out.println(s));
	}
}
// END main
