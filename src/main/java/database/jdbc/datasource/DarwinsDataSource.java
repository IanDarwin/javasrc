// package com.darwinsys.sql;

import java.sql.*;
import javax.sql.*;
import java.io.*;
import java.rmi.*;
import java.util.Properties;

/** A simple DataSource wrapper for a JDBC connection.
 * Constructed using a drivername and dbURL
 * @version $Id$
 */
public class DarwinsDataSource implements DataSource, Remote, Serializable {

	/** The SQL driver name. We cannot load the driver itself as we cannot
	  * ensure that all Drivers will be Serializable.
	  */
	protected String driverName;

	/** The Database URL */
	protected String dbURL;

	/** The LogWriter - not the same as DriverManager.getLogWriter */
	protected PrintWriter logWriter;

	public DarwinsDataSource(String driverName, String dbURL)
	 {
		this.driverName = driverName;
		this.dbURL = dbURL;
	}

	/** Attempt to establish a database connection using defaults. */
	public java.sql.Connection getConnection() throws java.sql.SQLException {
		return getConnection(null, null);
	}

	/** Attempt to establish a database connection using the given
	 * username and password as credentials.
	 */
	public java.sql.Connection getConnection(String userName, String passWord)
	throws java.sql.SQLException {

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.err.println("Can't load driver; check CLASSPATH!");
			return null;
		} catch (Exception ex) {
			System.err.println("Can't load driver: " + ex);
			return null;
		}
		Properties props = new Properties();
		props.setProperty("user", userName);
		props.setProperty("password", passWord);
		Connection c = DriverManager.getConnection(dbURL, props);

		return c;
	}

	/** Set the log writer for this data source. */
	public void setLogWriter(PrintWriter out) {
		logWriter = out;
	}

	/** Get the log writer for this data source. */
	public PrintWriter getLogWriter() {
		return logWriter;
	}

	/** Sets the maximum time in seconds that this data source will wait
	 * while attempting to connect to a database.
	 */
	public void setLoginTimeout(int seconds) {
		DriverManager.setLoginTimeout(seconds);
	}

	/** Gets the maximum time in seconds that this data source can wait
	 * while attempting to connect to the database.
	 */
	public int getLoginTimeout() {
		return DriverManager.getLoginTimeout();
	}
}
