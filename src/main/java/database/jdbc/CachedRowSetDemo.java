package database.jdbc;

import javax.sql.RowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/** Demonstrate simple use of the CachedRowSet.
 */
// BEGIN main
public class CachedRowSetDemo {
	public static void main(String[] args) throws Exception {
		RowSet rs;

		RowSetFactory rsFactory = RowSetProvider.newFactory();
	    rs = rsFactory.createCachedRowSet();

		rs.setUrl("jdbc:postgresql:tmclub");
		rs.setUsername("ian");
		rs.setPassword("secret");

		rs.setCommand("select * from members where name like ?");
		rs.setString(1, "I%");

		// This will cause the RowSet to connect, fetch its data, and
		// disconnect
		rs.execute();

		// Some time later, the client tries to do something.

		// Suppose we want to update data:
		while (rs.next()) {
			if (rs.getInt("id") == 42) {
				rs.setString(1, "Marvin");
				rs.updateRow();	// Normal JDBC

				// This additional call tells the CachedRowSet to connect
				// to its database and send the updated data back.
				rs.updateRow();
			}
		}
	
		// If we're all done...
		rs.close();
	}
}
// END main
