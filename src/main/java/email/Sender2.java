package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.darwinsys.lang.GetOpt;

/** sender -- send an email message.
 * If you give more than one file, each file will be sent to the
 * same recipient with the same subject, so you generally don't want to.
 * @author Ian F. Darwin
 */
public class Sender2 {

	/** The message recipient. */
	protected String message_recip;
	/* What's it all about, Alfie? */
	protected String message_subject;
	/** The message CC recipient. */
	protected String message_cc;
	/** The message body */
	protected String message_body;

	/** The JavaMail session object */
	protected Session session;
	/** The JavaMail message object */
	protected Message mesg;

	/** Properties object used to pass props into the MAIL API */
	Properties props = new Properties();

	/** Construct a Sender2 object */
	public Sender2() throws MessagingException {

		// Your LAN must define the local SMTP as "mailhost"
		// for this simple-minded version to be able to send mail...
		props.put("mail.smtp.host", "mailhost");
		finish();
	}

	/** Construct a Sender2 object.
	 * @param hostName - the name of the host to send to/via.
	 */
	public Sender2(String hostName) throws MessagingException {

		props.put("mail.smtp.host", hostName);
		finish();
	}

	private void finish() {

		// Create the Session object
		session = Session.getDefaultInstance(props, null);
		// session.setDebug(true);		// Verbose!
		
		// create a message
		mesg = new MimeMessage(session);
	}

	public void sendFile(String fileName) throws MessagingException {
		// Now the message body.
		setBody(message_body);
			
		sendFile();
	}

	/** Send the file with no filename, assuming you've already called
	 * the setBody() method.
	 */
	public void sendFile() {
		try {
			
			// Finally, send the message! (use static Transport method)
			Transport.send(mesg);

		} catch (MessagingException ex) {
			while ((ex = (MessagingException)ex.getNextException()) != null) {
				ex.printStackTrace();
			}
		}
	}

	/** Stub for providing help on usage
	 * You can write a longer help than this, certainly.
	 */
	protected static void usage(int returnValue) {
		System.err.println("Usage: Sender2 [-t to][-c cc][-f from][-s subj] file ...");
		System.exit(returnValue);
	}

	public void addRecipient(String message_recip) throws MessagingException {
		// TO Address 
		InternetAddress toAddress = new InternetAddress(message_recip);
		mesg.addRecipient(Message.RecipientType.TO, toAddress);
	}

	public void addCCRecipient(String message_cc) throws MessagingException { 
		// CC Address
		InternetAddress ccAddress = new InternetAddress(message_cc);
		mesg.addRecipient(Message.RecipientType.CC, ccAddress);
	}

	public void setFrom(String sender) throws MessagingException {
		// From Address - this should come from a Properties...
		mesg.setFrom(new InternetAddress(sender));
	}

	public void setSubject(String message_subject) throws MessagingException {
		// The Subject
		mesg.setSubject(message_subject);
	}

	/** Set the message body. */
	public void setBody(String message_body) throws MessagingException {
		mesg.setText(message_body);
		// XXX I18N: use setText(msgText.getText(), charset)
	}

	/** Driver to parse options and control Sender */
	public static void main(String[] args) {
		try {
			Sender2 sm = new Sender2();
			GetOpt go = new GetOpt("c:f:t:s:");
			char c;
			while ((c =go.getopt(args)) != 0) {
				switch(c) {
				case 'h':
					// XXX sm.setMailHost();
					sm.props.put("mail.smtp.host", go.optarg());
					break;
				case 't':
					sm.addRecipient(go.optarg());
					break;
				case 'c':
					sm.addCCRecipient(go.optarg());
					break;
				case 'f':
					sm.setFrom(go.optarg());
					break;
				case 's':
					sm.setSubject(go.optarg());
					break;
				default:
					System.err.println("Unknown option character " + c);
					usage(1);
				}
			}
			if (go.getOptInd() == args.length) {
				sm.sendFile("(standard input)");
			} else for (int i=go.getOptInd(); i<args.length; i++)
				sm.sendFile(args[i]);
		} catch (MessagingException e) {
			System.err.println(e);
		}
	}
}
