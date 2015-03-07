package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import domain.User;

/** A UserDB using JDBC and a relational DBMS..
 * We use the inherited getUser ("Find the User object for a given nickname")
 * since we keep everything in memory in this version.
 * <p>
 * ToDo: Consider whether this should be an Entity EJB.
 */
// BEGIN main
public class UserDBJDBC extends UserDB {

	protected PreparedStatement setPasswordStatement;
	protected PreparedStatement addUserStmt;
	protected PreparedStatement setLastLoginStmt;
	protected PreparedStatement deleteUserStmt;

	/** insert the dozen or so fields into the user database */
	final static String SQL_INSERT_USER =
		"insert into users " +
		" values (?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/** Default constructor */
	protected UserDBJDBC() throws NamingException, SQLException, IOException {
		super();

		System.out.println("UserDBJDBC.<init> starting...");
		
		System.out.println("Loading Driver Class");
		try {
			Class.forName("org.hsqldb.jdbcDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("FAILED: " + ex.toString());
			throw new IllegalStateException(ex.toString());
		}
		Connection conn = DriverManager.getConnection(
			"jdbc:hsqldb:/home/ian/src/jabadot/WEB-INF/jabadot",
			"jabadmin", "fredonia");

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from users");

		while (rs.next()) {
			//name:password:fullname:City:Prov:Country:privs

			// Get the fields from the query.
			// Could be an Entity EJB with CMP: this is unnecessarily 
			// chummy with the SQL. See CreateUserDatabase.java for field#'s!
			int i = 1;
			String nick = rs.getString(i++).trim();
			String pass = rs.getString(i++).trim();
			// System.err.println(nick + " (" + pass + ")");
			String first = rs.getString(i++);
			String last = rs.getString(i++);
			String email = rs.getString(i++);
			String city = rs.getString(i++);
			String prov = rs.getString(i++);
			String ctry = rs.getString(i++);
			java.sql.Date credt = rs.getDate(i++);
			java.sql.Date lastlog = rs.getDate(i++);
			String skin = rs.getString(i++);
			boolean editPrivs = rs.getBoolean(i++);
			boolean adminPrivs = rs.getBoolean(i++);

			// Construct a user object from the fields
			// System.out.println("Constructing User object");
			User u = new User(nick, pass, first, last, email,
				city, prov, ctry, credt, lastlog,
				skin, editPrivs, adminPrivs);
			// System.out.println("Adding User object " + u + " to " + users);
			// Add it to the in-memory copy.
			users.add(u);
			// System.err.println("User " + nick + "; pass " + pass.charAt(0));
		}
		rs.close();		// All done with that resultset
		stmt.close();

		// Set up the PreparedStatements now so we don't have to
		// re-create them each time needed.
		addUserStmt = conn.prepareStatement(SQL_INSERT_USER);
		setPasswordStatement = conn.prepareStatement(
			"update users SET password = ? where name = ?");
		setLastLoginStmt = conn.prepareStatement(
			"update users SET lastLogin = ? where name = ?");
		deleteUserStmt = conn.prepareStatement(
			"delete from users where name = ?");
		
		conn.close();
	}

	/** Add one user to the list, both in-memory and on disk. */
	public synchronized void addUser(User nu)
	throws IOException, SQLException {
		// Add it to the in-memory list
		super.addUser(nu);

		// Copy fields from user to DB
		// XXX WAY INCOMPLETE NOW
		int i = 1;
		addUserStmt.setString(i++, nu.getName());
		addUserStmt.setString(i++, nu.getPassword());
		addUserStmt.setString(i++, nu.getFirstName()); 
		addUserStmt.setString(i++, nu.getLastName());
		addUserStmt.setString(i++, nu.getEmail());
		addUserStmt.setString(i++, nu.getCity());
		addUserStmt.setString(i++, nu.getProvince());
		addUserStmt.setString(i++, nu.getCountry());
		java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
		addUserStmt.setDate(i++, now);
		addUserStmt.setDate(i++, now);
		addUserStmt.setString(i++, nu.getSkin());
		addUserStmt.setBoolean(i++, false);
		addUserStmt.setBoolean(i++, false);
		--i;

		if (i != 13) {
			System.out.println("Warning: not enough fields set! i = " + i);
		}

		// Store in persistent DB
		addUserStmt.executeUpdate();
	}

	public void deleteUser(String nick) throws SQLException {
		// Find the user object
		User u = getUser(nick);
		if (u == null) {
			throw new SQLException("User " + nick + " not in in-memory DB");
		}
		deleteUserStmt.setString(1, nick);
		int n = deleteUserStmt.executeUpdate();
		if (n != 1) {	// not just one row??
			/*CANTHAPPEN */
			throw new SQLException("ERROR: deleted " + n + " rows!!");
		}

		// IFF we deleted it from the DB, also remove from the in-memory list
		users.remove(u);
	}

	public synchronized void setPassword(String nick, String newPass) 
	throws SQLException {

		// Find the user object
		User u = getUser(nick);

		// Change it in DB first; if this fails, the info in
		// the in-memory copy won't be changed either.
		setPasswordStatement.setString(1, newPass);
		setPasswordStatement.setString(2, nick);
		setPasswordStatement.executeUpdate();

		// Change it in-memory
		u.setPassword(newPass);
	}

	/** Update the Last Login Date field. */
	public synchronized void setLoginDate(String nick, java.util.Date date) 
	throws SQLException {
	
		// Find the user object
		User u = getUser(nick);

		// Change it in DB first; if this fails, the date in
		// the in-memory copy won't be changed either.
		// Have to convert from java.util.Date to java.sql.Date here.
		// Would be more efficient to use java.sql.Date everywhere.
		setLastLoginStmt.setDate(1, new java.sql.Date(date.getTime()));
		setLastLoginStmt.setString(2, nick);
		setLastLoginStmt.executeUpdate();

		// Change it in-memory
		u.setLastLoginDate(date);
	}
}
// END main
