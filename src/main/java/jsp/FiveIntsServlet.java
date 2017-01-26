package jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/FiveIntsServlet")
public class FiveIntsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
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
		out.println("<a href=\".\">Back to main Page</A>");
	}
}
