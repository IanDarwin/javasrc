import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Display a mailbox or mailboxes.
 * @version $Id$
 */
public class MailReaderBean extends JSplitPane implements MailConstants {

	private JTextArea bodyText;
	private String protocol;
	private String host;
	private String user;
	private String password;
	private String rootName;

    public MailReaderBean() throws Exception {

		super(VERTICAL_SPLIT);

		boolean recursive = false;

		// Get the properties to be sure we can open the mail connection
		protocol = System.getProperty(RECV_PROTO);
		host = System.getProperty(RECV_HOST);
		user = System.getProperty(RECV_USER);
		password = System.getProperty(RECV_PASS);
		rootName = System.getProperty(RECV_ROOT);
		int port = System.getProperty(RECV_PORT) == null ? -1 :
			Integer.parseInt(System.getProperty(RECV_PORT));

		// Start with a Mail Session object
		Session session = Session.getDefaultInstance(
			System.getProperties(), null);
		session.setDebug(false);

		// Construct a javax.mail.URLName representing all the information.
		URLName connection = new URLName(protocol,
               host, port, rootName, user, password);

		// Use the URLName to get a Store object to read the mail from
		Store store = session.getStore(connection);
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
			Folder[] f = top.f.list();
			for (int i = 0; i < f.length; i++)
				listFolder(top, new FolderNode(f[i]), recursive);
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
		bodyText.setEditable(false);	// incoming mail is read-only
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
						for (int i=0; i<tos.length; i++) {
							bodyText.append(tos[i].toString());
							bodyText.append(" ");
						}
						bodyText.append("\n");

						bodyText.append("Subject: " + m.getSubject() + "\n");
						bodyText.append("From: ");
						Object[] froms = m.getFrom();
						for (int i=0; i<froms.length; i++) {
							bodyText.append(froms[i].toString());
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

	static void listFolder(FolderNode top, FolderNode folder, boolean recurse) throws Exception {
		// System.out.println(folder.f.getName() + folder.f.getFullName());
		if ((folder.f.getType() & Folder.HOLDS_MESSAGES) != 0) {
			Message[] msgs = folder.f.getMessages();
			for (int i=0; i<msgs.length; i++) {
				MessageNode m = new MessageNode(msgs[i]);
				Address from = m.m.getFrom()[0];
				String fromAddress;
				if (from instanceof InternetAddress)
					fromAddress = ((InternetAddress)from).getAddress();
				else
					fromAddress = from.toString();
				top.add(new MessageNode(msgs[i]));
			}
		}
		if ((folder.f.getType() & Folder.HOLDS_FOLDERS) != 0) {
			if (recurse) {
				Folder[] f = folder.f.list();
				for (int i=0; i < f.length; i++)
					listFolder(new FolderNode(f[i]), top, recurse);
				}
		}
	}

	/* Test unit - main program */
	public static void main(String[] args) throws Exception {
		final JFrame jf = new JFrame("MailReaderBean");
		String mbox = "/var/mail/ian";
		if (args.length > 0)
			mbox = args[0];
		MailReaderBean mb = new MailReaderBean();
		jf.getContentPane().add(mb);
		jf.setSize(640,480);
		jf.setVisible(true);
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			jf.setVisible(false);
			jf.dispose();
			System.exit(0);
			}
		});
	}
}
