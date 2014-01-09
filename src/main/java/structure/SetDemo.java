package structure;

import java.util.HashSet;
import java.util.Set;

/**
 * Demonstrate the Set interface
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class SetDemo {
	public static void main(String[] argv) {
		// BEGIN main
		Set<String> hashSet = new HashSet<String>();
		hashSet.add("One");
		hashSet.add("Two");
		hashSet.add("One"); // DUPLICATE
		hashSet.add("Three");
		for (String s : hashSet) {
			System.out.println(s);
		}
		// END main
	}
}
