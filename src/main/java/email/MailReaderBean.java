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
public class MailReaderBean extends JSplitPane {

	private JTextArea bodyText;

    public MailReaderBean(
		String protocol,
		String host,
		String user,
		String password,
		String rootName) throws Exception {

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
		this.add(treeScroller);

		// The Southern (Bottom) child is a textarea to display the msg.
		bodyText = new JTextArea(20, 80);
		this.add(new JScrollPane(bodyText));

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

  	/** Start a new file, prompting to save the old one first. */
	public void newFile(){}

	/** Load new model from fn{} if null, prompt for new fname */
	public void loadFile(String fn){}

	/** Save the current model's data in fn. 
	 * If null, use current fname or prompt for a filename. */
	public void saveFile(String fn){}

	/** Export the file in a (portable?) format.
	 */
	 public void exportFile(){}

	/** Ask the model if it has any unsaved changes, don't save otherwise */
	public boolean hasUnsavedChanges(){ return false; }

	/** If the module wants AutoSave when the user enables it. */
	public boolean wantAutoSave(){ return false; }

	/** Start the module's print routine */
	public void doPrint(){
		JOptionPane.showMessageDialog(null,
			"Can't Print Mail Yet", "TODO",
			JOptionPane.INFORMATION_MESSAGE);
	}
	/** Create a new item (usually by dialog?) */
	public void newItem(){}

	/** Modify the current via a dialog */
	public void modItem(){}

	/** Get this module to remember and control a MenuItem.  */
	public void ownThisMenuItem(JMenuItem mi) {
		// NOT USED - util.ownThisMenuItem(mi);
	}

	/** Enable the menuItems we own */
	public void beingShown() {
		// NOT USED - util.showMenus();
	}

	/** Disable the menuItems we own */
	public void beingHidden() {
		// NOT USED - util.hideMenus();
	}

	/** Edit->Copy */
	public void editCopy(){}

	/** Edit->Cut */
	public void editCut(){}

	/** Edit->Paste */
	public void editPaste(){}

	/** Edit->Delete */
	public void editDelete(){}

	/** If can undo */
	public boolean hasUndoableChange(){ return false; }

	/** Edit->Undo */
	public void undoChange(){}

	/** Edit->ReDo */
	public void redoChange(){}

	/** Synchronize with a PDA (tentative - functionality may go elsewhere) */
	public void doSynch(){}

	/* test case */
	public static void main(String[] args) throws Exception {
		final JFrame jf = new JFrame("MailReaderBean");
		String mbox = "/var/mail/ian";
		if (args.length > 0)
			mbox = args[0];
		MailReaderBean mb = new MailReaderBean("mbox", "localhost",
			"", "", mbox);
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
