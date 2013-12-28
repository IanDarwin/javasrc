package database.jdbc;


/** 
 * Load some drivers.
 */
// BEGIN main
public class LoadDriver {
	public static void main(String[] av) {
		try {
			// Try to load the jdbc-odbc bridge driver
			// Should be present on Sun JDK implementations.
			Class<?> c = Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			System.out.println("Loaded " + c.getName());
			// Try to load an Oracle driver.
			Class<?> d = Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Loaded " + d.getName());
		} catch (ClassNotFoundException ex) {
			System.err.println(ex);
		}
	}
}
// END main
