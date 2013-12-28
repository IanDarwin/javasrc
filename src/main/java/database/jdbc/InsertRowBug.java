package database.jdbc;

import java.sql.*;

public class InsertRowBug {
    public static void main(String args[]) {

        String url;
        // url = "jdbc:odbc:SQL Anywhere 5.0 Sample";
        // url = "jdbc:oracle:thin:@server:1521:Sample";
        url = "jdbc:odbc:Sample";

        String driver;
        //driver = "oracle.jdbc.driver.OracleDriver";
        driver = "sun.jdbc.odbc.JdbcOdbcDriver";

	String user, pass;
	user = "student";
	pass = "student";

        Connection con;
        Statement stmt;
	ResultSet uprs;

        try {
        	 Class.forName(driver);

        } catch(java.lang.ClassNotFoundException e) {
            System.err.println(e);
		return;
        }

        try {
            con = DriverManager.getConnection(url, user, pass);
			stmt = con.createStatement(
			ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			uprs = stmt.executeQuery("SELECT * FROM Music_Recordings");

			// Check the column count 
			ResultSetMetaData md = uprs.getMetaData();
			System.out.println("Resultset has " +
				md.getColumnCount() + " cols.");

			int rowNum = uprs.getRow();
			System.out.println("row1 " + rowNum);
			uprs.absolute(1);
			rowNum = uprs.getRow();
			System.out.println("row2 " + rowNum);
			uprs.next();
			uprs.moveToInsertRow();
			uprs.updateInt(1, 150);
			uprs.updateString(2, "Madonna");
			uprs.updateString(3, "Dummy");
			uprs.updateString(4, "Jazz");
			uprs.updateString(5, "Image");
			uprs.updateInt(6, 5);
			uprs.updateDouble(7, 5);
			uprs.updateInt(8, 15);
			uprs.insertRow();
			uprs.close();
			stmt.close();
            con.close();
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
	}
}
