import com.darwinsys.util.*;
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*; 

/** TestOpenMailRelay -- send self-returning SPAM to check for relay sites.
 * @author Ian F. Darwin
 * @version $Id$
 */
public class TestOpenMailRelay {

	public final static String RSS_SITE = "http://mail-abuse.org/rss/";

	/** Driver to parse options and control Sender */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: " + "TestOpenMailRelay" +
				" suspected_relay");
			System.exit(1);
		}
		process(args[0]);
	}

	public static void process(String suspect_relay) {
		try {
			Sender2 sm = new Sender2();
			sm.props.put("mail.smtp.host", suspect_relay);
			sm.addRecipient("ian@darwinsys.com");
			sm.setFrom("spam-magnet@darwinsys.com");
			sm.setSubject("Testing for open mail relay, see " + RSS_SITE);
			sm.setBody("This mail is an attempt to confirm that site " +
				suspect_relay + "\n" +
				"is in fact an open mail relay site.\n" +
				"For more information on the notion of open mail relays,\n" +
				"please visit site " + RSS_SITE + "\n" +
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
