package database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.User;

/** A base for several "database" accessors for User objects.
 * We use a Singleton access method for efficiency and to enforce
 * single access on the database, which means we can keep an in-memory
 * copy (in an ArrayList) perfectly in synch with the database.
 */
public abstract class UserDB {
	protected boolean adminPrivs, editPrivs;

	/** The in-memory copy of the data */
	protected List<User> users;

	/** The only instance of this class. */
	protected static final UserDB singleton;

	public static final int NAME = 0;
	
	public static final int PASSWORD = 2;

	public static final int FULLNAME = 1;

	public static final int EMAIL = 3;

	public static final int CITY = 5;
	
	public static final int PROVINCE = 6;

	public static final int COUNTRY = 7;

	public static final int PRIVS = 8;

	/** Static initializer to initialize the Singleton.
	 * It is created as the non-abstract subclass formerly named
	 * in the context parameter "jabadot.jabadb.class"
	 * but now hardcoded as "jabadot.UserDBJDBC"
	 */
	static {
		String dbClass = null;
		try {
			dbClass = "jabadot.UserDBJDBC";
			singleton = (UserDB)Class.forName(dbClass).newInstance();
			//singleton = new UserDBJDBC();
		} catch (Exception ex) {
			System.err.println(
			"Unexpected exception: Unable to initialize UserDB singleton");
			ex.printStackTrace(System.err);
			throw new IllegalArgumentException(ex.toString());
		}
	}

	/** In some subclasses the constructor will probably load the database,
	 *  while in others it may defer this until getUserList().
	 */
	protected UserDB() throws IOException, SQLException {
		users = new ArrayList<User>();
	}

	/** "factory" method to get an instance, which will always be
	 * the Singleton.
	 */
	public static UserDB getInstance() {
		if (singleton == null)
			throw new IllegalStateException(
				"UserDB initialization failed (singleton was null)");
		return singleton;
	}

	/** Get the list of users. */
	public List<User> getUserList() {
		return users;
	}

	/** Get the User object for a given nickname */
	public User getUser(String nick) {
		for (User u : users) {
			if (u.getName().equals(nick))
				return u;
		}
		return null;
	}

	public synchronized void addUser(User nu) throws IOException, SQLException {
		// Add it to the in-memory list
		users.add(nu);

		// Add it to the on-disk version
		// N.B. - must be done in subclass.
	}

	public abstract  void setPassword(String nick, String newPass) 
	throws SQLException;

	public abstract void deleteUser(String nick)
	throws SQLException;
}
