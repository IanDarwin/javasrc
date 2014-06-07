package email;

// BEGIN main
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * Display a mailbox or mailboxes.
 * This is a generic GUI component for displaying email.
 */
public class MailReaderBean extends JSplitPane {

	private static final long serialVersionUID = 1L;
	
	private JTextArea bodyText;

	/* Construct a mail reader bean with all defaults.
	 */
	public MailReaderBean() throws Exception {
		this("imap", "mailhost", "user", "*", "/");
	}

	/* Construct a mail reader bean with all values. */
    public MailReaderBean(
		String protocol,
		String host,
		String user,
		String password,
		String rootName)
	throws Exception {

		super(VERTICAL_SPLIT);

		boolean recursive = false;

		// Start with a Mail Session object
		Session session = Session.getDefaultInstance(
			System.getProperties(), null);
		session.setDebug(false);

		// Get a Store object for the given protocol
		Store store = session.getStore(protocol);
		store.connect(host, user, password);

		// Get Folder object for root, and list it
		// If root name = "", getDefaultFolder(), else getFolder(root)
		FolderNode top;
		if (rootName.length() != 0) {
			// System.out.println("Getting folder " + rootName + ".");
			top = new FolderNode(store.getFolder(rootName));
		} else {
			// System.out.println("Getting default folder.");
			top = new FolderNode(store.getDefaultFolder());
		}
		if (top == null || !top.f.exists()) {
			System.out.println("Invalid folder " + rootName);
			return;
		}

		if (top.f.getType() == Folder.HOLDS_FOLDERS) {
			Folder[] fs = top.f.list();
			for (Folder f : fs)
				listFolder(top, new FolderNode(f), recursive);
		} else
				listFolder(top, top, false);

		// Now that (all) the foldernodes and treenodes are in,
		// construct a JTree object from the top of the list down,
		// make the JTree scrollable (put in JScrollPane),
		// and add it as the MailComposeBean's Northern child.
		JTree tree = new JTree(top);
		JScrollPane treeScroller = new JScrollPane(tree);
		treeScroller.setBackground(tree.getBackground());
		this.setTopComponent(treeScroller);

		// The Southern (Bottom) child is a textarea to display the msg.
		bodyText = new JTextArea(15, 80);
		this.setBottomComponent(new JScrollPane(bodyText));

		// Add a notification listener for the tree; this will
		// display the clicked-upon message
		TreeSelectionListener tsl = new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent evt) {
				Object[] po = evt.getPath().getPath();	// yes, repeat it.
				Object o = po[po.length - 1];	// last node in path
				if (o instanceof FolderNode) {
					// System.out.println("Select folder " + o.toString());
					return;
				}
				if (o instanceof MessageNode) {
					bodyText.setText("");
					try {
						Message m = ((MessageNode)o).m;

						bodyText.append("To: ");
						Object[] tos = m.getAllRecipients();
						for (Object to : tos) {
							bodyText.append(to.toString());
							bodyText.append(" ");
						}
						bodyText.append("\n");

						bodyText.append("Subject: " + m.getSubject() + "\n");
						bodyText.append("From: ");
						Object[] froms = m.getFrom();
						for (Object from : froms) {
							bodyText.append(from.toString());
							bodyText.append(" ");
						}
						bodyText.append("\n");

						bodyText.append("Date: " + m.getSentDate() + "\n");
						bodyText.append("\n");

						bodyText.append(m.getContent().toString());

						// Start reading at top of message(!)
						bodyText.setCaretPosition(0);
					} catch (Exception e) {
						bodyText.append(e.toString());
					}
				} else 
					System.err.println("UNEXPECTED SELECTION: " + o.getClass());
			}
		};
		tree.addTreeSelectionListener(tsl);
	}

	/** Process one folder. */
	static void listFolder(FolderNode top, FolderNode folder, boolean recurse)
		throws Exception {

		if ((folder.f.getType() & Folder.HOLDS_MESSAGES) != 0) {
			Message[] msgs = folder.f.getMessages();
			for (Message ms : msgs) {
				MessageNode m = new MessageNode(ms);
				Address from = m.m.getFrom()[0];
				String fromAddress;
				if (from instanceof InternetAddress)
					fromAddress = ((InternetAddress)from).getAddress();
				else
					fromAddress = from.toString();
				top.add(new MessageNode(ms));
			}
		}
		if ((folder.f.getType() & Folder.HOLDS_FOLDERS) != 0) {
			if (recurse) {
				Folder[] fs = folder.f.list();
				for (Folder f : fs) {
					listFolder(new FolderNode(f), top, recurse);
				}
			}
		}
	}

	/* Demo unit - main program */
	public static void main(String[] args) throws Exception {
		final JFrame jf = new JFrame("MailReaderBean");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String mbox = "INBOX";
		if (args.length > 0)
			mbox = args[0];
		MailReaderBean mb = new MailReaderBean("imap", "localhost",
			System.getProperty("user.name"), "*", mbox);
		jf.getContentPane().add(mb);
		jf.setSize(640,480);
		jf.setVisible(true);
	}
}
// END main
