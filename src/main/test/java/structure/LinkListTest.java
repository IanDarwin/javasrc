package structure;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

/**
 * Linked list class, written out in full using Java.
* @author	Ian Darwin, http://www.darwinsys.com/
 */
public class LinkListTest extends TestCase {
	List<String> list;
	
	@SuppressWarnings("deprecation")
	public void setUp() {
		list = new LinkList();
		list.add(new Object().toString());
		list.add("Hello");
		list.add("End of list");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
	}

	public void testGet() {
		assertTrue(list.contains("Hello"));
	}

	public void testSize() {
		assertEquals(3, list.size());
	}

	public void testIterator() {

		Iterator<String> li = list.iterator();
		assertNotNull(li);

		// Run the list frontwards
		while (li.hasNext()) {
			System.out.println("Next to: " + li.next());
		}
	}
}
