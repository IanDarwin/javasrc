import java.util.*;

/**
 * Demo of 1.2 java.util.LinkedList class
 * using same example as my older LinkList class.
 * @author	Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class LinkedListDemo {
	public static void main(String[] argv) {
		System.out.println("Here is a demo of Java 1.2's LinkedList class");
		LinkedList l = new LinkedList();
		l.add(new Object());
		l.add("Hello");

		System.out.println("Here is a list of all the elements");
		ListIterator li = l.listIterator(0);
		while (li.hasNext())
			System.out.println(li.next());

		if (l.indexOf("Hello") < 0)
			System.err.println("Lookup does not work");
		else
			System.err.println("Lookup works");
	}
}
