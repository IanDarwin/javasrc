import java.util.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;

/** See if a DataSource exists and is usable.
 * @version $Id$
 */
public class TestDataSource {

	public static void main(String[] argv) 
		throws NamingException, SQLException {

		Context ctx = new InitialContext();

		String dsn = argv[0];
		System.out.println("Looking up " + dsn);
		Object o = ctx.lookup(dsn);
		javax.sql.DataSource d = (javax.sql.DataSource)o;

		System.out.println("Getting connection ");
		Connection con = d.getConnection();
		System.out.println("Got it!");
	}
}
