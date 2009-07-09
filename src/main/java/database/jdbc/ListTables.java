package database.jdbc;

/**
 * List the Tables in an SQL database
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.darwinsys.sql.ConnectionUtil;

public class ListTables {
	public static void main(String[] args) throws SQLException {
		String config = args[0];
		Connection c = ConnectionUtil.getConnection(config);
		DatabaseMetaData md = c.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		while (rs.next()) {
			System.out.println(rs.getString(3));
		}
	}
}
