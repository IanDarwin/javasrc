package gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;

/**
 * Demonstrate the Swing "Spinner" control.
 * @author ian
 */
// BEGIN main
public class SpinnerDemo {

	public static void main(String[] args) {
		JFrame jf = new JFrame("It Spins");
		Container cp = jf.getContentPane();
		cp.setLayout(new GridLayout(0,1));

		// Create a JSpinner using one of the pre-defined SpinnerModels
		JSpinner dates = new JSpinner(new SpinnerDateModel());
		cp.add(dates);

		// Create a JSPinner using a SpinnerListModel. 
		String[] data = { "One", "Two", "Three" };
		JSpinner js = new JSpinner(new SpinnerListModel(data));
		cp.add(js);

		jf.setSize(100, 80);
		jf.setVisible(true);
	}
}

// END main
