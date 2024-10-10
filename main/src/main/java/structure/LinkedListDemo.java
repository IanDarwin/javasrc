package structure;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Demo of java.util.LinkedList class
 * using same example as my older LinkList 
 * class (LinkListTest.java)
 * @author Ian Darwin, https://darwinsys.com/
 */
// tag::main[]
public class LinkedListDemo {
	public static void main(String[] argv) {
		System.out.println("Here is a demo of Java's LinkedList class");
		LinkedList<String> l = new LinkedList<>();
		l.add(new Object().toString());
		l.add("Hello");
		l.add("end of the list");

		System.out.println("Here is a list of all the elements");
		l.forEach(o -> 
			System.out.println("Next element: " + o));

		if (l.indexOf("Hello") != 1)
			System.err.println("Lookup does not work");
		else
			System.err.println("Lookup works");

		// Now, for added fun, let's walk the linked list backwards.
		ListIterator<String> li = l.listIterator();
		// (have to get to the end first)
		while (li.hasNext())
			li.next();
		while (li.hasPrevious()) {
			System.out.println("Backwards we find: " + li.previous());
		}
	}
}
// end::main[]
