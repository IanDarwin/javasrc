import java.io.*;
import java.util.*;

/** A trivial "database" for User objects, stored in a flat file.
 * <P>
 * Since this is exected to be used heavily, and to avoid the overhead
 * of re-reading the file, the "Singleton" Design Pattern is used
 * to ensure that there is only ever one instance of this class.
 */
public class UserDBDBM extends UserDB {
	protected final static String DEF_NAME = 
		"/home/ian/src/jabadot/userdb";		// It appends .pag

	protected DBM db;

	protected void init() throws IOException {
		singleton = new UserDBDBM(DEF_NAME);
	}

	public UserDB getInstance() throws IOException {
		if (singleton == null) {
			init();
		}
		return singleton;
	}

	/** Add one user to the list, both in-memory and on disk. */
	public synchronized void addUser(User nu) throws IOException {
		// Add it to the in-memory list
		super.addUser(nu);

		// Add it to the on-disk version: store in DB with
		// key = nickname, value = object.
		db.store(nu.getName(), nu);
	}

	/** Constructor is protected since it should only be called
	 * from within the init() method.
	 */
	protected UserDBDBM(String fn) throws IOException {
		super();
		
		db = new DBM(fn);
		String k;
		Object o;

		// Iterate through contents of DBM, adding into list.
		for (o=db.firstkeyObject(); o!=null; o=db.nextkey(o)) {
			// firstkey/nextkey give Key as Object, cast to String.
			k = (String)o;
			o = db.fetch(k);	// Get corresponding Value (a User)
			users.add((User)o);	// Add to list.
		}
	}

	/** Get the User object for a given nickname.
	 */
	public User getUser(String nick) {
		Iterator it = users.iterator();
		while (it.hasNext()) {
			User u = (User)it.next();
			if (u.getName().equals(nick))
				return u;
		}
		return null;
	}

}
