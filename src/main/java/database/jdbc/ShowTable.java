/**
 * List the Column names in an SQL database table.
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.darwinsys.sql.ConnectionUtil;

public class ShowTable {
	public static void main(String[] args) throws SQLException {
		String config = args[0];
		String table = args[1];
		Connection c = ConnectionUtil.getConnection(config);
		DatabaseMetaData md = c.getMetaData();
		ResultSet rs = md.getColumns(null, null, table, "%");
		while (rs.next()) {
			System.out.println(rs.getString(4));
		}
	}
}
