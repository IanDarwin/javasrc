import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 * BrokenServlet -- so where is it broken, anyway?
 * 
 * @version $Id$
 */
public class BrokenServlet extends HttpServlet
{
	protected PrintWriter out;

	/** Called in response to a GET request (data encoded in the URL) */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html");

		out = response.getWriter(); 

		// Simulate a database lookup
		int result = 42;

		print(result);

	}

	protected void print(int result)
	{
		out.println("<!DOCTYPE html PUBLIC " +
			"\"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
			"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"\n" +
			">");
		out.println("<html>");
		out.println("<head><title>Servlet Output</title></head>");
		out.println("<body>");

		out.println("<h1>Results</h1>");
		out.println("<p>The result is " + result + "</p>");

		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
