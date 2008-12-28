package jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/** Locate a JDBC DataSource using the app server's registry.
 * @version $Id$
 */
public class GetDataSource {

        public static void main(String[] av) throws Exception {

				// Exact context chosen based on "jndi.properties"
                Context ctx = new InitialContext();

                Object o = ctx.lookup("jdbc/HRDatabase");

                DataSource ds = (DataSource)o;
                System.out.println("Got datasource: " + ds);

				Connection c = ds.getConnection();

				DatabaseMetaData meta = c.getDatabaseMetaData();
				System.out.println("Database is " + meta.getDatabaseProduct());
        }
}

