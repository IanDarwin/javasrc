package linuxj;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** This is a ButtonDemo Application.
 */
public class ButtonDemo1 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton b1;

	/** Construct a ButtonDemo1 */
	public ButtonDemo1() {
			super("Linux Journal Button Demo");
			getContentPane().add(b1 = new JButton("A button"));
			b1.addActionListener(this);
			pack();
	}
 
	public void actionPerformed(ActionEvent event) {
	    System.exit(0);
	}

	public static void main(String[] av) {
	    new ButtonDemo1().setVisible(true);
	}
}
