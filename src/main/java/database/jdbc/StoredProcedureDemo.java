package database.jdbc;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.darwinsys.sql.ConnectionUtil;

/** FRAGMENT ONLY showing syntax of prepared statement.
 */
public class StoredProcedureDemo {
	public static void main(String[] args) throws SQLException {

		Connection conn = ConnectionUtil.getConnection("ecom");

		CallableStatement cs = conn.prepareCall("{call findOutOfStock()}");
		ResultSet rs = cs.executeQuery();

		while (rs.next()) {
			System.out.println("Item: " + rs.getString("sku"));
		}
	}
}
