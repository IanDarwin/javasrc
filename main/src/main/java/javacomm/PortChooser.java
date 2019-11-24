package javacomm;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.HashMap;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Choose a port, any port!
 *
 * Java Communications is a "standard extention" and must be downloaded
 * and installed separately from the JDK before you can even compile this 
 * program.
 *
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class PortChooser extends JDialog implements ItemListener {
	private static final long serialVersionUID = -7091059841411129363L;
	/** A mapping from names to CommPortIdentifiers. */
	protected HashMap<String, CommPortIdentifier> map = new HashMap<String, CommPortIdentifier>();
	/** The name of the choice the user made. */
	protected String selectedPortName;
	/** The CommPortIdentifier the user chose. */
	protected CommPortIdentifier selectedPortIdentifier;
	/** The JComboBox for serial ports */
	protected JComboBox serialPortsChoice;
	/** The JComboBox for parallel ports */
	protected JComboBox parallelPortsChoice;
	/** The JComboBox for anything else */
	protected JComboBox other;
	/** The SerialPort object */
	protected SerialPort ttya;
	/** To display the chosen */
	protected JLabel choice;
	/** Padding in the GUI */
	protected final int PAD = 5;

	/** This will be called from either of the JComboBoxen when the
	 * user selects any given item.
	 */
	public void itemStateChanged(ItemEvent e) {
		// Get the name
		selectedPortName = (String)((JComboBox)e.getSource()).getSelectedItem();
		// Get the given CommPortIdentifier
		selectedPortIdentifier = map.get(selectedPortName);
		// Display the name.
		choice.setText(selectedPortName);
	}

	/* The public "getter" to retrieve the chosen port by name. */
	public String getSelectedName() {
		return selectedPortName;
	}

	/* The public "getter" to retrieve the selection by CommPortIdentifier. */
	public CommPortIdentifier getSelectedIdentifier() {
		return selectedPortIdentifier;
	}

	/** A test program to show up this chooser. */
	public static void main(String[] ap) {
		PortChooser c = new PortChooser(null);
		c.setVisible(true);	// blocking wait
		System.out.println("You chose " + c.getSelectedName() +
			" (known by " + c.getSelectedIdentifier() + ").");
		System.exit(0);
	}

	/** Construct a PortChooser --make the GUI and populate the ComboBoxes.
	 */
	public PortChooser(JFrame parent) {
		super(parent, "Port Chooser", true);

		makeGUI();
		populate();
		finishGUI();
	}

	/** Build the GUI. You can ignore this for now if you have not
	 * yet worked through the GUI chapter. Your mileage may vary.
	 */
	protected void makeGUI() {
		Container cp = getContentPane();

		JPanel centerPanel = new JPanel();
		cp.add(BorderLayout.CENTER, centerPanel);

		centerPanel.setLayout(new GridLayout(0,2, PAD, PAD));

		centerPanel.add(new JLabel("Serial Ports", JLabel.RIGHT));
		serialPortsChoice = new JComboBox();
		centerPanel.add(serialPortsChoice);
		serialPortsChoice.setEnabled(false);

		centerPanel.add(new JLabel("Parallel Ports", JLabel.RIGHT));
		parallelPortsChoice = new JComboBox();
		centerPanel.add(parallelPortsChoice);
		parallelPortsChoice.setEnabled(false);

		centerPanel.add(new JLabel("Unknown Ports", JLabel.RIGHT));
		other = new JComboBox();
		centerPanel.add(other);
		other.setEnabled(false);

		centerPanel.add(new JLabel("Your choice:", JLabel.RIGHT));
		centerPanel.add(choice = new JLabel());

		JButton okButton;
		cp.add(BorderLayout.SOUTH, okButton = new JButton("OK"));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PortChooser.this.dispose();
			}
		});

	}

	/** Populate the ComboBoxes by asking the Java Communications API
	 * what ports it has.  Since the initial information comes from
	 * a Properties file, it may not exactly reflect your hardware.
	 */
	@SuppressWarnings("unchecked")
	protected void populate() {
		// get list of ports available on this particular computer,
		// by calling static method in CommPortIdentifier.
		Enumeration<CommPortIdentifier> pList = CommPortIdentifier.getPortIdentifiers();

		// Process the list, putting serial and parallel into ComboBoxes
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = pList.nextElement();
			// System.out.println("Port " + cpi.getName());
			map.put(cpi.getName(), cpi);
			if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				serialPortsChoice.setEnabled(true);
				serialPortsChoice.addItem(cpi.getName());
			} else if (cpi.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
				parallelPortsChoice.setEnabled(true);
				parallelPortsChoice.addItem(cpi.getName());
			} else {
				other.setEnabled(true);
				other.addItem(cpi.getName());
			}
		}
		serialPortsChoice.setSelectedIndex(-1);
		parallelPortsChoice.setSelectedIndex(-1);
	}

	protected void finishGUI() {
		serialPortsChoice.addItemListener(this);
		parallelPortsChoice.addItemListener(this);
		other.addItemListener(this);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
