package structure;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

/**
 * Linked list class, written out in full using Java.
 * @deprecated	Supplanted by java.util.LinkedList
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class LinkListTest extends TestCase {
	List<String> list;
	public void setUp() {
		System.out.println("Here is a demo of implementing a List in Java");
		System.out.println("(you know it don't come easy....)");
		list = new LinkList();
		list.add(new Object().toString());
		list.add("Hello");
		list.add("End of list");
		System.out.println("Here is a list of all the elements, from get");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
	}

	public void testGet() {

		assertTrue(list.contains("Hello"));
		System.out.println("Lookup seems to work");
	}

	public void testSize() {
		assertEquals(3, list.size());
	}

	public void testIterator() {

		Iterator li = list.iterator();
		assertNotNull(li);

		// Run the list frontwards
		while (li.hasNext()) {
			System.out.println("Next to: " + li.next());
		}
	}
}
