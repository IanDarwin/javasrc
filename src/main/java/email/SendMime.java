package email;

import java.io.IOException;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.darwinsys.util.FileProperties;

/** SendMime -- send a multi-part MIME email message.
 * @author Ian F. Darwin
 */
// BEGIN main
public class SendMime {

	/** The message recipient. */
	protected String message_recip = "spam-magnet@somedomainnamehere.com";
	/* What's it all about, Alfie? */
	protected String message_subject = "Re: your mail";
	/** The message CC recipient. */
	protected String message_cc = "nobody@erewhon.com";
	/** The text/plain message body */
	protected String message_body =
		"I am unable to attend to your message, as I am busy sunning " +
		"myself on the beach in Maui, where it is warm and peaceful. " +
		"Perhaps when I return I'll get around to reading your mail. " +
		"Or not.";
	/* The text/html data. */
	protected String html_data = 
		"<html><head><title>My Goodness</title></head>" +
		"<body><p>You <em>do</em> look a little " +
		"<font color='green'>GREEN</font> " +
		"around the edges..." +
		"</body></html>";

	/** The JavaMail session object */
	protected Session session;
	/** The JavaMail message object */
	protected Message mesg;

	/** Do the work: send the mail to the SMTP server.  */
	public void doSend() throws IOException, MessagingException {

		// We need to pass info to the mail server as a Properties, since
		// JavaMail (wisely) allows room for LOTS of properties...
		FileProperties props = 
			new FileProperties(MailConstants.PROPS_FILE_NAME);

		// Copy the value of Mail.send.host into mail.smtp.host
		props.setProperty("mail.smtp.host", 
			props.getProperty(MailConstants.SEND_HOST));

		// Create the Session object
		session = Session.getDefaultInstance(props, null);
		session.setDebug(true);		// Verbose!
		
		try {
			// create a message
			mesg = new MimeMessage(session);

			// From Address - this should come from a Properties...
			mesg.setFrom(new InternetAddress("nobody@host.domain"));

			// TO Address 
			InternetAddress toAddress = new InternetAddress(message_recip);
			mesg.addRecipient(Message.RecipientType.TO, toAddress);

			// CC Address
			InternetAddress ccAddress = new InternetAddress(message_cc);
			mesg.addRecipient(Message.RecipientType.CC, ccAddress);

			// The Subject
			mesg.setSubject(message_subject);

			// Now the message body.
			Multipart mp = new MimeMultipart();

			BodyPart textPart = new MimeBodyPart();
			textPart.setText(message_body);	// sets type to "text/plain"

			BodyPart pixPart = new MimeBodyPart();
			pixPart.setContent(html_data, "text/html");

			// Collect the Parts into the MultiPart
			mp.addBodyPart(textPart);
			mp.addBodyPart(pixPart);

			// Put the MultiPart into the Message
			mesg.setContent(mp);
			
			// Finally, send the message!
			Transport.send(mesg);

		} catch (MessagingException ex) {
			System.err.println(ex);
			ex.printStackTrace(System.err);
		}
	}
	// END main

	/** Simple test case driver */
	public static void main(String[] av) throws Exception {
		SendMime sm = new SendMime();
		sm.doSend();
	}
}
