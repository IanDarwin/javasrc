import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

/**
 * Display a mailbox, currently faked.
 * @version $Id$
 */
public class MailReaderBean extends JPanel implements JDModule {

    public MailReaderBean() {
		super();

		DefaultMutableTreeNode inbox;
		DefaultMutableTreeNode javaone;
		DefaultMutableTreeNode personal;
		DefaultMutableTreeNode spam;	

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Mail Boxes");

		top.add( inbox = new DefaultMutableTreeNode("INBOX") );
		top.add( javaone = new DefaultMutableTreeNode("javaone") );
		top.add( personal = new DefaultMutableTreeNode("personal") );
		top.add( spam = new DefaultMutableTreeNode("spam") );

		inbox.add(new DefaultMutableTreeNode(
			"ian@darwinsys.com    Call home"));
		inbox.add(new DefaultMutableTreeNode(
			"jag@sun.com                Re: Oak"));
		inbox.add( new DefaultMutableTreeNode(
			"legal@low_isp.net    Re: unsolicited email"));

		// next few fakes stolen from MetalWorks!
		personal.add( new DefaultMutableTreeNode("Hi") );
		personal.add( new DefaultMutableTreeNode("Good to hear from you") );
		personal.add( new DefaultMutableTreeNode("Re: Thank You") );

		javaone.add( new DefaultMutableTreeNode("Thanks for your order") );
		javaone.add( new DefaultMutableTreeNode("Price Quote") );
		javaone.add( new DefaultMutableTreeNode("Here is the invoice") );
		javaone.add( new DefaultMutableTreeNode("Project Metal: delivered on time") );
		javaone.add( new DefaultMutableTreeNode("Your salary raise approved") );

		spam.add( new DefaultMutableTreeNode("Buy Now") );
		spam.add( new DefaultMutableTreeNode("Make $$$ Now") );
		spam.add( new DefaultMutableTreeNode("HOT HOT HOT") );
		spam.add( new DefaultMutableTreeNode("Buy Now") );
		spam.add( new DefaultMutableTreeNode("Don't Miss This") );
		spam.add( new DefaultMutableTreeNode("Buy Now") );
		spam.add( new DefaultMutableTreeNode("Last Chance") );
		spam.add( new DefaultMutableTreeNode("Buy Now") );
		spam.add( new DefaultMutableTreeNode("Make $$$ Now") );
		spam.add( new DefaultMutableTreeNode("To Hot To Handle") );
		spam.add( new DefaultMutableTreeNode("I'm waiting for your call") );

		JTree tree = new JTree(top);
		JScrollPane treeScroller = new JScrollPane(tree);
		treeScroller.setBackground(tree.getBackground());
		add(treeScroller);
	}

	public Dimension getPreferredSize() {
		return new Dimension(325, 200);
	}

	public Dimension getMinimumSize() {
		return new Dimension(200, 170);
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
	public static void main(String a[]) {
		final JFrame jf = new JFrame("MailReaderBean");
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
