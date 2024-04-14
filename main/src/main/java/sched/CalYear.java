package sched;

// XXX need to move "selection" logic from CalMonth to Cal

import javax.swing.JPanel;

/** This CalYear is all prototype, and surely sucks performance-wise.
 */
public class CalYear extends JPanel {
	private static final long serialVersionUID = 1;
	CalYear() {
		// setLayout(new GridLayout(3, 4));
		// for (int i=0; i<12; i++)
			// add(new CalMonth(1998, i, 0));
	}

	public String toString() {
		return getClass().getName() + '@' + hashCode();
	}
}
