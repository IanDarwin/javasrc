import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.pdflib.*;

/** Output the dictionary in fancy(?) PDF.
 * This version uses "PDFlib", from PDFLib.GmbH (www.pdflib.com).
 * @author Ian Darwin
 * @version $Id$
 */
public class TermsServletPDF extends HttpServlet {
	/** A printwriter for getting the response. */
	PrintWriter out;

	/** Handle the get request. */
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException {

		try {

			out = new PrintWriter(response.getOutputStream());

			int font;
			pdflib p = new pdflib();

			if (p.open_file("") == -1) {
				warning(response, "Couldn't create in-memory PDF file", null);
				return;
			}

			p.set_info("Title", "Dictionary Project");
			p.set_info("Author", "Ian F. Darwin, ian@darwinsys.com");
			p.set_info("Creator", "www.darwinsys.com/dictionary");

			p.begin_page(595, 842);

			font = p.findfont("Helvetica", "host", 0);

			p.setfont(font, 14);

			p.set_text_pos(50, 700);
			p.show("Name");
			p.set_text_pos(70, 666);
			p.show("Value of definition");
			p.end_page();

			p.close();

			byte[] data = p.get_buffer();

			response.setContentType("application/pdf");
			response.getOutputStream().write(data);
		} catch (IOException e) {
			warning(response, "pdflib IO error:", e);
			return;
		} catch (Exception e) {
			warning(response, "pdflib error:", e);
			return;
		}
    }

	/** Generic error handler. Must call before any use of "out" */
	protected void warning(HttpServletResponse response, String error, Exception e) {
		response.setContentType("text/html");
		try {
			PrintWriter out = response.getWriter();
		} catch (IOException exc) {
			// egad - we can't tell the user a thing!
			System.err.println("EGAD! IO error " + exc + " trying to tell user about " + error + " " + e);
			return;
		}
	    out.println("<H1>Error</H1>");
		out.print("<P>Oh dear. You seem to have run across an error in ");
		out.print("our dictionary formatter. We apologize for the inconvenience");
		out.print("<P>Error message is ");
		out.println(error);

		if (e != null) {
			out.print("<P>Exception is: ");
			out.println(e.toString());
			out.print("Traceback is: ");
			out.print("<PRE>");
			e.printStackTrace(out);
			out.print("</PRE>");
		}
		System.out.print("DictionaryServletPDF: ");
		System.out.println(error);
		if (e != null) {
			System.out.println(e.toString());
		}
	}
}
