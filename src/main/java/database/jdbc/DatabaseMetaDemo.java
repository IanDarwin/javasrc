package JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.darwinsys.util.FileProperties;

/** A database MetaData query
 * @version $Id$
 */
public class DatabaseMetaDemo {

	public static void main(String[] av) {
		try {
			FileProperties fp = new FileProperties("JDBCMeta.properties");

			// Load the driver
			Class.forName(fp.getProperty("driver"));

			// Get the connection
			Connection conn = DriverManager.getConnection (
				fp.getProperty("dburl"),
				fp.getProperty("user"),
				fp.getProperty("password"));

			// Get a Database MetaData as a way of interrogating 
			// the names of the tables in this database.
			DatabaseMetaData meta = conn.getMetaData();

			System.out.println("We are using " + meta.getDatabaseProductName());
			System.out.println("Version is " + meta.getDatabaseProductVersion() );
        
			int txisolation = meta.getDefaultTransactionIsolation();
            System.out.println("Database default transaction isolation is " + 
				txisolation + " (" +
				transactionIsolationToString(txisolation) + ").");

			conn.close();

			System.out.println("All done!");

		} catch (java.io.IOException e) {
			System.out.println("Can't load PROPERTIES " + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load driver " + e);
		} catch (SQLException ex) {
			System.out.println("Database access failed:");
			System.out.println(ex);
		}
	}

	/** Convert a TransactionIsolation int (defined in java.sql.Connection)
	 * to the corresponding printable string.
	 */
	public static String transactionIsolationToString(int txisolation) {
		switch(txisolation) {
			case Connection.TRANSACTION_NONE: 
				// transactions not supported.
				return "TRANSACTION_NONE";
			case Connection.TRANSACTION_READ_UNCOMMITTED: 
				// All three phenomena can occur
				return "TRANSACTION_NONE";
			case Connection.TRANSACTION_READ_COMMITTED: 
			// Dirty reads are prevented; non-repeatable reads and 
			// phantom reads can occur.
				return "TRANSACTION_READ_COMMITTED";
			case Connection.TRANSACTION_REPEATABLE_READ: 
				// Dirty reads and non-repeatable reads are prevented;
				// phantom reads can occur.
				return "TRANSACTION_REPEATABLE_READ";
			case Connection.TRANSACTION_SERIALIZABLE:
				// All three phenomena prvented; slowest!
				return "TRANSACTION_SERIALIZABLE";
			default:
				throw new IllegalArgumentException(
					txisolation + " not a valid TX_ISOLATION");
		}
	}
}
