import com.darwinsys.util.FileProperties;

import java.awt.*;
import java.sql.*;

/** A database MetaData query
 * @version $Id$
 */
public class JDBCMeta {

	public static void main(String[] av) {
		int i;
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

			// Try to list all possible tables
			System.out.println("Listing Tables");
			ResultSet res = meta.getTables(null, "", "%", null);
			i = 0;
			while (res.next()) {
				System.out.print("Row " + ++i + " ");
				System.out.println(res.getString(3));
			}
			res.close();

			// Now get a ResultSetMetaData for "select * from userdb" as
			// a way of interrogating column names.
			ResultSet rs = 
				conn.createStatement().executeQuery("select * from userdb");
			ResultSetMetaData rsMeta = rs.getMetaData();
			System.out.println("ResultSetMetaData is " + rsMeta);
			int n = rsMeta.getColumnCount();
			System.out.println("ResultSet has " + n + " columns.");
			for (int col=1; col<=n; col++)
				System.out.println("Column " + col + " is " +
					rsMeta.getColumnName(col));
			rs.close();

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
}
