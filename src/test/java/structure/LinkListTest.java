package structure;

import java.util.List;

import junit.framework.TestCase;

/**
 * Linked list class, written out in full using Java.
 * XXX WARNING: The LinkList class is hopelessly incomplete and
 * should never be used for anything.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class LinkListTest extends TestCase {
	List<String> list;
	
	@SuppressWarnings("deprecation")
	public void setUp() {
		list = new LinkList<String>();
		list.add(new Object().toString());
		list.add("Hello");
		list.add("End of list");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
	}

	public void testGetSecond() {
		assertTrue("get first", list.contains("Hello"));
	}

	public void testSize() {
		assertEquals(3, list.size());
	}

}
