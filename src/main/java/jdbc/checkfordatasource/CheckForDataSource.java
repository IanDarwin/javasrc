package database.jdbc.checkfordatasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/** See if a DataSource exists and is usable.
 */
public class CheckForDataSource {

	public static void main(String[] argv)
		throws NamingException, SQLException {

		// Default constructor, looks for file
		// "jndi.properties" ON CLASSPATH
		// to configure JNDI factory, URL, etc.
		Context ctx = new InitialContext();

		String dsn = argv[0];
		System.out.println("Looking up " + dsn);
		Object o = ctx.lookup(dsn);
		DataSource d = (DataSource)o;
		if (d == null) {
			throw new IllegalArgumentException("Datasource " + dsn + " not found");
		}

		System.out.println("Getting connection ");
		Connection con = d.getConnection();
		System.out.println("Got it!");
		con.close();
	}
}
