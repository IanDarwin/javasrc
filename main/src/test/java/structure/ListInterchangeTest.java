package structure;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ListInterchangeTest extends TestCase {

	String[] raw = { "zero", "one", "two", "three", "four" };

	List<String> list = new ArrayList<String>();

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		for (String s : raw) {
			list.add(s);
		}
	}

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
		assertEquals("two", list.get(0));
	}

	public void testDescending() {

		ListInterchange.interchange(list, 3, 2);
		assertEquals("three", list.get(2));
		assertEquals("two", list.get(3));
	}
}
