import java.util.Properties;
import javax.mail.*;

/**
* List all available folders.
*/
public class MailLister {
	public static void main(String argv[]) throws Exception {
		if (argv.length < 5) {
			System.err.println("Usage: MailLister protocol host user pw root");
			System.exit(0);
		}
		String protocol = argv[0];
		String host = argv[1];
		String user = argv[2];
		String password = argv[3];
		String root = argv[4];
		String pattern = "%";
		boolean recursive = false;

		// Start with a Session object, as usual
		Session session = Session.getDefaultInstance(
			System.getProperties(), null);
		session.setDebug(false);

		// Get a Store object for the given protocol
		Store store = session.getStore(protocol);
		store.connect(host, user, password);

		// Map the given folder
		Folder folder = store.getFolder(root);
		if (folder == null || !folder.exists()) {
			System.out.println("Invalid folder");
			System.exit(1);
		}

		// Get Folder object, and list it
		Folder rf;
		if (root.length() != 0) {
			System.out.println("Getting folder " + root + ".");
			rf = store.getFolder(root);
		} else {
			System.out.println("Getting default folder.");
			rf = store.getDefaultFolder();
		}

		Folder[] f = rf.list(pattern);
		for (int i = 0; i < f.length; i++)
			listFolder(f[i], "", recursive);
	}

	static void listFolder(Folder folder, String tab, boolean recurse) throws Exception {
		System.out.println(tab + "Name: " + folder.getName());
		System.out.println(tab + "Full Name: " +
		folder.getFullName());
		if (!folder.isSubscribed())
			System.out.println(tab + "Not Subscribed");
		if (((folder.getType() & Folder.HOLDS_MESSAGES) != 0) &&
			folder.hasNewMessages())
				System.out.println(tab + "Has New Messages");
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
