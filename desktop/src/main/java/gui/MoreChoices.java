package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** Demo to show a way of having "More Choices/Less Choices"
 * in a pop-up window. The secret is to call pack() again
 * each time you add/subtract the bottom panel.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class MoreChoices extends JFrame {
	Container cp;
	boolean unsavedChanges = false;
	JButton moreOrLessButton;
	JPanel  moreOrLessPanel;
	ActionListener more;
	ActionListener less;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a MoreChoices object, tell it to show up
		JFrame jf = new MoreChoices();
		jf.setLocation(100, 100);	// get away from screen corner,
							// since on some OSes a main window at 0,0 may be
							// partly obscured (e.g. notebook with PowerPanel
		jf.setVisible(true);
	}

	/** Construct the object including its GUI */
	public MoreChoices() {
		super("More Choices");
		// cp = getContentPane();
		cp = this;
		cp.setLayout(new BorderLayout());

		ButtonsPanel bp = new ButtonsPanel();
		cp.add(BorderLayout.NORTH, bp);

		// Construct the more/less switcher
		less = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cp.remove(moreOrLessPanel);
				pack();
				moreOrLessButton.setText("More Choices");
				moreOrLessButton.removeActionListener(less);
				moreOrLessButton.addActionListener(more);
			}
		};
		more = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cp.add(BorderLayout.SOUTH, moreOrLessPanel);
				pack();
				moreOrLessButton.setText("Fewer Choices");
				moreOrLessButton.removeActionListener(more);
				moreOrLessButton.addActionListener(less);
			}
		};
		bp.add(moreOrLessButton = new JButton("More Choices"));
		// Initial state is to add more choices
		moreOrLessButton.addActionListener(more);

		moreOrLessPanel = new ChoicesPanel();

		// Finally a frame closer
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
			
		pack();
	}

	/** The panel that appears when you press More Choices.
	 * This is a toy; in a real application, this would likely
	 * be a separate full "public class".
	 */
	class ChoicesPanel extends JPanel {
		ChoicesPanel() {
			setBorder(BorderFactory.createEtchedBorder());
			add(new JCheckBox("Happiness"));
			add(new JCheckBox("Satisfaction"));
			add(new JCheckBox("Contentment"));
		}
	}

	/** The Panel that contains the More/Less button. It is just
	 * here to override getPreferredSize so that we can
	 * avoid "jitter" (i.e., the width changing); i.e., we must
	 * ensure that the main panel and the ChoicePanel have the
	 * same width
	 */
	class ButtonsPanel extends JPanel {
		public Dimension getPreferredSize() {
			// System.out.println("In ButtonsPanel.getPreferredSize()");
			// For height, use our normal height
			int dHeight = moreOrLessButton.getPreferredSize().height + 5 + 5;
			// For witdh, use the included Panel's width
			int dWidth  = moreOrLessPanel.getPreferredSize().width;
			// Combine them; that's the result we need.
			return new Dimension(dWidth, dHeight);
		}
	}
}
