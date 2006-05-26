package JDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.darwinsys.sql.SQLUtils;

/** Process a raw SQL query; use ResultSetMetaData to format it.
 */
public class RawSQLServlet extends HttpServlet {

	private static final long serialVersionUID = 4523110482208621396L;

	/** The application-wide servlet context */
	protected ServletContext application;

	/** The DB connection object */
	protected Connection conn;

	/** The JDBC statement object */
	protected Statement stmt;

	/** Initialize the servlet. */
	public void init() throws ServletException {
		application = getServletConfig().getServletContext();
		String driver = null;
		try {

			driver = application.getInitParameter("db.driver");
			Class.forName(driver);

			// Get the connection
			log(getClass() + ": Getting Connection");
			conn = DriverManager.getConnection (
				application.getInitParameter("db.url"),
				application.getInitParameter("db.user"),
				application.getInitParameter("db.password"));


			log(getClass() + ": Creating Statement");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException ex) {
			log(getClass() + ": init: Could not load SQL driver " + driver);
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

		try {	// SQL
			out.println("<p>Your query: <b>" + query + "</b></p>");
			stmt.execute(query);
			ResultSet rs = stmt.getResultSet();
			if (rs == null) {
				out.println("<b>Update Count = " + stmt.getUpdateCount());
			} else {
				SQLUtils.resultSetToHTML(rs, out);
			}
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
