package database.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Show the syntax for auto-closing via Closable interface.
 *
 * CAN NOT BE RUN; JUST SHOWS THE SYNTAX.
 * 
 *  * P L E A S E   R E A D   B E F O R E   C O M P L A I N I N G
 * This class absolutely requires Java SE 7+, so just add an exclusion rule
 * (Build Path -> Exclude) if you are living with a legacy version of Java SE.
 *
 * @author Ian Darwin
 */
public class TryResourceClose {
	
	public static void main(String[] args) {
		System.err.println("This demo cannot be run, it is just to show the syntax.");
		System.exit(1);
	}
	
	public static void showTryWithResources() throws SQLException {

		String query = "SELECT id,firstname,lastname FROM person";

		try (Connection conn = getConnection()) {
			try (Statement stmt = conn.createStatement()) {
				try (ResultSet rs = stmt.executeQuery(query)) {

					while (rs.next()) {
						int id = rs.getInt(1);
						String firstName = rs.getString(2);
						String lastName = rs.getString(3);
						System.out.printf("Person %d: %s %s%n", id, firstName, lastName);
					}
				} // No need for try { rs.close(); } catch (SQLException e) { }; Closable handles it.
			}
		}
	}

	private static Connection getConnection() {
		return null;
	}
}
