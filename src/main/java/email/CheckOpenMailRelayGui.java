import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** GUI for TestOpenMailRelay, lets you run it multiple times in one JVM.
 * Start each in its own Thread for fast return to ready state.
 */
public class TestMailRelayGUI extends JFrame {

	public static void main(String[] args) {
		new TestMailRelayGUI().setVisible(true);
	}

	JTextField tf;

	public TestMailRelayGUI() {
		super("Mail Relay Test");
		JPanel p;
		getContentPane().add(p = new JPanel());
		p.add(new JLabel("Host:"));
		p.add(tf = new JTextField(10));
		JButton b;
		p.add(b = new JButton("Try"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				new Thread(new Runnable() {
					public void run() {
						String host = tf.getText();
						String[] nargs = { host };
						TestOpenMailRelay.main(nargs);
					}
				}).start();
			}
		});
		pack();
	}
}
		
