package i18n;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.darwinsys.swingui.I18N;

/**
 * I18N'd JOptionPane
 * @author Ian Darwin
 */
// tag::main[]
public class JOptionDemo extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ResourceBundle rb;

	// Constructor
	JOptionDemo(String s) {
		super(s);

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());

		rb = ResourceBundle.getBundle("Widgets");

		JButton b = I18N.mkButton(rb, "getButton");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
					JOptionDemo.this,
					rb.getString("dialog1.text"),
					rb.getString("dialog1.title"),
					JOptionPane.INFORMATION_MESSAGE);
			}
		});
		cp.add(b);

		b = I18N.mkButton(rb, "goodbye");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cp.add(b);

		// the main window
		setSize(200, 150);
		pack();
	}

	public static void main(String[] arg) {
		JOptionDemo x = new JOptionDemo("Testing 1 2 3...");
		x.setVisible(true);
	}
}
// end::main[]
