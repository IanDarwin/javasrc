import java.io.*;
import javax.comm.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Choose a port, any port!
 *
 * Java Communications is a "standard extention" and must be downloaded
 * and installed separately from the JDK before you can even compile this 
 * program.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @version	$Id$
 */
public class Chooser extends JFrame implements ItemListener() {
	/** The JComboBox for serial ports */
	JComboBox jcs;
	/** The JComboBox for parallel ports */
	JComboBox jcp;
	/** The SerialPort object */
	SerialPort ttya;

	public static void main(String ap[]) {
		new Chooser().setVisible(true);
	}

	/** Constructing a Chooser consists of making the GUI and
	 * populating the ComboBoxes.
	 */
	public Chooser() {
		makeGUI();
		populate();
	}

	/** Build the GUI. You can ignore this for now if you have not
	 * yet worked through the GUI chapter. Your mileage may vary.
	 */
	protected void makeGUI() {
		Container cp = getContentPane();

		cp.add(new JLabel("Parallel Ports");
		jcs = new JComboBox();
		cp.add(jcs);
		jcs.addItemListener(this);

		cp.add(new JLabel("Serial Ports");
		jcp = new JComboBox();
		cp.add(jcp);
		jcp.addItemListener(this);
	}

	protected void populate() {
		// get list of ports available on this particular computer,
		// by calling static method in CommPortIdentifier.
		Enumeration pList = CommPortIdentifier.getPortIdentifiers();

		// Process the list, putting serial and parallel into ComboBoxes
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
			System.out.println("Port " + cpi.getName());
			if (// is serial) {
				jcs.setEnabled(true);
				jcs.addItem(cpi);
			} else if (// is parallel) {
				jcp.setEnabled(true);
				jcp.addItem(cpi);
			}
		}
	}

	/** This will be called from either of the Choosers when the
	 * user selects any given item.
	 */
	public void itemStateChanged(ItemEvent e) {
		System.out.println("You chose " + e.getSource());
	}
}
