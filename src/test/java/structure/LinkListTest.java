package structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Linked list class, written out in full using Java.
 * WARNING: The LinkList class is not necessarily complete and
 * should not be used to save data, until somebody finishes it!
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class LinkListTest {
	private static final String FIRST_STRING = "I am the first",
		STRING_HELLO = "Hello",
		LAST_STRING = "End of list";
	// Array is parallel to 'list' except when list modified,
	// then it's set to null to avoid confusion later in such tests.
	private static String[] testData = {
			FIRST_STRING,
			STRING_HELLO,
			LAST_STRING,
	};
	List<String> list;
	
	@Before @SuppressWarnings("deprecation")
	public void setUp() {
		list = new LinkList<String>();
		list.add(FIRST_STRING);
		list.add(STRING_HELLO);
		list.add(LAST_STRING);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetTooFar() {
		list.get(list.size()); // last should be size() - 1, so should fail
	}

	@Test
	public void testGetSecond() {
		assertEquals("get element", testData[1], list.get(1));
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetOnEmptyList() {
		list.clear();
		list.get(0);
	}

	@Test
	public void testSize() {
		assertEquals("list size", 3, list.size());
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testPutTooFar() {
		list.add(3, "You won't see this");
	}

	@Test
	public void testContains() {
		assertTrue(list.contains(STRING_HELLO));
	}
	
	@Test
	public void testIterable() {
		Iterator<String> iter = list.iterator();
		assertEquals(FIRST_STRING, iter.next());
		assertEquals(STRING_HELLO, iter.next());
		assertEquals(LAST_STRING, iter.next());
	}
	
	@Test
	public void testAddAll() {
		Collection<String> c = Arrays.asList("One", "Two", "Three");
		list.addAll(c);
		assertEquals(6, list.size());
		assertEquals("Three", list.get(5));
	}

	@Test //@Ignore("exposes non-halting behavior")
	public void testAddWithIndex() {
		list.add(1, "Meh");
		testData = null;
		assertEquals("list size post-insert", 4, list.size());
		assertEquals("Meh", list.get(2));
		//assertEquals(END_OF_LIST, list.get(4));
	}

	@Test
	public void testToArrayProvided() {
		String[] data = new String[list.size()];
		list.toArray(data);
		assertEquals(STRING_HELLO, data[1]);
		assertEquals(LAST_STRING, data[data.length - 1]);
	}
}
