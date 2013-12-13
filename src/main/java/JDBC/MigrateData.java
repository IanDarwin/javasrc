package JDBC;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Migrate data from one database to another using JDBC.
 * Written using try-with-resource to avoid "jdbc cleanup hell".
 */
public class MigrateData {


	public static void main(String[] av) throws Throwable {
		final String IN_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
		final String OUT_DRIVER = "org.postgresql.Driver";
		String inUrl = "jdbc:odbc:Huh?";
		String inUser = "students";
		String inPasswd = "flibbetywidget";

		String outUrl = "jdbc:postgresql:students";
		String outUser = "sa";
		String outPasswd = "like,totally,top secret";

		// Load the drivers
		Class<?> inClazz = Class.forName(IN_DRIVER);
		System.out.println("Input driver used: " + inClazz.getName());
		Class<?> outClazz = Class.forName(OUT_DRIVER);
		System.out.println("Output driver used: " + outClazz.getName());

		// Enable logging
		DriverManager.setLogWriter(new PrintWriter((System.err)));

		// Get Connections
		try (Connection inConn = 
				DriverManager.getConnection(inUrl, inUser, inPasswd)) {
			try (Connection outConn = 
					DriverManager.getConnection(outUrl, outUser, outPasswd)) {

				migrate(inConn, outConn);
			}
		} // No need for try { rs.close(); } catch (SQLException e) { }; Closable handles it.
	}

	/**
	 * Since the Connection require so many parameters each, make the
	 * migrate method accept the Connections as parameters.
	 * @param inConn
	 * @param outConn
	 * @throws SQLException
	 */
	public static void migrate(Connection inConn, Connection outConn) throws SQLException {
		// Prepare Statements
		try (PreparedStatement inPreparedStatement = inConn.prepareStatement(
				"Select firstname,lastname from student order by lastname")) {
			try (PreparedStatement outPreparedStatement = outConn.prepareStatement(
					"insert into newStudent (firstname,lastname) values(?,?)")) {

				// Do the input query
				try (ResultSet rs = inPreparedStatement.executeQuery()) {

					// If we get this far, start a transaction on the output db
					outConn.setAutoCommit(false);
					
					// Iterate over results, inserting into new DB
					while (rs.next()) {
						outPreparedStatement.setString(1, rs.getString(1));
						outPreparedStatement.setString(2, rs.getString(2));
						int rowCount = inPreparedStatement.executeUpdate();
						if (rowCount != 1) {
							System.err.printf("ERROR failed to insert %s %s, count %d%n",
									rs.getString(1), rs.getString(2), rowCount);
						}
					}
					
					// Commit output transaction
					outConn.commit();
				}
			}
		}
	}
}

