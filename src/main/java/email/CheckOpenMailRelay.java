package email;

import java.io.IOException;
import java.io.PrintStream;

import javax.mail.MessagingException;

/** TestOpenMailRelay -- send self-returning SPAM to check for relay sites.
 * @author Ian F. Darwin
 * @version $Id$
 */
public class TestOpenMailRelay {

	/** Where to refer people that find the test messages on their system. */
	public final static String RSS_SITE = "http://mail-abuse.org/rss/";

	/** Where the test messages will be collected. */
	public final static String MY_TARGET = "spam-magnet@darwinsys.com";

	/** Driver to parse options and control Sender */
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			new TestOpenMailRelayGUI().setVisible(true);
		} else {
			for (int i=0; i<args.length; i++) {
				process(args[i]);
			}
		}
	}

	/** Try the given mail server, writing output to System.out */
	public static void process(String suspect_relay) {
		process(suspect_relay, System.out);
	}

	/** Try the given mail server, writing output to the given PrintStream */
	public static void process(String suspect_relay, PrintStream pw) {
		pw.println("processs: trying: " + suspect_relay);
		try {
			// Redirect all output from mail API to the given stream.
			System.setOut(pw);
			System.setErr(pw);
			Sender2 sm = new Sender2(suspect_relay);
			sm.addRecipient("nobody@erewhon.moc");
			sm.setFrom(MY_TARGET);
			sm.setSubject("Testing for open mail relay, see " + RSS_SITE);
			sm.setBody("This mail is an attempt to confirm that site " +
				suspect_relay + "\n" +
				"is in fact an open mail relay site.\n" +
				"For more information on the problem of open mail relays,\n" +
				"please visit site " + RSS_SITE + "\n" +
				"Please join the fight against spam by closing all open mail relays!\n" +
				"If this open relay has been closed, please accept our thanks.\n");
			sm.sendFile();
		} catch (MessagingException e) {
			pw.println(e);
		} catch (Exception e) {
			pw.println(e);
		}
	}
}
