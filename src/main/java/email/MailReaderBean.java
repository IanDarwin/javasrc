// $Id$
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.sun.java.swing.*;
import com.sun.java.swing.border.*;
import com.sun.java.swing.tree.*;

/**
  * Display a mailbox, initially faked.
  */
public class MailReaderBean extends JPanel {

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
