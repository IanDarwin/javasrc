import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/** GUI for TestOpenMailRelay, lets you run it multiple times in one JVM
 * to avoid startup delay.
 *
 * Starts each in its own Thread for faster return to ready state.
 *
 * Uses PipedI/OStreams to capture system.out/err into a window.
 */
public class TestOpenMailRelayGUI extends JFrame {

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
	protected PrintStream ps;
	/** The piped stream to read from "ps" into "results" */
	protected BufferedReader iis;

	/** This inner class is the action handler both for pressing
	 * the "Try" button and also for pressing <ENTER> in the text
	 * field. It gets the IP name/address from the text field
	 * and passes it to process() in the main class. Run in the
	 * GUI Dispatch thread to avoid messing the GUI. -- tmurtagh.
	 */
	ActionListener runner = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			goButton.setEnabled(false);
			SwingUtilities.invokeLater(
				new Thread() {
					public void run() {
						String host = hostTextField.getText().trim();
						ps.println("Trying " + host);
						TestOpenMailRelay.process(host, ps);
						goButton.setEnabled(true);
					}
				});
		}
	};

	/** Construct a GUI and some I/O plumbing to get the output
	 * of "TestOpenMailRelay" into the "results" textfield.
	 */
	public TestOpenMailRelayGUI() throws IOException {
		super("Tests for Open Mail Relays");
		PipedInputStream is;
		PipedOutputStream os;
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

		// Create a pair of Piped Streams.
		is = new PipedInputStream();
		os = new PipedOutputStream(is);

		iis = new BufferedReader(new InputStreamReader(is, "ISO8859_1"));
		ps = new PrintStream(os);

		// Construct and start a Thread to copy data from "is" to "os".
		new Thread() {
			public void run() {
				try {
					String line;
					while ((line = iis.readLine()) != null) {
						results.append(line);
						results.append("\n");
					}
				} catch(IOException ex) {
					JOptionPane.showMessageDialog(null,
						"*** Input or Output error ***\n" + ex,
						"Error",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		}.start();
	}
}
