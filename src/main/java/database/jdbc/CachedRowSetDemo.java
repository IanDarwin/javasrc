package JDBC;

import javax.sql.rowset.CachedRowSet;

/** Demonstrate simple use of the CachedRowSet.
 * The RowSet family of interfaces is in JDK1.5, but the Implementation
 * classes are (as of Beta 1) still in the unsupported "com.sun" package.
 */
public class CachedRowSetDemo {
	public static final String ROWSET_IMPL_CLASS = "com.sun.rowset.CachedRowSetImpl";
	public static void main(String[] args) throws Exception {
		CachedRowSet rs;

		// Create the class with class.forName to avoid importing
		// from the unsupported com.sun packages.
		Class c = Class.forName(ROWSET_IMPL_CLASS);
		rs = (CachedRowSet)c.newInstance();

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
				rs.acceptChanges();
			}
		}
	
		// If we're all done...
		rs.close();
	}
}
