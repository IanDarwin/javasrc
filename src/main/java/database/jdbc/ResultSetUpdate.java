package JDBC;

import java.sql.*;

public class ResultSetUpdate {
    public static void main(String args[]) {

        String url;
        url = "jdbc:odbc:UserDB";

		String user, pass;
		user = "ian";
		pass = "stjklsq";

        Connection con;
        Statement stmt;
		ResultSet rs;

        try {
            con = DriverManager.getConnection(url, user, pass);
			stmt = con.createStatement(
			ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM Users where nick=\"ian\"");

			// Get the resultset ready, update the passwd field, commit
			rs.first();
			rs.updateString("password", "unguessable");
			rs.updateRow();

			rs.close();
			stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
	}
}
