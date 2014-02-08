package database.jdbc;

import java.io.Console;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/** Test of loading a driver and connecting to a database.
 * Password reading REQUIRES JAVA 6 or later.
 */
public class PreparedStatementDemo {

	final static String DRIVER = "org.postgresql.Driver";

	public static void main(String[] av) throws Throwable {
		Console cons = System.console();
		if (cons == null) {
			throw new RuntimeException("No console, can't get password");
		}
		String url = "jdbc:postgresql:students"; //cons.readString("URL:");
		String user = "students"; // cons.readString("DB User:");
		char[] passwd = null;
			passwd = cons.readPassword("DB Password:");

			// Load the jdbc-odbc bridge driver
			Class.forName(DRIVER);

			// Enable logging
			DriverManager.setLogWriter(new PrintWriter((System.err)));

			Connection conn = 
				DriverManager.getConnection(url, user, new String(passwd));

			// Do something with the connection here...
			PreparedStatement ps = conn.prepareStatement(
					"Select firstname,lastname from student order by ?");
			ps.setString(1, "firstname");
//			final ParameterMetaData parameterMetaData = ps.getParameterMetaData();
//			Iterate through  parameterMetaData.getParameterCount() {
//				System.out.println(parameterMetaData.getParameterClassName(i));
//			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.printf("%s\t%s%n", rs.getString(1), rs.getString(2));
			}

			conn.close();	// All done with that DB connection

	}
}
