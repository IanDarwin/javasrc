import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/*
 * ForwardException - show forwarding an exception to an error page.
 * 
 * @author  Ian Darwin
 * @version $Id$
 */
public class Servlet extends HttpServlet
{
	/** Called in response to a GET request (data encoded in the URL) */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		ServletContext application = getServletContext();

		// BOILERPLATE beginning 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 

		try {
			// to do: logic code and main HTML goes here.

			// simulate an error condition
			throw new RuntimeException("Test exception");

			out.println("<!DOCTYPE html PUBLIC " +
				"\"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
				"\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"\n" +
				">");
			out.println("<html>");
			out.println("<head><title>Servlet Output</title></head>");
			out.println("<body>");

			// BOILERPLATE ending
			out.println("</body>");
			out.println("</html>");
			out.close();

		catch (Exception ex) {

			// dispatch to JSP to display the error.
			RequestDispatcher rd = application.getRequestDispatcher("/oops.jsp");
			request.setAttribute("javax.servlet.jsp.jspException", exc);
			rd.forward(request, response);
		}
	}
}

