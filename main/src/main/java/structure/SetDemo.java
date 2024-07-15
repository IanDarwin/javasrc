package structure;

import java.util.HashSet;
import java.util.Set;

/**
 * Demonstrate the Set interface
 * @author Ian F. Darwin, https://darwinsys.com/
 */
public class SetDemo {
	public static void main(String[] argv) {
		// tag::main[]
		Set<String> hashSet = new HashSet<>();
		hashSet.add("One");
		hashSet.add("Two");
		hashSet.add("One"); // DUPLICATE
		hashSet.add("Three");
		System.out.println(hashSet);
		// end::main[]
		hashSet.stream().map(s->s.hashCode()).forEach(System.out::println);
	}
}
