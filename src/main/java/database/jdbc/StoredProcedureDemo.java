import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** FRAGMENT ONLY showing syntax of prepared statement.
 */
public class CallableExample {
	public static void main(String[] args) throws SQLException {

		Connection conn = ConnectionUtil.getConnection("jabadot");

		CallableStatement cs = conn.prepareCall("{call ListDefunctUsers}");
		ResultSet rs = cs.executeQuery();

		while (rs.next()) {
			System.out.println("Defunct user: " + rs.getString("user"));
		}
	}
}
