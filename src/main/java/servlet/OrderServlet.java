import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * OrderServlet implements order processing for Acme Widgets.
 * This dummy version just generates a thank-you notice.
 *
 * @author	Ian Darwin, ian@darwinsys.com, November, 1997
 */
public class OrderServlet extends HttpServlet {

	/** doPost() processes one POST request. It is the one method
	 * that must be provided (its abstract in GenericServlet).
	 * Assuming weve been installed correctly, this can only 
	 * come from the order page, so we blindly respond, 
	 * without much validation of the order :-)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		resp.setContentType("text/html");
		ServletOutputStream out = resp.getOutputStream();

		// out.println("<PRE>");
		// Enumeration parms = req.getParameterNames();
		// while (parms.hasMoreElements())
			// out.println("Parm " + parms.nextElement());
		// out.println("</PRE>");

		String cardType = req.getParameter("cardType");
		String cardNumber = req.getParameter("cardNumber");
		String expMonth = req.getParameter("expMonth");
		String expYear = req.getParameter("expYear");
		if (cardType == null || cardNumber == null ||
			cardNumber.length() == 0 ||
			expMonth == null || expYear == null) {
			make_reply(false, out, "Acme Widgets: Thanks for trying...", 
				"Error!",
				"<P>We need a credit card type <B>and</B> a " +
				"valid credit card number, as well as the expiration "+
				"month and year. Please click your " +
				"browsers <I>Back</I> button and try again.\n" +
				"<P>Again, thank you for your order.");
		} else {
			make_reply(true, out, "Acme Widgets: Thank you for your order",
				"Thank you for your credit card order",
				"We will bill it to your " + cardType + " credit card" +
				" which expires " + expMonth + "/" + expYear +
				"<P>Wile E. Coyote will process your order " +
				"as soon as he returns from his latest trip " +
				"to the bottom of the Grand Canyon.</P>");
		}
		return;
	}

	/** The DocumentRoot for the server. 
	 * XXX TODO: Must get this from the Web Server API somehow.
	 */
	final static DOCUMENT_ROOT = "c:/javasrc/";

	void make_reply(boolean cardIsApproved, ServletOutputStream os, 
		String title, String h1, String txt) {
		String reply = "<HTML><HEAD>" +
			"<TITLE>" + title + "</TITLE>" +
			"<BODY><H1>" + h1 + "</H1>" +
			txt +
			"</BODY></HTML>";
		try {
			PrintWriter ps = new PrintWriter(new FileWriter(
				DOCUMENT_ROOT + 
				"/tmp_replies/" + "xxx.html"));
			ps.println(reply);
			ps.close();
			os.println((cardIsApproved?"A":"D") + " " +
				"/tmp_replies/" + "xxx.html");
			os.close();
		} catch (IOException e) {
			System.err.println("Error " + e);
		}
	}

	/** For now, handle Get same as Post. DO NOT DO THIS FOR REAL,
	 * since GET is not safe to use for $$ transactions (See Jeeves
	 * Developer Doc).
	 * Just a hack while we work on the Applet.
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
			doPost(req, resp);
	}

	public String getServletInfo() {
		return "Acme Widgets Credit Card Validation";
	}
}
