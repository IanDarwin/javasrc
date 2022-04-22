package linuxj;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/** This is another ButtonDemo Application
 */
public class ButtonDemo2 extends JFrame {

	private static final long serialVersionUID = 1L;
	JButton b1, b2;

	/** Construct a ButtonDemo2 */
	public ButtonDemo2() {
			super("Linux Journal Button Demo");
			Container cp = getContentPane();
			cp.setLayout(new FlowLayout());

			// An Inner Class for implementing the action listeners 
			class MyActionListener implements ActionListener {
				String buttonMessage;
				public void actionPerformed(ActionEvent event) {
					System.out.println("Thanks for pushing " + buttonMessage);
					System.exit(0);
				}
				MyActionListener(String msg) {
					buttonMessage = msg;
				}
			}
			cp.add(b1 = new JButton("Button 1"));
			b1.addActionListener(new MyActionListener("Button 1"));
			cp.add(b2 = new JButton("Button 2"));
			b2.addActionListener(new MyActionListener("Button 2"));

			pack();
	}
 
	public static void main(String[] av) {
	    new ButtonDemo2().setVisible(true);
	}
}
