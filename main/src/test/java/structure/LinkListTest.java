package structure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Linked list class, written out in full using Java.
 * WARNING: The LinkList class is not necessarily complete or perfect.
 * You should probably use java.util.LinkedList instead!
 * @author	Ian Darwin, https://darwinsys.com/
 */
class LinkListTest {
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

	@BeforeEach
	@SuppressWarnings("deprecation")
	void setUp() {
		list = new LinkList<>();
		assertTrue(list.add(FIRST_STRING));
		assertTrue(list.add(MIDDLE_STRING));
		assertTrue(list.add(LAST_STRING));
	}

	@Test
	void addAll() {
		Collection<String> c = Arrays.asList("One", "Two", "Three");
		assertTrue(list.addAll(c));
		assertEquals(6, list.size());
		assertEquals("Three", list.get(5));
	}

	@Test
	void addAllIndexed() {
		Collection<String> c = Arrays.asList("One", "Two", "Three");
		assertTrue(list.addAll(1, c));
		assertEquals(6, list.size());
		// list.forEach(System.out::println);
		assertEquals("One", list.get(2));
		assertEquals(LAST_STRING, list.get(5));
	}

	@Test
	void addWithIndex() {
		final String MY_STRING = "Meh";
		// This overload of add() curiously returns void
		list.add(1, MY_STRING);
		assertEquals(4, list.size(), "list size post-insert");
		assertEquals("Meh", list.get(2));
		assertEquals(LAST_STRING, list.get(3));
	}

	@Test
	void addTooFar() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(3, "You won't see this");
		});
	}

	@Test
	void contains() {
		assertTrue(list.contains(MIDDLE_STRING));
	}

	@Test
	void getTooFar() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.get(list.size()); // last should be size() - 1, so should fail
		}); // last should be size() - 1, so should fail
	}

	@Test
	void getSecond() {
		assertEquals(testData[1], list.get(1), "get element");
	}

	@Test
	void getOnEmptyList() {
		assertThrows(NoSuchElementException.class, () -> {
			list.clear();
			list.getFirst();
		});
	}

	@Test
	void iterable() {
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
	void iterableAgain() {
		list.forEach(o -> setString(o));
		assertEquals(LAST_STRING, string);
	}

	@Test
	void removeFirst() {
		assertTrue(list.remove(FIRST_STRING));
		assertEquals(2, list.size());
		assertSame(MIDDLE_STRING, list.getFirst());
	}

	@Test
	void removeMIDDLE() {
		assertTrue(list.remove(MIDDLE_STRING));
		assertEquals(2, list.size());
		assertSame(FIRST_STRING, list.getFirst());
		assertSame(LAST_STRING, list.get(1));
	}

	@Test
	void removeLast() {
		assertTrue(list.remove(LAST_STRING));
		assertEquals(2, list.size());
		assertSame(FIRST_STRING, list.getFirst());
		assertSame(MIDDLE_STRING, list.get(1));
	}

	@Test
	void set() {
		assertEquals(MIDDLE_STRING, list.get(1));
		list.set(1, "Woo Woo");
		assertEquals("Woo Woo", list.get(1));
	}

	@Test
	void size() {
		assertEquals(3, list.size(), "list size");
	}

	@Test
	void toArrayProvided() {
		String[] data = new String[list.size()];
		list.toArray(data);
		assertEquals(MIDDLE_STRING, data[1]);
		assertEquals(LAST_STRING, data[data.length - 1]);
	}
}
