package structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Linked list class, written out in full using Java.
 * WARNING: The LinkList class is hopelessly incomplete and
 * should not be used to save data, until somebody finishes it!
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class LinkListTest {
	private static final String END_OF_LIST = "End of list";
	List<String> list;
	
	@Before @SuppressWarnings("deprecation")
	public void setUp() {
		list = new LinkList<String>();
		list.add(new Object().toString());
		list.add("Hello");
		list.add(END_OF_LIST);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetTooFar() {
		list.get(list.size()); // last should be size() -1, so should fail
	}

	@Test
	public void testGetSecond() {
		assertTrue("get element", list.contains("Hello"));
	}

	@Test
	public void testSize() {
		assertEquals("list size", 3, list.size());
	}
	
	@Test
	public void testContains() {
		assertTrue(list.contains("Hello"));
	}
	
	@Test
	public void testAddAll() {
		Collection<String> c = Arrays.asList("One", "Two", "Three");
		list.addAll(c);
		assertEquals(6, list.size());
		assertEquals("Three", list.get(5));
	}
	
	@Test @Ignore("exhibits non-halting behavior")
	public void testAddWithIndex() {
		list.add(2, "Meh");
		assertEquals("list size post-insert", 4, list.size());
		//assertEquals(list.get(2), "Meh");
		//assertEquals(list.get(3), END_OF_LIST);
	}
}
