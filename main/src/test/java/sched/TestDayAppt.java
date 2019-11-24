package sched;

import java.util.*;

import junit.framework.TestCase;

/** Confirm that Appts compare, sort, print, and read back correctly.
 */
public class TestDayAppt extends TestCase {
	
	public void testFactoryAndGetters() {
		
		// Exercise static factory method.
		Appt a1 = Appt.fromString("1951 4 24 6 0 birthday");
		assertEquals("fromString and getters", 1951, a1.getYear());
		assertEquals("fromString and getters",    4, a1.getMonth());
		assertEquals("fromString and getters",   24, a1.getDay());
		assertEquals("fromString and getters",    6, a1.getHour());
		assertEquals("fromString and getters",    0, a1.getMinute());
		assertEquals("fromString and getters", "birthday", a1.getText());
	}
	
	public void testFactoryFailures() {

		try {
			Appt.fromString(null);
			fail("did not throw expected NullPointerException");
		} catch (NullPointerException e) {
			// OK
		}

		try {
			Appt.fromString("random garbage");
			fail("did not throw expected IllegalArumentException");
		} catch (IllegalArgumentException e) {
			// OK
		}

		try {
			Appt.fromString("1951 foo 24 6 0 birthday");
			fail("did not throw expected IllegalArumentException");
		} catch (IllegalArgumentException e) {
			// OK
		}
	}
	
	public void testConstructorAndComparisons() {
		TreeSet<Appt> days =  new TreeSet<Appt>();
		// Exercise compareTo() method by inserting duplicates
		// into the TreeSet - ensure that only one copy is added.
		days.add(new Appt("go home", 1999, 1, 23, 12, 10));
		days.add(new Appt("go home", 1999, 1, 23, 12, 10));
		assertEquals("compareto", 1, days.size());

		Appt a = new Appt("testing 1 2 3", 1, 2, 3, 1, 2);

		assertEquals("a is a", a, a);	
		
		assertEquals("fromString(toString()) idempotent", 
			Appt.fromString(a.toString()), a);
	}
}
