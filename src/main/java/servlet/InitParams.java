import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** Show both ServletContext.getInitParameter and Servlet.getInitParameter!
 * The following configuration info is for Tomcat and J2EE XML:
 * The ServletContext init parameters are set as context-param in web.xml
 * as <contet-param><param-name></>param-value></>
 * The Servlet's own init parameters are set in web.xml as
 *	<servlet>...<init-param><param-name>...</><param-value>...</></>
 */
public class InitParams extends HttpServlet {

	String CONTEXT_PARM;
	String CURR_DIR;

	public void init() throws ServletException {

		ServletConfig cfg = getServletConfig();
		ServletContext ctx = getServletContext();

		CONTEXT_PARM = ctx.getInitParameter("myContextParm");
		CURR_DIR = getServletContext().getRealPath("/");
	}

	public void service(HttpServletRequest request,
		HttpServletResponse response) 
		throws ServletException, IOException {

		String SERVLET_PARM = getInitParameter("myServletParm");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<b>Hello</b>");
		out.println("<p>CONTEXT_PARM = " + CONTEXT_PARM);
		out.println("<p>SERVLET_PARM = " + SERVLET_PARM);
		out.println("<p>CURR_DIR = " + CURR_DIR);
	}

}
