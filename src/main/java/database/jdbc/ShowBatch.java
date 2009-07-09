package database.jdbc;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.darwinsys.sql.ConnectionUtil;

/**
 * Show use of Batching
 */
public class ShowBatch {
	public static void main(String[] args) {
		try {
			String config = args[0];
			String table = args[1];
			Connection c = ConnectionUtil.getConnection(config);
			c.setAutoCommit(false);

			Statement s = c.createStatement();
			// NEVER USE THIS IN REAL LIFE - use a PreparedStatement for ANY user input
			s.addBatch("update " + table + " set stockcount = 0 where ...");
			s.addBatch("insert into orders ...");			
			int[] updateCounts = s.executeBatch();
			
			for (int i = 0; i < updateCounts.length; i++) {
				System.out.println(i + "-->" + updateCounts[i]);
			}
		} catch (BatchUpdateException e) {
			System.err.println(e);
		} catch (SQLException e) {
			System.err.println(e);
		}
	}
}
