package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/** sender -- send an email message.
 * @author Ian F. Darwin
 */
// BEGIN main
public class Sender {

	/** The message recipient. */
	protected String message_recip = "spam-magnet@darwinsys.com";
	/* What's it all about, Alfie? */
	protected String message_subject = "Re: your mail";
	/** The message CC recipient. */
	protected String message_cc = "nobody@erewhon.com";
	/** The message body */
	protected String message_body =
		"I am unable to attend to your message, as I am busy sunning " +
		"myself on the beach in Maui, where it is warm and peaceful." +
		"Perhaps when I return I'll get around to reading your mail. " +
		"Or not.";

	/** The JavaMail session object */
	protected Session session;
	/** The JavaMail message object */
	protected Message mesg;

	/** Do the work: send the mail to the SMTP server.  */
	public void doSend() {

		// We need to pass info to the mail server as a Properties, since
		// JavaMail (wisely) allows room for LOTS of properties...
		Properties props = new Properties();

		// Your LAN must define the local SMTP server as "mailhost"
		// for this simple-minded version to be able to send mail...
		props.put("mail.smtp.host", "mailhost");

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
			mesg.setText(message_body);
			// XXX I18N: use setText(msgText.getText(), charset)
			
			// Finally, send the message!
			Transport.send(mesg);

		} catch (MessagingException e) {
			throw new RuntimeException("Mail sending failed: " + e, e);
		}
	}

	/** Simple test case driver */
	public static void main(String[] av) {
		Sender sm = new Sender();
		sm.doSend();
	}
}
// END main
