package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Simple Cookie-based Page Color Display servlet demo.
 * @author Ian Darwin
 * @version $Id$
 */
public class CookieServlet extends HttpServlet {
	/** The preferences cookie name */
	protected final static String PREFS_BGCOLOR = "prefs.bgcolor";
	/** Where to go if we have not yet been customized. */
	protected final static String CUSTOMIZER = "/ColorCustomize.html";
	/** The user's chosen color, if any */
	protected String faveColor = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		// Go through all the cookies we have, looking for a faveColor.
		Cookie[] mySiteCookies = request.getCookies();
		for (int i=0; i<mySiteCookies.length; i++) {
			Cookie c = mySiteCookies[i];
			if (c.getName().equals(PREFS_BGCOLOR)) {
				faveColor = c.getValue();
				break;
			}
		}

		// if we did not find a faveColor in a cookie,
		// punt to customization servlet to bake one up for us.
		if (faveColor == null) {
			ServletContext sc = getServletContext();

			// Requires Servlet API 2.1 or later!
			// RequestDispatcher rd = 
			//	sc.getRequestDispatcher(CUSTOMIZER");
			//rd.forward(request, response);

			// Do it the old way
			response.sendRedirect(CUSTOMIZER);
		}

		// OK, we have a color, so we can do the page.
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		out.println("<html><title>A Custom-Colored Page</title>");
		out.print("<body bgcolor=\"");
		out.print(faveColor);
		out.println("\">");
		out.println("<P>Welcome! We hope you like your colored page!</P>");
		out.println("</body></html>");
		out.flush();
	}
}
