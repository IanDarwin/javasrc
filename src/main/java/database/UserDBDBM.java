import java.io.*;
import java.util.*;
import java.sql.SQLException;

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

	/** Default Constructor */
	protected UserDBDBM() throws IOException,SQLException {
		this(DEF_NAME);
	}

	/** Constructor */
	protected UserDBDBM(String fn) throws IOException,SQLException {
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

	/** Add one user to the list, both in-memory and on disk. */
	public synchronized void addUser(User nu) throws IOException, SQLException {
		// Add it to the in-memory list
		super.addUser(nu);

		// Add it to the on-disk version: store in DB with
		// key = nickname, value = object.
		db.store(nu.getName(), nu);
	}
}
