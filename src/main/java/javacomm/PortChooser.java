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
public class Chooser extends JFrame implements ItemListener {
	/** The JComboBox for serial ports */
	protected JComboBox jcs;
	/** The JComboBox for parallel ports */
	protected JComboBox jcp;
	/** The JComboBox for anything else */
	protected JComboBox other;
	/** The SerialPort object */
	protected SerialPort ttya;
	/** To display the chosen */
	protected JLabel choice;
	/** Padding in the GUI */
	protected final int PAD = 5;

	public static void main(String ap[]) {
		new Chooser().setVisible(true);
	}

	/** Construct a Chooser --make the GUI and populate the ComboBoxes.
	 */
	public Chooser() {
		super("Chooser");

		makeGUI();
		populate();
		finishGUI();
	}

	/** Build the GUI. You can ignore this for now if you have not
	 * yet worked through the GUI chapter. Your mileage may vary.
	 */
	protected void makeGUI() {
		Container cp = getContentPane();

		cp.setLayout(new GridLayout(0,2, PAD, PAD));

		cp.add(new JLabel("Serial Ports", JLabel.RIGHT));
		jcs = new JComboBox();
		cp.add(jcs);
		jcs.setEnabled(false);

		cp.add(new JLabel("Parallel Ports", JLabel.RIGHT));
		jcp = new JComboBox();
		cp.add(jcp);
		jcp.setEnabled(false);

		cp.add(new JLabel("Unknown Ports", JLabel.RIGHT));
		other = new JComboBox();
		cp.add(other);
		other.setEnabled(false);

		cp.add(new JLabel("Your choice:", JLabel.RIGHT));
		cp.add(choice = new JLabel());
	}

	protected void populate() {
		// get list of ports available on this particular computer,
		// by calling static method in CommPortIdentifier.
		Enumeration pList = CommPortIdentifier.getPortIdentifiers();

		// Process the list, putting serial and parallel into ComboBoxes
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
			// System.out.println("Port " + cpi.getName());
			if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				jcs.setEnabled(true);
				jcs.addItem(cpi.getName());
			} else if (cpi.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
				jcp.setEnabled(true);
				jcp.addItem(cpi.getName());
			} else {
				other.setEnabled(true);
				other.addItem(cpi.getName());
			}
		}
	}

	protected void finishGUI() {
		jcs.addItemListener(this);
		jcp.addItemListener(this);
		other.addItemListener(this);
		pack();
		addWindowListener(new WindowCloser(this, true));
	}

	/** This will be called from either of the Choosers when the
	 * user selects any given item.
	 */
	public void itemStateChanged(ItemEvent e) {
		choice.setText((String)((JComboBox)e.getSource()).getSelectedItem());
	}
}
