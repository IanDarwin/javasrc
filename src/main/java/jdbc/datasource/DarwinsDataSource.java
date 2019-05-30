package database.jdbc.datasource;

import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.darwinsys.database.DataBaseException;

/** A simple DataSource wrapper for a JDBC connection
 * constructed using a drivername and dbURL
 */
public class DarwinsDataSource implements DataSource, Serializable {

	private static final long serialVersionUID = -4300791498296750769L;

	/** Added in Java SE 6 - NOT YET IMPLEMENTED */
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	/** Added in Java SE 6 - NOT YET IMPLEMENTED */
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}
	
	/** New - not yet implemented */
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		throw new SQLFeatureNotSupportedException();
	}
	/** The SQL driver name. */
	protected String driverName;

	/** The Database URL */
	protected String dbURL;

	/** The LogWriter */
	protected PrintWriter logWriter = new PrintWriter(System.out);

	public DarwinsDataSource(String driverName, String dbURL) {
		this.driverName = driverName;
		this.dbURL = dbURL;
	}

	/** Attempt to establish a database connection using defaults. */
	public Connection getConnection() throws SQLException {
		return getConnection(null, null);
	}

	/** Attempt to establish a database connection using the given
	 * username and password as credentials.
	 */
	public Connection getConnection(String userName, String passWord)
	throws SQLException {

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			throw new DataBaseException("Can't find driver", e);
		}

		Connection c = DriverManager.getConnection(dbURL, userName, passWord);

		return c;
	}

	/** Set the log writer for this data source. */
	public void setLogWriter(PrintWriter out) {
		DriverManager.setLogWriter(out);
	}

	/** Get the log writer for this data source. */
	public PrintWriter getLogWriter() {
		return DriverManager.getLogWriter();
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
