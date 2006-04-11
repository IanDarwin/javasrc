package structure;

import java.util.ArrayList;
import java.util.List;

/** Code Fragmment showing how to insert in two lists (an ArrayList
 * and an AWT List) in sorted order, using a simple linear search
 * of the ArrayList to find the object (or end of list) before which
 * to insert the new user. 
 */
public class CallTrack {

	/** The list of Person objects. */
	protected List<Person> usrList = new ArrayList<Person>();

	/** The scrolling list */
	protected java.awt.List visList = new java.awt.List();

	/** Add one (new) Person to the list, keeping the list sorted. */
	protected void add(Person p) {
		String lastName = p.getLastName();
		int i;
		for (i=0; i<usrList.size(); i++)
			if (lastName.compareTo(((Person)(usrList.get(i))).getLastName()) <= 0)
				break;
		usrList.add(i, p);
		visList.add(p.getName(), i);
		visList.select(i);      // ensure current
	}

}
