import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Simple Hello World Servlet
 * @author Ian Darwin
 * @version $Id$
 */
public class HelloServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<H1>Hello from a Servlet</H1>");
		out.println("<P>This servlet ran at ");
		out.println(new Date().toString());
		out.println("<P>Courtesy of $Id$");
	}
}
