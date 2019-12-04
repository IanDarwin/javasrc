package gui;

import java.awt.Button;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * Simple Save Dialog demo.
 */
public class SaveDialog extends JFrame {
	boolean unsavedChanges = false;
	Button quitButton;

	/** "main program" method - construct and show */
	public static void main(String[] av) {
		// create a SaveDialog object, tell it to show up
		new SaveDialog().setVisible(true);
	}

	/** Construct the object including its GUI */
	public SaveDialog() {
		super("SaveDialog");
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(new Label("Press this button to see the Quit dialog: "));
		cp.add(quitButton = new Button("Quit"));
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("In Exit Button's action handler");
				if (okToQuit()) {
					setVisible(false);
					dispose();
					System.exit(0);
				}
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		pack();
	}
	boolean okToQuit() {
		String[] choices = {
			"Yes, Save and Quit", "No, Quit without saving", "CANCEL"
		};
		int result = JOptionPane.showOptionDialog(this,
			"You have unsaved changes. Save before quitting?", "Warning",
			JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, 
			null, choices, choices[0]);

			// Use of "null" as the Icon argument is contentious... the
			// document says you can pass null, but it does seem to
			// generate a lot of blather if you do, something about
			// a NullPointerException :-) ...

		if (result >= 0)
			System.out.println("You clicked " + choices[result]);

		switch(result) {
		case -1:
			System.out.println("You killed my die-alog - it died");
			return false;
		case 0:	// save and quit
			System.out.println("Saving...");
			// mainApp.doSave();
			return true;
		case 1:	// just quit
			return true;
		case 2:	// cancel
			return false;
		default:
			throw new IllegalArgumentException("Unexpected return " + result);
		}
	}
}
