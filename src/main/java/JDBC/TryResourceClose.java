package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.darwinsys.sql.MockJDBCConnection;

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
				}
			}
		}
	}

	private static Connection getConnection() {
		return new MockJDBCConnection();
	}
}
