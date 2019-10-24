package gui_awt;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileDialogDemo extends Frame {
	FileDialog fc;

	/** Construct a FileDialogDemo */
	FileDialogDemo() {
		super("FileDialogDemo");
		setSize(200, 200);

		// Construct, but don't show, the file dialog.
		fc = new FileDialog(this, "Choose a file", FileDialog.LOAD);
		// Set its starting directory to the root, not where we run it
		// (just to show that there are methods you can call on it).
		fc.setDirectory("/");

		Button b;
		add(b = new Button("Browse..."));	// Create and add a Button
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Make the dialog appear, and wait for it.
				fc.setVisible(true);
				// If we get here, user either chose a file or did CANCEL.
				String fn = fc.getFile();
				if (fn == null)
					System.out.println("You cancelled the choice");
				else
					System.out.println("You chose " + fn);
			}
		});
	}

	/** main test program: construct the Frame and show it */
	public static void main(String[] a) {
		new FileDialogDemo().setVisible(true);
	}
}
