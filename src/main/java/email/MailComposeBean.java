import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*; 

/** MailComposeBean - Mail gather and send Component Bean.
 *
 * Can be used as a Visible bean or as a Non-Visible bean.
 * If setVisible(true), puts up a mail compose window with a Send button.
 * If user clicks on it, tries to send the mail to a Mail Server
 * for delivery on the Internet.
 *
 * If not visible, use addXXX(), setXXX(), and doSend() methods.
 *
 * @author Ian F. Darwin
 * @version $Id$
 */
public class MailComposeBean extends JPanel {

	/** The parent frame to be hidden/disposed; may be JFrame, JInternalFrame
	 * or JPanel, as necessary */
	private Container parent;

	private JButton sendButton, cancelButton;
	private JTextArea msgText;		// The message!

	// The To, Subject, and CC lines are treated a bit specially,
	// any user-defined headers are just put in the tfs array.
	private JTextField tfs[], toTF, ccTF, subjectTF;
	// tfsMax MUST == how many are current, for focus handling to work
	private int tfsMax = 3;
	private final int TO = 0, SUBJ = 1, CC = 2, BCC = 3, MAXTF = 8;

	/** The list of TO recipients */
	Vector toList;
	/** The list of Cc recipients */
	Vector ccList;
	/** The list of Bcc recipients */
	Vector bccList;

	/** The JavaMail session object */
	private Session session = null;
	/** The JavaMail message object */
	private Message mesg = null;

	private int mywidth;
	private int myheight;

	/** Construct a MailComposeBean with no default recipient */
	MailComposeBean(Container parent, String title, int height, int width) {
		this(parent, title, null, height, width);
	}

	/** Construct a MailComposeBean with no arguments (needed for Beans) */
	MailComposeBean() {
		this(null, "Compose", null, 300, 200);
	}

