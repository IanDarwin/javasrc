package structure;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/** Show the union and intersection of two sets. */
public class SetArithmetic {
	public static void main(String[] args) {

		// Create two sets.
		Set<String> s1 = new HashSet<String>();
		s1.add("Ian Darwin");
		s1.add("Tom Dooley");
		s1.add("Jesse James");
		print("s1", s1);

		Set<String> s2 = new HashSet<String>();
		s2.add("Ian Darwin");
		s2.add("Duelin' Dalton");
		print("s2", s2);

		Set<String> union = new TreeSet<String>(s1);
		union.addAll(s2);		// now contains the union

		print("union", union);

		Set<String> intersect = new TreeSet<String>(s1);
		intersect.retainAll(s2);

		print("intersection", intersect);

	}

	protected static void print(String label, Collection<String> c) {

		System.out.println("--------------" + label + "--------------");

		for (String s : c) {
			System.out.println(s);
		}
	}
}
