package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A very minimal Servlet, just to show syntax, and 
 * thus a very bad example. Do the world a favor:
 * Use a framework like Struts, SpringMVC or Seam instead
 * of writing low-level cruft like this.
 */
public class HelloServletMinimal extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		out.printf("<html><head><title>Hello from a Servlet</title></head>"+
			"<body><h1>Hello World from a Java Servlet at %s</h1>"+
			"</body></html>", new java.util.Date());
	}
}
