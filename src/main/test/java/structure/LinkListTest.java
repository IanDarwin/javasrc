import java.util.*;

/**
 * Linked list class, written out in full using Java.
 * @deprecated	Supplanted by java.util.LinkedList
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class LinkListTest {
	public static void main(String[] argv) {
		System.out.println("Here is a demo of implementing a List in Java");
		System.out.println("(you know it don't come easy....)");
		LinkList l = new LinkList();
		l.add(new Object());
		l.add("Hello");
		l.add("End of list");
		System.out.println("Here is a list of all the elements, from get");
		System.out.println(l.get(0));
		System.out.println(l.get(1));
		System.out.println(l.get(2));

		if (l.lookup("Hello"))
			System.err.println("Lookup works");
		else
			System.err.println("Lookup does not work");

		ListIterator li = l.listIterator();
		if (li == null) {
			throw new IllegalStateException("listIterator() returned null");
		}

		// Run the list frontwards
		while (li.hasNext())
			System.out.println("Next to: " + li.next());

		// Now, for added fun, let's walk the linked list backwards.
		while (li.hasPrevious()) {
			System.out.println("Back to: " + li.previous());
		}
		
	}
}
