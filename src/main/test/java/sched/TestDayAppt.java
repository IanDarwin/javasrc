import java.util.*;

/** Confirm that Appts compare, sort, print, and read back correctly.
 * @version $Id$
 */
public class TestDayAppt {
	public static void main(String va[]) {
		TreeSet days = new TreeSet();
		// Exercise static factory method.
		days.add(Appt.fromString("1951 4 24 6 0 birthday"));
		days.add(Appt.fromString("1956 9 3 13 10 birthday"));
		days.add(Appt.fromString("2001 1 1 0 0 3rd millenium"));

		// Exercise constructor.
		days.add(new Appt("go home", 1999, 1, 23, 12, 11));

		// Exercise compareTo() method by inserting duplicates
		// into the TreeSet - ensure that only one copy is added.
		days.add(new Appt("go home", 1999, 1, 23, 12, 10));
		days.add(new Appt("go home", 1999, 1, 23, 12, 10));

		// Print the whole mess. Exercises toString().
		Iterator it = days.iterator();
		while (it.hasNext())
			System.out.println(it.next());

		Appt a = new Appt("testing 1 2 3", 1, 2, 3, 1, 2);

		if (!a.equals(a))
			throw new RuntimeException(
				"a.equals(a) failed!");
		if (!a.fromString(a.toString()).equals(a))
			throw new RuntimeException(
				"fromString(toString()) not idempotent");

		System.out.println();
		System.out.println("All tests passed.");
	}
}
