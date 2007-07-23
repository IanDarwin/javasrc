package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Simple Hello World Servlet, just to show syntax, and
 * thus a very bad example. Do the world a favor:
 * Use a framework like Struts, SpringMVC or Seam instead
 * of writing low-level cruft like this.
 * @author Ian Darwin
 * @version $Id$
 */
public class HelloServlet extends HttpServlet{

	/** Called when the user clicks on a link to this servlet
	 * @parameter request Encapsulates the details about the input.
	 * @parameter response Encapsulates what you need to get a reply to the
	 *		user's browser.
	 */
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException, ServletException {

		// Get a writer to generate the reply to user's browser
		PrintWriter out = response.getWriter();

		// Generate the HTTP header to say the response is in HTML
		response.setContentType("text/html");

		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"");
		out.println("\t\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"");
		out.println(">");
		out.println();

		out.println("<html><head><title>Hello from a Servlet</title></head>");
		out.println("<body>");
		out.println("<h1>Hello from a Servlet</h1>");
		out.println("<p>This servlet ran at ");
		out.println(new Date().toString());
		out.println("</p>");
		out.println("<p>Courtesy of " +
			"$Id$</p>");
		out.println("</body></html>");
	}
}
