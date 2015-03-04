package email;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.darwinsys.mail.Mailer;
import com.darwinsys.util.FileProperties;

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
 */
// BEGIN main
public class MailComposeBean extends JPanel {
	private static final long serialVersionUID = 1L;
	
	/** The parent frame to be hidden/disposed; may be JFrame, JInternalFrame
	 * or JPanel, as necessary */
	private Container parent;

	private JButton sendButton, cancelButton;
	private JTextArea msgText;		// The message!

	// The To, Subject, and CC lines are treated a bit specially,
	// any user-defined headers are just put in the tfs array.
	private JTextField tfs[], toTF, ccTF, subjectTF;
	private final int TO = 0, SUBJ = 1, CC = 2, /* BCC = 3, */ MAXTF = 8;

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

		// THE GUI
		Container cp = this;
		cp.setLayout(new BorderLayout());


		// Top is a JPanel for name, address, etc.
		// Center is the TextArea.
		// Bottom is a panel with Send and Cancel buttons.
		JPanel tp = new JPanel();
		tp.setLayout(new GridLayout(3,2));
		cp.add(BorderLayout.NORTH, tp);

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

		// Center is the TextArea
		cp.add(BorderLayout.CENTER, msgText = new JTextArea(70, 10));
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
					JOptionPane.showMessageDialog(null,
						"Sending error:\n" + err.toString(),
						"Send failed", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		bp.add(cancelButton = new JButton("Cancel"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maybeKillParent();
			}
		});
		cp.add(BorderLayout.SOUTH, bp);
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

		try {
			Mailer m = new Mailer();

			FileProperties props =
				new FileProperties(MailConstants.PROPS_FILE_NAME);
			String serverHost = props.getProperty(MailConstants.SEND_HOST);
			if (serverHost == null) {
				JOptionPane.showMessageDialog(parent,
					"\"" + MailConstants.SEND_HOST +
						"\" must be set in properties",
					"No server!",
					JOptionPane.ERROR_MESSAGE);
				return;
			}
			m.setServer(serverHost);

			String tmp = props.getProperty(MailConstants.SEND_DEBUG);
			m.setVerbose(tmp != null && tmp.equals("true"));

			String myAddress = props.getProperty("Mail.address");
			if (myAddress == null) {
				JOptionPane.showMessageDialog(parent,
					"\"Mail.address\" must be set in properties",
					"No From: address!",
					JOptionPane.ERROR_MESSAGE);
				return;
			}
			m.setFrom(myAddress);

			m.setToList(toTF.getText());
			m.setCcList(ccTF.getText());
			// m.setBccList(bccTF.getText());

			if (subjectTF.getText().length() != 0) {
				m.setSubject(subjectTF.getText());
			}

			// Now copy the text from the Compose TextArea.
			m.setBody(msgText.getText());
			// XXX I18N: use setBody(msgText.getText(), charset)
				
			// Finally, send the sucker!
			m.doSend();

			// Now hide the main window
			maybeKillParent();

		} catch (MessagingException me) {
			me.printStackTrace();
			while ((me = (MessagingException)me.getNextException()) != null) {
				me.printStackTrace();
			}
			JOptionPane.showMessageDialog(null,
				"Mail Sending Error:\n" + me.toString(),
				"Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
				"Mail Sending Error:\n" + e.toString(),
				"Error", JOptionPane.ERROR_MESSAGE);
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


	/** Simple test case driver */
	public static void main(String[] av) {
		final JFrame jf = new JFrame("DarwinSys Compose Mail Tester");
		System.getProperties().setProperty("Mail.server", "mailhost");
		System.getProperties().setProperty("Mail.address", "nobody@home");
		MailComposeBean sm =
			new MailComposeBean(jf, 
			"Test Mailer", "spam-magnet@darwinsys.com", 500, 400);
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
// END main
