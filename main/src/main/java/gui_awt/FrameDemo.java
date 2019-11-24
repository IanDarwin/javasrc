package gui_awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Make a Frame with two Panels.
 */
public class FrameDemo extends Frame {
	/** "main program" method - construct and show */
	public static void main(String[] av) {
		new FrameDemo().setVisible(true);
	}

	/** Construct the object including its GUI */
	public FrameDemo() {
		setTitle("FrameDemo");
		Panel top_frame = new Panel();
		top_frame.add(new Label("User"));
		top_frame.add(new TextField(10));
		top_frame.add(new Label("Password"));
		top_frame.add(new TextField(10));
		add(top_frame, BorderLayout.NORTH);
		Panel bottom_frame = new Panel();
		Button ok;
		bottom_frame.add(ok = new Button("OK"));
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});

		bottom_frame.add(new Button("Cancel"));
		add(bottom_frame, BorderLayout.SOUTH);
		pack();
	}
}
