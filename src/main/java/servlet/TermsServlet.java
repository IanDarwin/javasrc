package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/** A Servlet to list the dictionary terms.
 * @author Ian Darwin
 * @version $Id$
 */
public class TermsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<TITLE>Ian Darwin's Computer Terms and Acronyms</TITLE>");
		out.println("<BODY>");
		out.println("<H1>Ian Darwin's Computer Terms and Acronyms</H1>");
		out.println("<TABLE BORDER=2>");
		out.println("<TR><TH>Term<TH>Meaning</TR>");

		// This part of the Servlet generates a list of lines like
		//	<TR> <TD>JSP <TD>Java Server Pages, a neat tool for ...
		TermsAccessor tax = new TermsAccessor("terms.txt");
		Iterator e = tax.iterator();
		while (e.hasNext()) {
			Term t = (Term)e.next();
			out.print("<TR><TD>");
			out.print(t.term);
			out.print("<TD>");
			out.print(t.definition);
			out.println("</TR>");
		}
		out.println("</TABLE>");
		out.println("<HR></HR>");
		out.println("<A HREF=\"servlet/TermsServletPDF\">Printer-friendly (Acrobat PDF) version</A>");
		out.println("<HR></HR>");
		out.println("<A HREF=\"mailto:compquest@darwinsys.com/subject=Question\">Ask about another term</A>");
		out.println("<HR></HR>");
		out.println("<A HREF=\"index.html\">Back to HS</A> <A HREF=\"../\">Back to DarwinSys</A>");
		out.println("<HR></HR>");
		out.println("<H6>Produced by $Id$");
		out.print(" using ");
		out.print(tax.ident);
		out.println("</H6>");
	}
}
