package database.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.darwinsys.sql.ConnectionUtil;

// BEGIN main
/** A database MetaData query */
public class DatabaseMetaDemo {

	public static void main(String[] args) {
		try {
				// Get the connection
			Connection conn = 
				ConnectionUtil.getConnection(args[0]);

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

		} catch (SQLException ex) {
			System.out.println("Database access failed:");
			System.out.println(ex);
		}
	}

	/** Convert a TransactionIsolation int (defined in java.sql.Connection)
	 * to the corresponding printable string.
	 * 
	 * XXX Remove from here once darwinsys.jar gets committed.
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
// END main
