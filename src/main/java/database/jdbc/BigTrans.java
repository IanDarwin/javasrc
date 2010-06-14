package database.jdbc;

import java.sql.*;
import java.util.Random;

import static com.darwinsys.sql.ConnectionUtil.getConnection;

/**
 * Measure time used by big transactions.
 * Sample outputs:
 * PostgreSQL inserted 20001 rows in 14429 mSec.
 * SQLite inserted 20001 rows in 26314 mSec.
 */
public class BigTrans {
	static Random r = new Random();

	public static void main(String[] args) throws SQLException {
		String name = args[0];
		Connection conn = getConnection(name);
		PreparedStatement stmt;
		boolean createDrop = false;
		if (createDrop) {
			stmt = conn.prepareStatement("drop table if exists bigx;");
			stmt.execute();
			stmt.close();
			stmt = conn.prepareStatement("create table bigx (" +
				"id serial primary key," +
				"name varchar(24)," +
				"number float(32))");
			stmt.execute();
			stmt.close();
		}
		stmt = conn.prepareStatement("insert into bigx(name,number) values(?,?)");
		long time = System.currentTimeMillis();
		conn.setAutoCommit(false);
		int i;
		for (i = 0; i < 20000; i++) {
			stmt.setString(1, "foo foo le snou");
			stmt.setDouble(2,r.nextDouble());
			int rowCount = stmt.executeUpdate();
			if (rowCount != 1) {
				throw new RuntimeException("Failed to insert row #" + i);
			}
		}
		conn.commit();
		long t2 = System.currentTimeMillis();
		stmt.close();
		System.out.printf("%s inserted %d rows in %d mSec.%n", 
			conn.getMetaData().getDatabaseProductName(), i, t2 - time);
		conn.close();
	}
}
