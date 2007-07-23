package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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

	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws IOException {

		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		out.println("<h1>Hello from a Servlet</h1>");
		out.println("<p>Server time is now ");
		out.println(new Date().toString());
	}
}
