import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/** Standalone MailClient GUI application.
 * @author	Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class MailClient extends JComponent {
	/** The quit button */
	JButton quitButton;
	/** The read mode */
	MailReaderBean mrb;
	/** The send mode */
	MailComposeFrame mcb;

	/** Construct the MailClient JComponent by creating its GUI */
	public MailClient() throws Exception {
		super();
		setLayout(new BorderLayout());
		JTabbedPane tbp = new JTabbedPane();
		add(BorderLayout.CENTER, tbp);
		tbp.addTab("Reading", mrb =
			new MailReaderBean("mbox", "localhost", "", "", "/var/mail/ian"));
		tbp.addTab("Sending", mcb = new MailComposeFrame());
		add(BorderLayout.SOUTH, quitButton = new JButton("Exit")); 
	}

	/** "main program" method - construct and show */
	public static void main(String av[]) throws Exception {

		// create a MailClient object
		final JFrame f = new JFrame("MailClient");
		MailClient comp = new MailClient();
		f.getContentPane().add(comp);

		// Load customization properties
		Properties sp = System.getProperties();
		sp.load(new FileInputStream("MailClient.properties"));

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
		f.setLocation(140, 80);
		f.setSize    (500,400);

		f.setVisible(true);
	}
}