	/** Constructor for MailComposeBean object.
	 *
	 * @param parent	Container parent. If JFrame or JInternalFrame,
	 *					will setvisible(false) and dispose() when
	 *					message has been sent. Not done if "null" or JPanel.
	 * @param title		Title to display in the titlebar
	 * @param recipient	Email address of recipient
	 * @param height	Height of mail compose window
	 * @param width		Width of mail compose window
	 */
	MailComposeBean(Container parent, String title, String recipient,
			int width, int height) {
		super();

		this.parent = parent;

		mywidth = width;
		myheight = height;

		// THE DATA STRUCTURE
		toList = new Vector();
		ccList = new Vector();
		bccList = new Vector();

		// THE GUI
		Container cp = this;
		cp.setLayout(new BorderLayout());

		// Nothing doing, if the javax.mail package is not installed!
		// But construct the data structures (above) first to avoid
		// further runtime exceptions.
		try {
			Class.forName("javax.mail.Session");
		} catch (ClassNotFoundException cnfe) {
			add(BorderLayout.CENTER, 
				new JLabel("Sorry, the javax.mail package was not found",
				JLabel.CENTER));
			return;
		}

		// Top is a JPanel for name, address, etc.
		// Centre is the TextArea.
		// Bottom is a panel with Send and Cancel buttons.
		JPanel tp = new JPanel();
		tp.setLayout(new GridLayout(3,2));
		cp.add("North", tp);

		tfs = new JTextField[MAXTF];

		tp.add(new JLabel("To: ", JLabel.RIGHT));
		tp.add(tfs[TO] = toTF = new JTextField(35));
		if (recipient != null)
			toTF.setText(recipient);
		toTF.requestFocus();

		tp.add(new JLabel("Subject: ", JLabel.RIGHT));
		tp.add(tfs[SUBJ] = subjectTF = new JTextField(35));
		subjectTF.requestFocus();

		tp.add(new JLabel("Cc: ", JLabel.RIGHT));
		tp.add(tfs[CC] = ccTF = new JTextField(35));

		// Centre is the TextArea
		cp.add("Center", msgText = new JTextArea(70, 10));
		msgText.setBorder(BorderFactory.createTitledBorder("Message Text"));

		// Bottom is the apply/cancel button
		JPanel bp = new JPanel();
		bp.setLayout(new FlowLayout());
		bp.add(sendButton = new JButton("Send"));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					doSend();
				} catch(Exception err) {
					System.err.println("Error: " + err);
				}
			}
		});
		bp.add(cancelButton = new JButton("Cancel"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maybeKillParent();
			}
		});
		cp.add("South", bp);
	}

	public Dimension getPreferredSize() {
		return new Dimension(mywidth, myheight);
	}
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	/** Do the work: send the mail to the SMTP server.
	 * 
	 * ASSERT: must have set at least one recipient.
	 */
	public void doSend() {

		// We need to pass info to the mail server as a Properties, since
		// JavaMail (wisely) allows room for LOTS of properties...
		Properties props = new Properties();
		String serverHost = System.getProperty("Mail.server");
		if (serverHost == null) {
			JOptionPane.showMessageDialog(parent,
				"\"Mail.server\" must be set in System.properties",
				"No server!?",
				JOptionPane.ERROR_MESSAGE);
			return;
		}
		props.put("mail.smtp.host", serverHost);

		session = Session.getDefaultInstance(props, null);
		session.setDebug(true);		// XXX

		String myAddress = System.getProperty("Mail.address");
		if (myAddress == null) {
			JOptionPane.showMessageDialog(parent,
				"\"Mail.address\" must be set in System.properties",
				"No server!?",
				JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			// create a message
			mesg = new MimeMessage(session);

			// From Address - from System properties
			mesg.setFrom(new InternetAddress(myAddress));

			// TO Address - FROM the textfield and/or
			// any addTOrecipient() call
			String recip = toTF.getText();
			if (recip != null && recip.length() > 0) {
				StringTokenizer tf = new StringTokenizer(recip, ",");
				while (tf.hasMoreTokens())
					addTOrecipient(tf.nextToken());
			}
			InternetAddress[] toAddress = new InternetAddress[toList.size()];
			for (int i=0; i<toList.size(); i++)
				toAddress[i] = new InternetAddress((String)toList.elementAt(i));
			mesg.setRecipients(Message.RecipientType.TO, toAddress);

			if (mesg.getAllRecipients()== null ||
				mesg.getAllRecipients().length == 0) {
				return;
			}

			// CC - XXX ONLY ONE FOR NOW
			String s;
			if ((s = ccTF.getText()).length() != 0) {
				InternetAddress[] ccAddress = {new InternetAddress(s)};
				mesg.setRecipients(Message.RecipientType.CC, ccAddress);
			}

			// The Subject
			if ((s = subjectTF.getText()).length() != 0) {
				mesg.setSubject(s);
			}

			// Now copy the text from the Compose TextArea.
			mesg.setText(msgText.getText());
			// XXX I18N: use setText(msgText.getText(), charset)
			
			// Finally, send the sucker!
			Transport.send(mesg);

			// Now hide the main window
			maybeKillParent();

		} catch (MessagingException ex) {
			ex.printStackTrace();
			while ((ex = (MessagingException)ex.getNextException()) != null) {
				ex.printStackTrace();
			}
		}
	}

	private void maybeKillParent() {
		if (parent == null)
			return;
		if (parent instanceof Frame) {
			((Frame)parent).setVisible(true);
			((Frame)parent).dispose();
		}
		if (parent instanceof JInternalFrame) {
			((JInternalFrame)parent).setVisible(true);
			((JInternalFrame)parent).dispose();
		}
	}


	// METHODS FOR USE IN BEANS

	/** Add a given recipient to the TO list */
	public void addTOrecipient(String address) {
		toList.addElement(address);
	}

	/** Add a given recipient to the CC list */
	public void addCCrecipient(String address) {
		ccList.addElement(address);
	}

	/** Add a given recipient to the BCC list */
	public void addBCCrecipient(String address) {
		bccList.addElement(address);
	}

	/** Set the subject */
	public void setSubject(String s) {
		try {
			mesg.setSubject(subjectTF.getText());
		} catch (Exception e) {
			System.out.println("SetSubject failed?!?" + e);
		}
	}

	/** Set the text */
	public void setText(String s) {
	}

	/** Simple test case driver */
	public static void main(String av[]) {
		final JFrame jf = new JFrame("DarwinSys Compose Mail Tester");
		System.setProperty("Mail.server", "mailhost");
		System.setProperty("Mail.address", "nobody@home");
		MailComposeBean sm = 
			new MailComposeBean(jf, "Test Mailer", "spam-magnet@darwinsys.com", 500, 400);
		sm.setSize(500, 400);
		jf.getContentPane().add(sm);
		jf.setLocation(100, 100);
		jf.setVisible(true);
        jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
			jf.setVisible(false);
			jf.dispose();
			System.exit(0);
			}
		});
		jf.pack();
	}
}
