package structure;

import java.util.ArrayList;
import java.util.List;

import domain.Person;

/** Code Fragmment showing how to insert in two lists (an ArrayList
 * and an AWT List) in sorted order, using a simple linear search
 * of the ArrayList to find the object (or end of list) before which
 * to insert the new user. 
 */
// tag::main[]
public class CallTrack {

	/** The list of Person objects. */
	protected List<Person> usrList = new ArrayList<>();

	/** The scrolling list */
	protected java.awt.List visList = new java.awt.List();

	// tag::addInSorted[]
	/** Add one (new) Person to the list, keeping the list sorted. */
	protected void add(Person p) {
		String lastName = p.getLastName();
		int i;
		// Find in "i" the position in the list where to insert this person
		for (i=0; i<usrList.size(); i++)
			if (lastName.compareTo((usrList.get(i)).getLastName()) <= 0)
				break; // If we don't break, OK, will insert at end of list.
		usrList.add(i, p);
	// end::addInSorted[]

		// Now insert them in the scrolling list, in the same position.
		visList.add(p.getFullName(), i);
		visList.select(i);      // ensure current
	}

}
// end::main[]
