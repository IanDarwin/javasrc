import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** GUI for TestOpenMailRelay, lets you run it multiple times in one JVM.
 * Starts each in its own Thread for faster return to ready state.
 */
public class TestOpenMailRelayGUI extends JFrame {

	JTextField tf;

	ActionListener runner = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			new Thread(new Runnable() {
				public void run() {
					String host = tf.getText().trim();
					TestOpenMailRelay.process(host);
				}
			}).start();
		}
	});

	public TestOpenMailRelayGUI() {
		super("Tests for Open Mail Relays");
		JPanel p;
		getContentPane().add(p = new JPanel());
		p.add(new JLabel("Host:"));
		p.add(tf = new JTextField(10));
		tf.addActionListener(runner);
		JButton b;
		p.add(b = new JButton("Try"));
		b.addActionListener(runner);

		// This inner class is the action handler both for pressing
		// the "Try" button and also for pressing <ENTER> in the text
		// field. It gets the IP name/address from the text field
		// and passes it to process() in the main class.

		pack();
	}
}
