package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * OrderServlet implements order processing for Acme Widgets.
 * This dummy version just generates a thank-you notice.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/, November, 1997
 */
public class OrderServlet extends HttpServlet {

	/** doPost() processes one POST request.
	 * Assuming weve been installed correctly, this can only 
	 * come from the order page, so we blindly respond, 
	 * without much validation of the order :-)
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		String cardType = req.getParameter("cardType");
		String cardNumber = req.getParameter("cardNumber");
		String expMonth = req.getParameter("expMonth");
		String expYear = req.getParameter("expYear");

		// Simple validation.
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
			return;
		} 

		// Code here to phone the bank and validate the card.

		// Now make a nice thankyou to the user
		make_reply(true, out, "Acme Widgets: Thank you for your order",
				"Thank you for your credit card order",
				"We will bill it to your " + cardType + " credit card" +
				" which expires " + expMonth + "/" + expYear +
				"<P>Wile E. Coyote will process your order " +
				"as soon as he returns from his latest trip " +
				"to the bottom of the Grand Canyon.</P>");
		return;
	}

	/** Common code to generate a reply */
	void make_reply(boolean cardIsApproved, PrintWriter os, 
		String title, String h1, String txt) {

		// There would be code here to log the approve/deny status

		// Now make up the reply to the user.
		os.print("<HTML><HEAD>");
		os.print("<TITLE>");
		os.print(title);
		os.print("</TITLE>");
		os.print("<BODY><H1>");
		os.print(h1);
		os.print("</H1>");
		os.print(txt);
		os.print("</BODY></HTML>");
	}

	/** Some Servlet engines use this to report on the servlet */
	public String getServletInfo() {
		return "Acme Widgets Credit Card Validation";
	}
}
