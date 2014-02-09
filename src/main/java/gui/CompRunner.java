package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

// BEGIN main
/** 
 * CompRunner -- Component Runner.
 * A generic main program, for testing a Component-based GUI class that 
 * has a no-argument constructor. This seemed easier than adding a trivial
 * main program to every GUI component that I ever wrote...
 * @author	Ian F. Darwin, http://darwinsys.com/
 */
public class CompRunner {

	/** The component being displayed. */
	static Component comp = null;

	/** "main program" method - construct and show */
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		if (args.length == 0) {
			System.err.println("Usage: CompRunner ComponentSubclass");
			System.exit(1);
		}
		String className = args[0];

		// create an instance of class named in "className", save in "Component comp".
		Class<Component> clazz = null;
		try {
			clazz = (Class<Component>) Class.forName(className);
		} catch (Exception e) {
			System.err.println("ERROR: " + className + " not valid; probably not on CLASSPATH");
			System.exit(1);
		}
		try {
			Object o = clazz.newInstance();
			if (!(o instanceof Component)) {
				System.err.println("ERROR: Class " + className +
					" is not a subclass of Component");
				System.exit(1);
			}
			comp = (Component)o;
		} catch (Exception e) {
			System.err.println(className + " got exception in construction or initialization");
			System.err.println(e.toString());
			System.exit(1);
		}

		// Java GUI events are not threadsafe, so start the GUI on the Event Thread
        EventQueue.invokeLater(new Runnable() {
			public void run() {
				// create a Frame, and "Component comp" to it.
				final JFrame f = new JFrame("CompRunner: " + args[0]);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Container cp = f.getContentPane();
				cp.add(BorderLayout.CENTER, comp);		// Add the component under test

				// Set things reasonably sized.
				Dimension d = comp.getPreferredSize();
				if (d.width == 0 || d.height == 0) {
					// component doesn't have working getPreferredSize() yet, pick a size.
					f.setSize(300, 200);
				} else {
					f.pack();
				}
				f.setLocation(200, 200);
				f.setVisible(true);
			}
		});
	}
}
// END main
