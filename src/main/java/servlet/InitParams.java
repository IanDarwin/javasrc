import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Show both ServletContext.getInitParameter and Servlet.getInitParameter!
 * The following configuration info is for Tomcat and J2EE XML.
 *
 * N.B. The web.xml file MUST be that for the "web application", NOT
 * the master one for Tomcat which lives in /etc/tomcat (or whereever).
 *
 * The ServletContext init parameters are set as context-param in web.xml
 * as <context-param><param-name></>param-value></>
 * The Servlet's own init parameters are set in web.xml as
 *	<servlet>...<init-param><param-name>...</><param-value>...</></>
 */
public class InitParams extends HttpServlet {

	protected String CONTEXT_PARAM = "nothing";
	protected String CURR_DIR = "nowhere";
	protected String SERVLET_PARAM = "nuttin'";

	public void init() throws ServletException {

		ServletConfig config = getServletConfig();
		ServletContext ctx = config.getServletContext();

		CONTEXT_PARAM = ctx.getInitParameter("myParm");
		CURR_DIR = getServletContext().getRealPath("/");
	}

	public void service(HttpServletRequest request,
		HttpServletResponse response) 
		throws ServletException, IOException {

		SERVLET_PARAM = getInitParameter("myParm");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<i>Hello</i>");
		out.println("<p>CONTEXT_PARAM = " + CONTEXT_PARAM);
		out.println("<p>SERVLET_PARAM = " + SERVLET_PARAM);
		out.println("<p>CURR_DIR = " + CURR_DIR);
		out.println("<h6>$Id$</h6>");
	}

}
