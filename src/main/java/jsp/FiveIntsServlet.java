package jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FiveIntsServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) 
	throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Your Personal Random Numbers</TITLE>");
		out.println("<H1>Your Personal Random Numbers</H1>");
		out.println("<P>Here are your personal random numbers,");
		out.println("carefully selected by a");
		out.println("<A HREF=\"http://java.sun.com\">Java</A> program.");
		out.println("<OL>");
		Random r = new Random();
		for (int i=0; i<5; i++) {
			out.print("<LI>");
			out.println(r.nextInt());
		}
		out.println("</OL>");
		out.println("<HR></HR>");
		out.println("<A HREF=\"index.html\">Back to main Page</A>");
	}
}
