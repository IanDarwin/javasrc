import java.awt.event.*;
import javax.swing.*;

public class AllClasses {
	/** Inner class can be used anywhere in this file */
	public class Data {
		int x;
		int y;
	}
	public void getResults() {
		JButton b = new JButton("Press me");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("Thanks for pressing me");
			}
		});
	}
}

/** Class contained in same file as AllClasses, but can be used
 * (with a warning) in other contexts.
 */
class AnotherClass {
	// methods and fields here...
}
