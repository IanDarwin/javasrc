import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*; 

/** sender -- send an email message.
 * If you give more than one file, each file will be sent to the
 * same recipient with the same subject, so you generally don't want to.
 * @author Ian F. Darwin
 * @version $Id$
 */
public class TestOpenMailRelay {


	/** Driver to parse options and control Sender */
	public static void main(String args[]) {
		if (args.length != 1) {
			System.err.println("Usage: " + "TestOpenMailRelay" +
				" suspected_relay");
			System.exit(1);
		}
		String suspect_relay = args[0];
		try {
			Sender2 sm = new Sender2();
			sm.props.put("mail.smtp.host", suspect_relay);
			sm.addRecipient("ian@darwinsys.com");
			sm.setFrom("spam-magnet@darwinsys.com");
			sm.setSubject("Testing for open mail relay, see www.vix.org/rbl");
			sm.setBody("This mail is an attempt to confirm that site " +
				suspect_relay + "\n" +
				"is in fact an open mail relay site.\n" +
				"For more information on the notion of open mail relays,\n" +
				"please visit site " + "http://www.vix.org/rbl/.\n" + // XXX
				"Please join the fight against spam by closing all open mail relays!\n" +
				"If this open relay has been closed, please accept our thanks.\n");
			sm.sendFile();
		} catch (MessagingException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
