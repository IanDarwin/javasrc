package database.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

/** 
 * Demonstrate how a JDBC Driver registers itself with the Driver Manager
 * Please don't try to use this as a real driver!
 * @author Ian Darwin
 */
public class RegisterDemo implements Driver {

	static {
		try {
			DriverManager.registerDriver(new RegisterDemo());
		} catch (SQLException e) {
			throw new ExceptionInInitializerError("Failed to register driver");
		}
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		// TODO Write a real method here
		return null;
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return url.startsWith("registerdemo" + ":");
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
			throws SQLException {
		// TODO Write a real method here
		return null;
	}

	@Override
	public int getMajorVersion() {
		return 0;
	}

	@Override
	public int getMinorVersion() {
		return 1;
	}

	@Override
	public boolean jdbcCompliant() {
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		 throw new SQLFeatureNotSupportedException();
	}
}
