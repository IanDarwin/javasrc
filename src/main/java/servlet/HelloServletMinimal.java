import java.io.*;
import java.util.*;
import javax.servlet.http.*;

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
