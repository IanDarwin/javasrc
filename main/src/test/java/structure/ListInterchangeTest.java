package structure;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

public class ListInterchangeTest {

	String[] raw = { "zero", "one", "two", "three", "four" };

	List<String> list = Arrays.asList(raw);

	public void testAscending() {
		ListInterchange.interchange(list, 2, 3);
		System.out.println(
				"ListInterchangeTest.testAscending(): " + list);
		assertEquals("three", list.get(2));
		assertEquals("two", list.get(3));
	}

	public void testNonAdjacent() {
		ListInterchange.interchange(list, 0, 2);
		System.out.println(
			"ListInterchangeTest.testNonAdjacent(): " + list);
		assertEquals("zero", list.get(2));
		assertEquals("two", list.getFirst());
	}

	public void testDescending() {

		ListInterchange.interchange(list, 3, 2);
		assertEquals("three", list.get(2));
		assertEquals("two", list.get(3));
	}
}
