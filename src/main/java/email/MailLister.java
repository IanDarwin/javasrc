import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
* List all available folders.
*/
public class MailLister {
	static StringFormat fromFmt = new StringFormat(20, StringFormat.JUST_LEFT);
	static StringFormat subjFmt = new StringFormat(40, StringFormat.JUST_LEFT);

	public static void main(String argv[]) throws Exception {
		String fileName = MailConstants.PROPS_FILE_NAME;

		if (argv.length < 5) {
			System.err.println("Usage: MailLister protocol host user pw root");
			System.exit(0);
		}
		String protocol = argv[0];
		String host = argv[1];
		String user = argv[2];
		String password = argv[3];
		String root = argv[4];

		// An alternate way of setting the values, based on Properties.
		// if (argv.length > 0) {
		// 	fileName = argv[0];
		// FileProperties fp = new FileProperties(fileName);
		// fp.load();
		// String protocol = fp.getProperty(MailConstants.RECV_PROTO);
		// String host = fp.getProperty(MailConstants.RECV_HOST);
		// String user = fp.getProperty(MailConstants.RECV_USER);
		// String password = fp.getProperty(MailConstants.RECV_PASS);
		// String root = fp.getProperty(MailConstants.RECV_ROOT);

		boolean recursive = false;

		// Start with a Session object, as usual
		Session session = Session.getDefaultInstance(
			System.getProperties(), null);
		session.setDebug(false);

		// Get a Store object for the given protocol
		Store store = session.getStore(protocol);
		store.connect(host, user, password);

		// Get Folder object for root, and list it
		// If root name = "", getDefaultFolder(), else getFolder(root)
		Folder rf;
		if (root.length() != 0) {
			System.out.println("Getting folder " + root + ".");
			rf = store.getFolder(root);
		} else {
			System.out.println("Getting default folder.");
			rf = store.getDefaultFolder();
		}

		if (rf.getType() == Folder.HOLDS_FOLDERS) {
			Folder[] f = rf.list();
			for (int i = 0; i < f.length; i++)
				listFolder(f[i], "", recursive);
		} else
				listFolder(rf, "", false);
	}

	static void listFolder(Folder folder, String tab, boolean recurse) throws Exception {
		System.out.println(tab + "Name: " + folder.getName() + '(' +
			folder.getFullName() + ')');
		if (!folder.isSubscribed())
			System.out.println(tab + "Not Subscribed");
		if ((folder.getType() & Folder.HOLDS_MESSAGES) != 0) {
			if (folder.hasNewMessages())
				System.out.println(tab + "Has New Messages");
			else
				System.out.println(tab + "No New Messages");
			Message[] msgs = folder.getMessages();
			for (int i=0; i<msgs.length; i++) {
				Message m = msgs[i];
				Address from = m.getFrom()[0];
				String fromAddress;
				if (from instanceof InternetAddress)
					fromAddress = ((InternetAddress)from).getAddress();
				else
					fromAddress = from.toString();
				StringBuffer sb = new StringBuffer();
				fromFmt.format(fromAddress, sb, null);
				sb.	append("  ");
				subjFmt.format(m.getSubject(), sb, null);
				System.out.println(sb.toString());
			}
		}
		if ((folder.getType() & Folder.HOLDS_FOLDERS) != 0) {
			System.out.println(tab + "Is Directory");
		if (recurse) {
			Folder[] f = folder.list();
			for (int i=0; i < f.length; i++)
				listFolder(f[i], tab + "", recurse);
			}
		}
	}
}
