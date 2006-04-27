package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

/** Convert the database from text form to JDBC form.
 */
public class TextToJDBC {

	protected final static String TEXT_NAME = "users.txt";
	protected final static String DB_URL = "jdbc:idb:jabadb.prp";

	public static void main(String[] fn)
	throws ClassNotFoundException, SQLException, IOException {

		BufferedReader is = new BufferedReader(new FileReader(TEXT_NAME));

		// Load the database driver
		Class.forName("jdbc.idbDriver");

		System.out.println("Getting Connection");
		Connection conn = DriverManager.getConnection(
			DB_URL, "ian", "");	// user, password

		System.out.println("Creating Statement");
		Statement stmt = conn.createStatement();

		System.out.println("Creating table and index");
		stmt.executeUpdate("DROP TABLE users");
		stmt.executeUpdate("CREATE TABLE users (\n" +
			"name     char(12) PRIMARY KEY,\n" +
			"password char(20),\n" +
			"fullName char(30),\n" +
			"email    char(60),\n" +
			"city     char(20),\n" +
			"prov     char(20),\n" +
			"country  char(20),\n" +
			"privs    int\n" +
			")");
		stmt.executeUpdate("CREATE INDEX nickIndex ON users (name)");
		stmt.close();

		// put the data in the table
		PreparedStatement ps = conn.prepareStatement(
			"INSERT INTO users VALUES (?,?,?,?,?,?,?,?)");

		String line;
		while ((line = is.readLine()) != null) {
			//name:password:fullname:City:Prov:Country:privs

			if (line.startsWith("#")) {		// comment
				continue;
			}

			// XXX Why doesn't this use UserDBText?
			StringTokenizer st =
				new StringTokenizer(line, ":");
			String nick = st.nextToken();
			String pass = st.nextToken();
			String full = st.nextToken();
			String email = st.nextToken();
			String city = st.nextToken();
			String prov = st.nextToken();
			String ctry = st.nextToken();
			// User u = new User(nick, pass, full, email,
			//	city, prov, ctry);
			String privs = st.nextToken();
			int iprivs = 0;
			if (privs.indexOf("A") != -1) {
				iprivs |= User.P_ADMIN;
			}
			if (privs.indexOf("E") != -1) {
				iprivs |= User.P_EDIT;
			}
			ps.setString(1, nick);
			ps.setString(2, pass);
			ps.setString(3, full);
			ps.setString(4, email);
			ps.setString(5, city);
			ps.setString(6, prov);
			ps.setString(7, ctry);
			ps.setInt(8, iprivs);
			ps.executeUpdate();
		}
		ps.close();		// All done with that statement
		conn.close();	// All done with that DB connection
		return;			// All done with this program.
	}
}
