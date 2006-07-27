package email;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.darwinsys.io.TextAreaOutputStream;

/** GUI for TestOpenMailRelay, lets you run it multiple times in one JVM
 * to avoid startup delay.
 *
 * Starts each invocation in its own Thread for faster return to ready state.
 *
 * Uses TextAreaWriter to capture program into a window.
 */
public final class TestOpenMailRelayGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String unused[]) throws IOException {
		new TestOpenMailRelayGUI().setVisible(true);
	}

	/** The one-line textfield for the user to type Host name/IP */
	protected JTextField hostTextField;
	/** The push button to start a test; a field so can disable/enable it. */
	protected JButton goButton;
	/** Multi-line text area for results. */
	protected JTextArea results;
	/** The piped stream for the main class to write into "results" */
	protected PrintStream out;
	/** The piped stream to read from "ps" into "results" */
	protected BufferedReader iis;

	/** This inner class is the action handler both for pressing
	 * the "Try" button and also for pressing <ENTER> in the text
	 * field. It gets the IP name/address from the text field
	 * and passes it to process() in the main class. Run in the
	 * GUI Dispatch thread to avoid messing the GUI. -- tmurtagh.
	 */
	final ActionListener runner;
	/** Construct a GUI and some I/O plumbing to get the output
	 * of "TestOpenMailRelay" into the "results" textfield.
	 */
	public TestOpenMailRelayGUI() throws IOException {
		super("Tests for Open Mail Relays");

		runner = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				goButton.setEnabled(false);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						String host = hostTextField.getText().trim();
						out.println("Trying " + host);
						TestOpenMailRelay.process(host, out);
						goButton.setEnabled(true);
					}
				});
			}
		};

		JPanel p;
		Container cp = getContentPane();
		cp.add(BorderLayout.NORTH, p = new JPanel());

		// The entry label and text field.
		p.add(new JLabel("Host:"));
		p.add(hostTextField = new JTextField(10));
		hostTextField.addActionListener(runner);

		p.add(goButton = new JButton("Try"));
		goButton.addActionListener(runner);

		JButton cb;
		p.add(cb = new JButton("Clear Log"));
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				results.setText("");
			}
		});
		JButton sb;
		p.add(sb = new JButton("Save Log"));
		sb.setEnabled(false);

		results = new JTextArea(20, 60);
		// Add the text area to the main part of the window (CENTER).
		// Wrap it in a JScrollPane to make it scroll automatically.
		cp.add(BorderLayout.CENTER, new JScrollPane(results));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();			// end of GUI portion

		out = new PrintStream(new TextAreaOutputStream(results));
		
		
	}
}
