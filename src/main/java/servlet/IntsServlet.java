import java.io.*;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.http.*;

public class IntsServlet extends HttpServlet {
	protected final int DEFAULT_NUMBER = 5;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<BODY BGCOLOR=\"white\">");
		out.println("<TITLE>Your Personal Random Numbers</TITLE>");

		int n = DEFAULT_NUMBER;
		String num=req.getParameter("howmany");
		if (num != null && num.length() != 0) {
			try {
				n = Integer.parseInt(num);
			} catch (NumberFormatException e) {
				out.println("<P>I didn't think much of ");
				out.println(num);
				out.println(" as a number.</P>");
			}
		}
		out.println("<H1>Your Personal Random Numbers</H1>");
		out.println("<P>Here are your personal random numbers,");
		out.println("carefully selected by a");
		out.println("<A HREF=\"http://java.sun.com\">Java</A> program.");
		out.println("<OL>");
		Random r = new Random();
		for (int i=0; i<n; i++) {
			out.print("<LI>");
			out.println(r.nextInt());
		}
		out.println("</OL>");
		out.println("<HR></HR>");
		out.println("<A HREF=\"index.html\">Back to main Page</A>");
		out.println("</HTML>");
	}
}
