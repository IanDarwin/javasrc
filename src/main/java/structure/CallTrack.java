/** Code Fragmment showing how to insert in two lists (an ArrayList
 * and an AWT List) in sorted order, using a simple linear search
 * of the ArrayList to find the object (or end of list) before which
 * to insert the new user. 
 */

/** The list of User objects. */
ArrayList usrList = new Vector();
/** The scrolling list */
java.awt.List visList = new List();
/** Add one (new) Candidate to the lists */
protected void add(Candidate c) {
	String n = c.lastname;
	int i;
	for (i=0; i<usrList.size(); i++)
		if (n.compareTo(((User)(usrList.elementAt(i))).lastname) <= 0)
			break;
	visList.add(c.getName(), i);
	usrList.add(i, c);
	visList.select(i);      // ensure current
}
