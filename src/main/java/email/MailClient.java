import com.darwinsys.util.FileProperties;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/** Standalone MailClient GUI application.
 * @author	Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class MailClient extends JComponent implements MailConstants {
	/** The quit button */
	JButton quitButton;
	/** The read mode */
	MailReaderBean mrb;
	/** The send mode */
	MailComposeFrame mcb;

	/** Construct the MailClient JComponent a default Properties filename */
	public MailClient() throws Exception {
		this(PROPS_FILE_NAME);
	}

	/** Construct the MailClient JComponent with no Properties filename */
	public MailClient(String propsFileName) throws Exception {
		super();

		// Get the Properties for the mail reader and sender.
		// Save them in System.properties so other code can find them.
		FileProperties mailProps = new FileProperties(propsFileName);
		mailProps.load();

		// Gather some key values
		String proto = mailProps.getProperty(RECV_PROTO);
		String user  = mailProps.getProperty(RECV_USER);
		String pass  = mailProps.getProperty(RECV_PASS);
		String host  = mailProps.getProperty(RECV_HOST);

		if (proto==null)
			throw new IllegalArgumentException(RECV_PROTO + "==null");

		// Protocols other than "mbox" need a password.
		if (!proto.equals("mbox") && (pass == null || pass.equals("ASK"))) {
			String np;
			do {
				// VERY INSECURE -- should use JDialog + JPasswordField!
				np = JOptionPane.showInputDialog(null,
				"Please enter password for " + proto + " user  " +
					user + " on " + host + "\n" +
					"(warning: password WILL echo)",
				"Password request", JOptionPane.QUESTION_MESSAGE);
			} while (np == null || (np != null && np.length() == 0));
			mailProps.setProperty(RECV_PASS, np);
		}

		// Dump them all into System.properties so other code can find.
		System.getProperties().putAll(mailProps);

		// Construct the GUI
		// System.out.println("Constructing GUI");
		setLayout(new BorderLayout());
		JTabbedPane tbp = new JTabbedPane();
		add(BorderLayout.CENTER, tbp);
		tbp.addTab("Reading", mrb = new MailReaderBean());
		tbp.addTab("Sending", mcb = new MailComposeFrame());
		add(BorderLayout.SOUTH, quitButton = new JButton("Exit")); 
		// System.out.println("Leaving Constructor");
	}

	/** "main program" method - run the program */
	public static void main(String[] av) throws Exception {

		final JFrame f = new JFrame("MailClient");

		// Start by checking that the javax.mail package is installed!
		try {
			Class.forName("javax.mail.Session");
		} catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(f, 
				"Sorry, the javax.mail package was not found\n(" + cnfe + ")",
				"Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// create a MailClient object
		MailClient comp;
		if (av.length == 0)
			comp = new MailClient();
		else
			comp = new MailClient(av[0]);
		f.getContentPane().add(comp);

		// Set up action handling for GUI
		comp.quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setVisible(false);
				f.dispose();
				System.exit(0);
			}
		});

		// Set bounds. Best at 800,600, but works at 640x480
		// f.setLocation(140, 80);
		// f.setSize    (500,400);
		f.pack();

		f.setVisible(true);
	}
}
