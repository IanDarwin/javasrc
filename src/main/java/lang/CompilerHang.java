// This causes the 1.1.3 and 1.1.4 (at least) compiler to HANG indefinitely 
// while compiling the first ActionListener (see ### comment below).
// It compiles under other compilers such as "pc", and works,
// and it sure looks like valid Java to me.

import java.awt.*;
import java.awt.event.*;

// Dummy version so we can compile this standalone
class SMTPException extends Exception { }

// Dummy version so we can compile this standalone
class SMTPTalk {
	SMTPTalk(String server) {
	}
	void converse(String from, String to, String subj, String msg) {
	}
}

/** CompilerHang - Mail gather and send object.
 *
 * Puts up a mail compose window with a Send button.
 * If user clicks on it, tries to send the mail to a Mail Server
 * (currently via SMTP) for delivery anywhere on the Internet.
 *
 * @author Ian F. Darwin
 */
public class CompilerHang extends Frame {
	private Button sendButton, cancelButton;
	private TextArea msgText;		// The message!

	// The To, Subject, and CC lines are treated a bit specially,
	// any user-defined headers are just put in the tfs array.
	private TextField tfs[], toTF, ccTF, subjectTF;
	// tfsMax MUST == how many are current, for focus handling to work
	private int tfsMax = 3;
	private final int TO = 0, SUBJ = 1, CC = 2, BCC = 3, MAXTF = 8;

	private SMTPTalk st = null;

	/** Constructor for CompilerHang object.
	 *
	 * @param title		Title to display in the titlebar
	 * @param recipient	Email address of recipient
	 * @param server	Network name of SMTP server
	 * @param height	Height of mail compose window
	 * @param width		Width of mail compose window
	 */
	CompilerHang(String title, String recipient, String server,
			int height, int width) throws SMTPException {
		super(title);

		setLayout(new BorderLayout());

		// Top is a Panel for name, address, etc.
		// Centre is the TextArea.
		// Bottom is a panel with Send and Cancel buttons.
		Panel tp = new Panel();
		tp.setLayout(new GridLayout(3,2));
		add("North", tp);

		tfs = new TextField[MAXTF];

		tp.add(new Label("To: ", Label.RIGHT));
		tp.add(tfs[TO] = toTF = new TextField(35));
		toTF.setText(recipient);
		toTF.requestFocus();

		tp.add(new Label("Subject: ", Label.RIGHT));
		tp.add(tfs[SUBJ] = subjectTF = new TextField(35));

		tp.add(new Label("Cc: ", Label.RIGHT));
		tp.add(tfs[CC] = ccTF = new TextField(35));

		// Centre is the TextArea
		add("Center", msgText = new TextArea(70, 10));

		// Bottom is the apply/cancel button
		Panel bp = new Panel();
		bp.setLayout(new FlowLayout());
		bp.add(sendButton = new Button("Send"));

		// ### If you comment this part out, it compiles.
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			try {
				handle();
			} catch(Exception err) {
				System.err.println("Error: " + err);
			}
			CompilerHang.this.setVisible(false);
			CompilerHang.this.dispose();
			}
		});

		bp.add(cancelButton = new Button("Cancel"));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		add("South", bp);

		setSize(height,width);
		// pack();

        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
        	// If we do setVisible and dispose, then the Close completes
			setVisible(false);
			dispose();
			}
		});

		// Finally, set up the Mail connection to the server
		st = new SMTPTalk(server);
	}

	//public void clearValues() {
	//	for (int i=0; i<tfs.length; i++)
	//		tfs[i].setText("");
	//}

	/** Do the work: send the mail to the SMTP server */
	private void handle() throws SMTPException {
		String s, recips, subject;
		recips = toTF.getText();
		if (recips.length() == 0)
			throw new IllegalArgumentException("No recipients");
		subject = subjectTF.getText();
		if ((s = ccTF.getText()).length() != 0) {
			recips+=" " + s;
		}

		st.converse("JabaDex User", recips,
			subject, msgText.getText());

	}


	// Simple test case
	public static void main(String[] av) throws SMTPException {
		CompilerHang sm = new CompilerHang("Test Mailer", "server", "ian", 400, 300);
		sm.setVisible(true);
	}
}
