package linuxj;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** This is a longer ButtonDemo Application
 * that shows UI switching.
 */
public class ButtonDemo3 extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JTextArea tf;

	/** Construct a ButtonDemo3 */
	public ButtonDemo3() {
		super("Linux Journal Button Demo");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		tf = new JTextArea(2, 40);
		cp.add(tf);

		// An Inner Class for implementing the action listeners 
		class SwitchActionListener implements ActionListener {
			String lnfName;
			public void actionPerformed(ActionEvent event) {
				tf.setText("Thanks for requesting " + lnfName + "\n");
	
				try {
					UIManager.setLookAndFeel(lnfName);
					SwingUtilities.updateComponentTreeUI(ButtonDemo3.this);
					pack();
				} catch (Exception exc) {
					tf.append("could not load LookAndFeel: " + lnfName);
				}
			}
			SwitchActionListener(String msg) {
				lnfName = msg;
			}
		}

		ButtonGroup cbg = new ButtonGroup();

		JRadioButton myButton;
		
		cp.add(myButton = new JRadioButton("Metal", false));
		myButton.addActionListener(new SwitchActionListener(
					"javax.swing.plaf.metal.MetalLookAndFeel"));
		cbg.add(myButton);

		cp.add(myButton = new JRadioButton("Motif", false));
		myButton.addActionListener(new SwitchActionListener(
					"com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
		cbg.add(myButton);

		cp.add(myButton = new JRadioButton("Windows", false));
		myButton.addActionListener(new SwitchActionListener(
					"com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
		cbg.add(myButton);

		// This only works with MacL+F download; maybe later MRJ
		cp.add(myButton = new JRadioButton("Macintosh", false));
		myButton.addActionListener(new SwitchActionListener(
			"com.sun.java.swing.plaf.mac.MacLookAndFeel"));
		cbg.add(myButton);

		pack();

		addWindowListener(new WindowAdapter() {
		 	public void windowClosing(WindowEvent we) {
		 		System.exit(0);
		 	}
		});
	}
 
	public static void main(String[] av) {
	    new ButtonDemo3().setVisible(true);
	}
}
