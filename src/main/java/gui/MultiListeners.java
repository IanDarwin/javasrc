import java.awt.event.*;
import javax.swing.*;

/** Demo I wrote in response to a student request for how to have
 * multiple listeners (Bobs) that are already on-screen added as listeners
 * to a newly-created pushbutton (Fred).
 */
public class MultiListeners {
	public static void main(String[] args) { 
		Bob b1 = new Bob();
		b1.setVisible(true);
		Bob b2 = new Bob();
		b2.setVisible(true);
		ActionListener[] bobs = { b1, b2};

		Fred f1 = new Fred(bobs);
		f1.setVisible(true);
	}
}
class Fred extends JFrame {
	JButton okbutton = new JButton("OK");
	public Fred(ActionListener[] bobs) {
		getContentPane().add(okbutton);
		for (int i=0; i<bobs.length; i++) {
			okbutton.addActionListener(bobs[i]);
		}
	}
}
class Bob extends JFrame implements ActionListener {
	protected JLabel statusLabel = new JLabel("     ");
	public Bob() {
		getContentPane().add(statusLabel);
	}
	public void actionPerformed(ActionEvent e) {
		statusLabel.setText("OK");
	}
}
