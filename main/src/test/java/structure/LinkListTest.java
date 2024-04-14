package structure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Linked list class, written out in full using Java.
 * WARNING: The LinkList class is not necessarily complete or perfect.
 * You should probably use java.util.LinkedList instead!
 * @author	Ian Darwin, https://darwinsys.com/
 */
public class LinkListTest {
	private static final String FIRST_STRING = "First of Three",
		MIDDLE_STRING = "Second of Three",
		LAST_STRING = "Third of Three";
	// Array is parallel to 'list' except when list modified,
	// then it's set to null to avoid confusion later in such tests.
	private final static String[] testData = {
			FIRST_STRING,
			MIDDLE_STRING,
			LAST_STRING,
	};
	List<String> list;
	
	@Before @SuppressWarnings("deprecation")
	public void setUp() {
		list = new LinkList<>();
		assertTrue(list.add(FIRST_STRING));
		assertTrue(list.add(MIDDLE_STRING));
		assertTrue(list.add(LAST_STRING));
	}
	
	@Test
	public void testAddAll() {
		Collection<String> c = Arrays.asList("One", "Two", "Three");
		assertTrue(list.addAll(c));
		assertEquals(6, list.size());
		assertEquals("Three", list.get(5));
	}

	@Test
	public void testAddAllIndexed() {
		Collection<String> c = Arrays.asList("One", "Two", "Three");
		assertTrue(list.addAll(1, c));
		assertEquals(6, list.size());
		// list.forEach(System.out::println);
		assertEquals("One", list.get(2));
		assertEquals(LAST_STRING, list.get(5));
	}

	@Test
	public void testAddWithIndex() {
		final String MY_STRING = "Meh";
		// This overload of add() curiously returns void
		list.add(1, MY_STRING);
		assertEquals("list size post-insert", 4, list.size());
		assertEquals("Meh", list.get(2));
		assertEquals(LAST_STRING, list.get(3));
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testAddTooFar() {
		list.add(3, "You won't see this");
	}

	@Test
	public void testContains() {
		assertTrue(list.contains(MIDDLE_STRING));
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetTooFar() {
		list.get(list.size()); // last should be size() - 1, so should fail
	}

	@Test
	public void testGetSecond() {
		assertEquals("get element", testData[1], list.get(1));
	}

	@Test(expected=NoSuchElementException.class)
	public void testGetOnEmptyList() {
		list.clear();
		list.getFirst();
	}

	@Test
	public void testIterable() {
		Iterator<String> iter = list.iterator();
		assertEquals(FIRST_STRING, iter.next());
		assertEquals(MIDDLE_STRING, iter.next());
		assertEquals(LAST_STRING, iter.next());
	}
	
	private String string = null;
	private void setString(String string) {
		this.string = string;
	}

	@Test
	public void testIterableAgain() {
		list.forEach(o -> setString(o));
		assertEquals(LAST_STRING, string);
	}
	
	@Test
	public void testRemoveFirst() {
		assertTrue(list.remove(FIRST_STRING));
		assertEquals(2, list.size());
		assertSame(MIDDLE_STRING, list.getFirst());
	}
	
	@Test
	public void testRemoveMIDDLE() {
		assertTrue(list.remove(MIDDLE_STRING));
		assertEquals(2, list.size());
		assertSame(FIRST_STRING, list.getFirst());
		assertSame(LAST_STRING, list.get(1));
	}
	
	@Test
	public void testRemoveLast() {
		assertTrue(list.remove(LAST_STRING));
		assertEquals(2, list.size());
		assertSame(FIRST_STRING, list.getFirst());
		assertSame(MIDDLE_STRING, list.get(1));
	}
	
	@Test
	public void testSet() {
		assertEquals(MIDDLE_STRING, list.get(1));
		list.set(1, "Woo Woo");
		assertEquals("Woo Woo", list.get(1));
	}

	@Test
	public void testSize() {
		assertEquals("list size", 3, list.size());
	}

	@Test
	public void testToArrayProvided() {
		String[] data = new String[list.size()];
		list.toArray(data);
		assertEquals(MIDDLE_STRING, data[1]);
		assertEquals(LAST_STRING, data[data.length - 1]);
	}
}
