package structure;

import java.util.TreeSet;

/**
 * TreeSet Demo.
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class TreeSetDemo {
	public static void main(String[] argv) {
		//+
		/* A TreeSet keeps objects in sorted order. We use a
		 * Comparator published by String for case-insensitive
		 * sorting order.
		 */
		TreeSet tm = new TreeSet(String.CASE_INSENSITIVE_ORDER);
		tm.add("Gosling");
		tm.add("da Vinci");
		tm.add("van Gogh");
		tm.add("Java To Go");
		tm.add("Vanguard");
		tm.add("Darwin");
		tm.add("Darwin");	// TreeSet is Set, ignores duplicates.

		// Since it is sorted we can ask for various subsets
		System.out.println("Lowest (alphabetically) is " + tm.first());
		// Print how many elements are greater than "k"
		System.out.println(tm.tailSet("k").toArray().length + " elements higher than \"k\"");

		// Print the whole list in sorted order
		System.out.println("Sorted list:");
		java.util.Iterator t = tm.iterator();
		while (t.hasNext())
			System.out.println(t.next());
		//-
	}
}
