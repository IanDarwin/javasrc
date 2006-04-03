package gui;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FontChooserDemo {

	/** Simple main program to start it running */
	public static void main(String[] args) {
		final JFrame f = new JFrame("FontChooser Startup");
		final FontChooser fc = new FontChooser(f);
		final Container cp = f.getContentPane();
		cp.setLayout(new GridLayout(0, 1));	// one vertical column

		JButton theButton = new JButton("Change font");
		cp.add(theButton);

		final JLabel theLabel = new JLabel("Java is great!", JLabel.CENTER);
		cp.add(theLabel);

		theButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fc.setVisible(true);
				Font myNewFont = fc.getSelectedFont();
				System.out.println("You chose " + myNewFont);
				theLabel.setFont(myNewFont);
				f.pack();		// adjust for new size
				fc.dispose();
			}
		});

		f.setSize(150, 100);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
