import java.io.*;
import java.util.*;

/** A trivial "database" for User objects, stored in a flat file.
 * <P>
 * Since this is exected to be used heavily, and to avoid the overhead
 * of re-reading the file, the "Singleton" Design Pattern is used
 * to ensure that there is only ever one instance of this class.
 */
public class UserDBText extends UserDB {
	protected final static String DEF_NAME = 
		"/home/ian/src/jabadot/userdb.txt";

	protected String fileName;

	protected void init() throws IOException {
		singleton = new UserDBText(DEF_NAME);
	}

	public UserDB getInstance() throws IOException {
		if (singleton == null) {
			init();
		}
		return singleton;
	}

	/** Constructor is protected since it should only be called
	 * from within the init() method.
	 */
	protected UserDBText(String fn) throws IOException {
		super();
		fileName = fn;
		BufferedReader is = new BufferedReader(new FileReader(fn));
		String line;
		while ((line = is.readLine()) != null) {
			//name:passwd:fullname:City:Prov:Country:privs

			if (line.startsWith("#")) {		// comment
				continue;
			}

			StringTokenizer st =
				new StringTokenizer(line, ":");
			String nick = st.nextToken();
			String pass = st.nextToken();
			String full = st.nextToken();
			String email = st.nextToken();
			String city = st.nextToken();
			String prov = st.nextToken();
			String ctry = st.nextToken();
			User u = new User(nick, pass, full, email,
				city, prov, ctry);
			String privs = st.nextToken();
			if (privs.indexOf("A") != -1) {
				u.setAdminPrivileged(true);
			}
			users.add(u);
		}
	}

	/** Get the User object for a given nickname */
	public User getUser(String nick) {
		Iterator it = users.iterator();
		while (it.hasNext()) {
			User u = (User)it.next();
			if (u.getName().equals(nick))
				return u;
		}
		return null;
	}

	protected PrintWriter pw;

	public synchronized void addUser(User nu) throws IOException {
		// Add it to the in-memory list
		super.addUser(nu);

		// Add it to the on-disk version
		// name:passwd:fullname:City:Prov:Country:privs
		if (pw == null)
			pw = new PrintWriter(new FileWriter(fileName, true));
		pw.println(nu.toDB());
		pw.flush();
	}
}
