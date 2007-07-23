package servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;

/* This servlet responds to the CodeRed worm by sending an email
 * to "administrator@" (and "root@") the infected site.
 * Keeps a hashtable in Application scope to only nag
 * each infested site once.
 * You will typically see "probe" entries like this in your
 * Apache logfile:
 * <pre>
 * 211.218.252.9 - - [02/Aug/2001:06:01:38 -0400] "GET /default.ida?NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN%u9090%u6858%ucbd3%u7801%u9090%u6858%ucbd3%u7801%u9090%u6858%ucbd3%u7801%u9090%u9090%u8190%u00c3%u0003%u8b00%u531b%u53ff%u0078%u0000%u00=a  HTTP/1.0" 400 326 "-" "-"
 * 193.255.201.85 - - [02/Aug/2001:09:27:59 -0400] "GET /default.ida?NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN%u9090%u6858%ucbd3%u7801%u9090%u6858%ucbd3%u7801%u9090%u6858%ucbd3%u7801%u9090%u9090%u8190%u00c3%u0003%u8b00%u531b%u53ff%u0078%u0000%u00=a  HTTP/1.0" 400 326 "-" "-"
 * </pre>
 * These are CodeRed probes: attempting to take over your IIS server
 * by overrunning a buffer in the ".ida" handler and installing the
 * executable program listed at the end of the overflowed buffer.
 */
public class CodeRed extends HttpServlet {

	// Change this to the (FQDN?) name of your mail host
	protected final String SMTPSERVER = "localhost";
	/** This, too, should be updated to something more FQ */
	protected final String MYADDR = "root@your.domain";
	/** The JavaMail session object XXX Is it threadsafe? */
	protected Session session;

	public void init() {

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTPSERVER);

		// Create the Mail Session object
		session = Session.getDefaultInstance(props, null);
		session.setDebug(true);		// Verbose!
	}

	public void service(HttpServletRequest request,
		HttpServletResponse response)
	throws IOException, ServletException {

		ServletContext ctx = getServletContext();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Default.ida</title></head>");
		out.println("<body text=\"white\" bgcolor=\"black\">");

		HashMap list =
			(HashMap)ctx.getAttribute("codered.notified");

		if (list == null) {
			list = new HashMap();
			ctx.setAttribute("codered.notified", list);
		}

		String hostIP = request.getRemoteAddr();

		if (list.get(hostIP) != null) {
			// log("Already tried to notify " + hostIP);
			return;
		}

		String message_body =
		"Your site, whose IP address is " +  hostIP + ", " +
		"has requested a \".ida\" file from a web site " +
		"that doesn't run the bug-infested Microsoft IIS server. " +
		"This typically indicates that your site " +
		"has been compromised by the \"CodeRed\" virus. " +
		"You should (1) immediately reboot the offending machine and " +
		"(2) To protect your system from re-infection: Install Microsoft's " +
		"patch for the Code Red vulnerability problem: " +
		"Windows NT version 4.0: " +
		"http://www.microsoft.com/Downloads/Release.asp?ReleaseID=30833 " +
		"Windows 2000 Professional, Server and Advanced Server: " +
		"http://www.microsoft.com/Downloads/Release.asp?ReleaseID=30800 " +
		"(3) update your virus protection software and do a full scan.";

		out.println(message_body);

		try {

			/** The JavaMail message object */
			Message mesg;

			// create a message
			mesg = new MimeMessage(session);

			// From Address - this should come from a Properties...
			mesg.setFrom(new InternetAddress(MYADDR));

			// TO Address
			InternetAddress toAddress = new InternetAddress(
				"administrator@[" + hostIP + "]");
			mesg.addRecipient(Message.RecipientType.TO, toAddress);

			// CC Address
			// InternetAddress ccAddress = new InternetAddress(message_cc);
			// mesg.addRecipient(Message.RecipientType.CC, ccAddress);

			// The Subject
			mesg.setSubject("VIRUS ALERT");

			// Now the message body.
			mesg.setText(message_body);
			// XXX I18N: use setText(msgText.getText(), charset)

			// Finally, send the message!
			Transport.send(mesg);

		} catch (MessagingException ex) {
			out.println("<hr><h1>Mail error</h1>");
			out.println("Rats! An error occurred sending a mail notification");
			out.println("<pre>");
			while ((ex = (MessagingException)ex.getNextException()) != null) {
				ex.printStackTrace(out);
			}
			log("Failed to notify " + hostIP);
			out.flush();
			return;
		}
		log("Sent notification for " + hostIP);
		list.put(hostIP, "");
		out.flush();
	}
}
