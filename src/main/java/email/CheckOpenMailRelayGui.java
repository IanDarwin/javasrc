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

	/** The one-line textfield for the user to type Host name/IP */
	JTextField hostTextField;
	/** Multi-line text area for results. */
	JTextArea results;
	/** The piped stream for the main class to write into "results" */
	PrintStream ps;
	/** The piped stream to read from "ps" into "results" */
	DataInputStream iis;

	/** This inner class is the action handler both for pressing
	 * the "Try" button and also for pressing <ENTER> in the text
	 * field. It gets the IP name/address from the text field
	 * and passes it to process() in the main class.
	 */
	ActionListener runner = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			new Thread(new Runnable() {
				public void run() {
					String host = hostTextField.getText().trim();
					ps.println("Trying " + host);
					TestOpenMailRelay.process(host, ps);
				}
			}).start();
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

		JButton b;
		p.add(b = new JButton("Try"));
		b.addActionListener(runner);

		results = new JTextArea(20, 60);
		// Add the text area to the main part of the window (CENTER).
		// Wrap it in a JScrollPane to make it scroll automatically.
		cp.add(BorderLayout.CENTER, new JScrollPane(results));

		pack();			// end of GUI portion

		// Create a pair of Piped Streams.
		is = new PipedInputStream();
		os = new PipedOutputStream(is);

		iis = new DataInputStream(is);
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
						results.append("\n");
						results.append("*** Input or Output error ***\n");
						results.append(ex.toString());
						return;
				}
			}
		}.start();
	}
}
