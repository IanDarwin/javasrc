package servlet;

import java.io.*;
import java.util.Random;
import javax.servlet.http.*;

public class IntsServlet extends HttpServlet {
	protected final int DEFAULT_NUMBER = 5;

	/** Called when the form is filled in by the user. */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
	
		// The usual HTML setup stuff.
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<BODY BGCOLOR=\"white\">");

		// HTML for this page
		out.println("<TITLE>Your Personal Random Numbers</TITLE>");
		out.println("<H1>Your Personal Random Numbers</H1>");
		out.println("<P>Here are your personal random numbers,");
		out.println("carefully selected by a");
		out.println("<A HREF=\"http://java.sun.com\">Java</A> program.");
		out.println("<OL>");

		// Figure out how many numbers to print.
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

		// Now actually generate some random numbers.
		Random r = new Random();
		for (int i=0; i<n; i++) {
			out.print("<LI>");
			out.println(r.nextInt(49));	// for Lotto 6/49
		}
		out.println("</OL>");

		// Print a break and a back link.
		out.println("<HR></HR>");
		out.println("<A HREF=\"index.html\">Back to main Page</A>");
		out.println("</HTML>");
	}
}
