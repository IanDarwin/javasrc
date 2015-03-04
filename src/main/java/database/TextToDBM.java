package database;

import java.io.*;
import java.util.*;

import dbm.DBM;
import domain.User;

/** Convert the database from text form to DBM form.
 */
public class TextToDBM {

	protected final static String TEXT_NAME = "users.txt";
	protected final static String DBM_NAME = "users";

	public static void main(String[] fn) throws IOException {
		BufferedReader is = new BufferedReader(new FileReader(TEXT_NAME));
		DBM db = new DBM(DBM_NAME);

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
			db.store(nick, u);
		}
		db.close();
		is.close();
	}
}
