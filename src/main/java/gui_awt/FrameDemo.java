import java.awt.*;
import java.awt.event.*;

/** Make a Frame with two Panels.
 * @version $Id$
 */
public class FrameDemo extends Frame {
	/** "main program" method - construct and show */
	public static void main(String av[]) {
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
		add("North", top_frame);
		Panel bottom_frame = new Panel();
		bottom_frame.add(new Button("OK"));
		bottom_frame.add(new Button("Cancel"));
		add("South", bottom_frame);
		pack();
	}
}
