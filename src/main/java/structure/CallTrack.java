/** The list of User objects. */
Vector usrList = new Vector();
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
		usrList.insertElementAt(c, i);
		visList.select(i);      // ensure current
	}
