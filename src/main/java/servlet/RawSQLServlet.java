import com.darwinsys.util.FileProperties;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import java.util.*;

/** Process a raw SQL query; use ResultSetMetaData to format it.
 */
public class RawSQLServlet extends HttpServlet {
	public final static String PROPS_FILE = "JDBCMeta.properties";

	/** The name of the JDBC Driver */
	protected String DRIVER;

	/** The DB connection object */
	protected Connection conn;

	/** The JDBC statement object */
	protected Statement stmt;

	/** Initialize the servlet. */
	public void init() throws ServletException {
		try {
			// Get a Properties to load from
			FileProperties fp = new FileProperties(PROPS_FILE);

			// Load the database driver
			DRIVER = fp.getProperty("driver");
			Class.forName(DRIVER);

			// Get the connection
			log(getClass() + ": Getting Connection");
			Connection conn = DriverManager.getConnection (
				fp.getProperty("dburl"),
				fp.getProperty("user"),
				fp.getProperty("password"));


			log(getClass() + ": Creating Statement");
			stmt = conn.createStatement();
		} catch (IOException ex) {
			log(getClass() + ": init: could not load props file " + PROPS_FILE);
		} catch (ClassNotFoundException ex) {
			log(getClass() + ": init: Could not load SQL driver " + DRIVER);
		} catch (SQLException ex) {
			log(getClass() + ": init: SQL Error: " + ex);
		}
	}

	/** Do the SQL query */
	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {

		String query = request.getParameter("sql");

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (query == null) {
			out.println("<b>Error: malformed query, contact administrator</b>");
			return;
		}

		// NB MUST also check for admin privs before proceding!
		if (!query.toLowerCase().startsWith("select")) {
			throw new SecurityException("You can only select data");
		}

		try {	// SQL
			out.println("<br>Your query: <b>" + query + "</b>");
			ResultSet rs = stmt.executeQuery(query);

			out.println("<br>Your response:");

			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			out.println("<table border=1>");
			out.print("<tr>");
			for (int i=1; i<=count; i++) {
				out.print("<th>");
				out.print(md.getColumnName(i));
			}
			out.println("</tr>");
			while (rs.next()) {
				out.print("<tr>");
				for (int i=1; i<=count; i++) {
					out.print("<td>");
					out.print(rs.getString(i));
				}
				out.println("</tr>");
			}
			out.println("</table>");
			// rs.close();
		} catch (SQLException ex) {
			out.print("<B>" + getClass() + ": SQL Error:</B>\n" + ex);
			out.print("<pre>");
			ex.printStackTrace(out);
			out.print("</pre>");
		}
	}

	public void destroy() {
		try {
			conn.close();	// All done with that DB connection
		} catch (SQLException ex) {
			log(getClass() + ": destroy: " + ex);
		}
	}
}
