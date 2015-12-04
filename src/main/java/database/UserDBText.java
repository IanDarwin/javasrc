package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.StringTokenizer;

import domain.User;

/** A trivial "database" for User objects, stored in a flat file.
 * <P>
 * Since this is expected to be used heavily, and to avoid the overhead
 * of re-reading the file, the "Singleton" Design Pattern is used
 * to ensure that there is only ever one instance of this class.
 */
public class UserDBText extends UserDB {
	protected final static String DEF_NAME = "jabadb.txt";

	protected String fileName;

	protected UserDBText() throws IOException,SQLException {
		this(DEF_NAME);
	}

	/** Constructor */
	protected UserDBText(String fn) throws IOException,SQLException {
		super();
		BufferedReader is = null;
		try {
			fileName = fn;
			is = new BufferedReader(new FileReader(fn));
			String line;
			while ((line = is.readLine()) != null) {
				//name:password:fullname:City:Prov:Country:privs
				
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
		} finally {
			if (is != null)
				is.close();
		}
	}

	protected PrintWriter pw;

	public synchronized void addUser(User nu) throws IOException,SQLException {
		// Add it to the in-memory list
		super.addUser(nu);

		// Add it to the on-disk version
		if (pw == null) {
			pw = new PrintWriter(new FileWriter(fileName, true));
		}
		pw.println(toDB(nu));
		// toDB returns: name:password:fullname:City:Prov:Country:privs
		pw.flush();
	}

	protected String toDB(User u) {
		// #name:password:fullName:email:City:Prov:Country:privs
		char privs = '-';
		if (adminPrivs)
			privs = 'A';
		else if (editPrivs) 
			privs = 'E';
		
		return new StringBuffer()
			.append(u.getName()).append(':')
			.append(u.getPassword()).append(':')
			.append(u.getFullName()).append(':')
			.append(u.getEmail()).append(':')
			.append(u.getCity()).append(':')
			.append(u.getProvince()).append(':')
			.append(u.getCountry()).append(':')
			.append(privs)
			.toString();
	}

	/* (non-Javadoc)
	 * @see database.UserDB#setPassword(java.lang.String, java.lang.String)
	 */
	public void setPassword(String nick, String newPass) throws SQLException {
		// TODO Write a real method here
		
	}

	/* (non-Javadoc)
	 * @see database.UserDB#deleteUser(java.lang.String)
	 */
	public void deleteUser(String nick) throws SQLException {
		// TODO Write a real method here
		
	}
}
