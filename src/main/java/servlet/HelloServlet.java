import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Simple Hello World Servlet
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
		HttpServletResponse response) throws IOException {

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
