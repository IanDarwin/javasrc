import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 * ApiSearch.java -- look up an API name in the api database
 * 
 * @author  Ian Darwin, http://www.darwinsys.com/
 * @version $Id$
 */
public class ApiSearch extends HttpServlet
{
	/** Set up the Servlet */
	public void init() {
		// Get a database connection?
	}

	/** Called in response to a GET request (data encoded in the URL) */
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter(); 

		// logic code and main HTML goes here.

		// Forward to "apisearchresults.jsp"
	}

	/** Called in response to a POST request (data unencoded on the socket) */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		doGet(req, resp);
	}
}
