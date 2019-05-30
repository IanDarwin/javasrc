package database.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.darwinsys.sql.ConnectionUtil;

/** Display JDBC ConnectionMetaData.
 */
public class ConnectionMetaDemo {

	public static void main(String[] av) throws Throwable {
		Connection conn = null;
	    try {
			conn = ConnectionUtil.getConnection(av[0]);
			
			// Do something with the connection here...
			final DatabaseMetaData metaData = conn.getMetaData();
			System.out.printf("Database name %s%n",
					metaData.getDatabaseProductName());
			System.out.printf("Database version %d.%d%n",
					metaData.getDatabaseMajorVersion(),
					metaData.getDatabaseMinorVersion());

	    } catch (SQLException e) {
			System.out.println("Database problem " + e);
			e.printStackTrace();
	    } finally {
	    	if (conn != null)
	    		conn.close();	// Done with that DB connection
	    }
	}
}
