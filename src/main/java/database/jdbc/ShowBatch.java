/**
 * Show use of Batching
 */

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.darwinsys.sql.ConnectionUtil;

public class ShowBatch {
	public static void main(String[] args) {
		try {
			String config = args[0];
			String table = args[1];
			Connection c = ConnectionUtil.getConnection(config);
			c.setAutoCommit(false);
			Statement s = c.createStatement();
			s.addBatch("update recordings set stockcount = 0 where ...");
			s.addBatch("insert into orders ...");
			int[] updateCounts = s.executeBatch();
		} catch (BatchUpdateException e) {
			// ...
		} catch (SQLException e) {
			// ...
		}
	}
}
