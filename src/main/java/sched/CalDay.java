package sched;

// XXX need to move "selection" logic from CalMonth to Cal

import javax.swing.*;
import java.awt.GridLayout;

/** This CalDay is just a prototype!!
 */
public class CalDay extends JPanel implements DateSelectedListener {
	CalDay() {
		setLayout(new GridLayout(0, 1));
		for (int i=9; i<=17; i++) 
			add(new HourView(i));
	}
	/** An Inner class to display one Hour view. */
	class HourView extends JPanel {
		HourView(int h) {
			add(new JLabel(h + ":00", JLabel.RIGHT));
			add(new JTextField(30));
		}
	}
	/** Called from other sources when the date changes. */
	public void dateSelected(DateSelectedEvent dse) {
		// nothing to do with it yet
	}
	/** Show this CalDay as a String */
	public String toString() {
		return getClass().getName() + '@' + hashCode();
	}

}
