import com.sun.java.swing.*;
import com.sun.java.swing.text.*;
import com.sun.java.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;

/**
 * CrsSetup -- GUI application to set up 471/478 machines for Learning Tree
 *
 * XXX TODO 
 *	Warn them to delete the unused Hardware Profiles after Apply.
 *	get the current IPNet and MachNum from the registry.
 *	Get the list of interfaces that are IP, only change them.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class CrsSetup extends JFrame {
	/** The version number */
	final static String VERSION = "$Version$";
	/** How many machines to go up to */
	final int NUSERS = 22;
	/** The link to us in the Startup folder, to remove it when done */
	final String STARTUP_LINK_NAME = "CrsSetup.pif";
	/** The full path to us in the Startup folder, to rm it when done */
	final String STARTUP_LINK =
		"C:\\windows\\Start Menu\\Programs\\Startup\\" + STARTUP_LINK_NAME;
	/** The name to write the hosts file into */
	final String HOSTS = "C:\\windows\\hosts";

	// MODEL
	/** The default Course */
	final String defCOURSE = "471";
	/** The current machine name */
	int machNameber = 0;
	/** The default network */
	final String DEFAULT_NET = "200.1.1";
	/** The current network */
	String netName = DEFAULT_NET;

	// VIEW
	/** The contentpane */
	Container cont;
	/** The Apply Button */
	JButton aB;
	/** The Test Button */
	JButton testB;
	/** The Close Button */
	JButton qB;
	/** The Snuff button */
	JButton snuffB;
	/** The unSnuff button */
	JButton unSnuffB;
	/** A titlepane for Layouts */
	JPanel tp;
	/** A Plain Panel for Layouts */
	Panel pp;
	/** The chooser for the Machine ID network */
	JComboBox mach;
	/** The chooser for the IP network */
	JComboBox ip;
	/** A Panel for bottom of main layout */
	Panel p;
	/** A radiobuttongroup, for course number */
	ButtonGroup cbg;
	/** Some radio buttons, also for course number */
	JRadioButton j471, j478;

	// CONTROLLERS are throughout -- look for add...Listener().

	/** "main program" method - construct and show */
	public static void main(String av[]) {
		new CrsSetup().setVisible(true);
	}

	/** Construct the object including its GUI */
	public CrsSetup() {
		super("Java Course 471/478 Configurator " +
			VERSION + "(C) Ian F. Darwin");

		cont = getContentPane();
		cont.setLayout(new FlowLayout());

		// STUFF FOR SELECTING THE COURSE
		tp = new JPanel();
		tp.setBorder(BorderFactory.createTitledBorder("Course"));
		tp.setLayout(new BoxLayout(tp, BoxLayout.Y_AXIS));
		cbg = new ButtonGroup();
		cbg.add(j471 = new JRadioButton("471", true));
		j471.setActionCommand("471");	// redundant??
		j471.setToolTipText("Setup files for LT471, Java Programming");
		j471.doClick();	// make it the default
		cbg.add(j478 = new JRadioButton("478", false));
		j478.setActionCommand("478");	// redundant??
		j478.setToolTipText("Setup files for LT478, Java for C++");
		tp.add(j471);
		tp.add(j478);
		cont.add(tp);

		// STUFF FOR CHOOSING THE NETWORK
		tp = new JPanel();
		tp.setBorder(BorderFactory.createTitledBorder("Network Number"));
		tp.setLayout(new BoxLayout(tp, BoxLayout.Y_AXIS));
		ip = new JComboBox();
		ip.setToolTipText("Set IP network number");
		ip.addItem(DEFAULT_NET);
		ip.addItem("204.92.76");
		ip.addItem("204.92.77");
		ip.addItem("206.138.217");
		ip.addItem("193.122.158");
		ip.setEditable(true);	// allow user to type own value.
		ip.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				netName = e.getItem().toString();
				// System.out.println("IP chosen; Network is " + netName);
			}
		});
		tp.add(ip);
		cont.add(tp);

		// STUFF FOR CHOOSING THE MACHINE
		tp = new JPanel();
		tp.setBorder(BorderFactory.createTitledBorder("This Machine"));
		tp.setLayout(new BoxLayout(tp, BoxLayout.Y_AXIS));
		mach = new JComboBox();
		mach.setToolTipText("Set Machine nameber");
		mach.setEditable(true);	// allow user to type other values.
		// mach.setMaximumSize(new Dimension(Integer.MAX_VALUE,20));
		mach.addItem("-----------");
		for (int i=1; i<=NUSERS; i++)
			mach.addItem(""+i);
		mach.addItem("44");			// instructor-notebook

		mach.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String chosen = e.getItem().toString();
				if (chosen.charAt(0) == '-')
					machNameber = 0;
				else
					machNameber = Integer.parseInt(chosen);
				// machNameber = mach.getSelectedIndex();
			}
		});
		tp.add(mach);
		cont.add(tp);

		// STUFF FOR APPLY/CLOSE BUTTONS
		tp = new JPanel();
		tp.setBorder(BorderFactory.createTitledBorder("What Next?"));
		tp.setLayout(new BoxLayout(tp, BoxLayout.Y_AXIS));

		tp.add(aB = new JButton("Apply"));
		aB.setToolTipText("Make all these changes to the system!");
		aB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doApply(true);	// applies it to this system!!
			}
		});

		tp.add(aB = new JButton("Test Apply"));
		aB.setToolTipText("Make regedit file, but no changes to the system");
		aB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doApply(false);	// applies it to this system!!
			}
		});

		tp.add(snuffB = new JButton("Do not start on Reboot"));
		snuffB.setToolTipText("Remove the Startup link that runs me at reboot");
		snuffB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeStartupLink(true);
			}
		});

		tp.add(unSnuffB = new JButton("Re-set to start on Reboot"));
		unSnuffB.setToolTipText("Re-create the Startup link that runs me at reboot");
		unSnuffB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createStartupLink();
			}
		});

		tp.add(qB = new JButton("Close"));
		qB.setToolTipText("Close the system configurer");
		qB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cont.add(tp);

		// STUFF FOR LOOK-AND-FEEL SWITCHER
		tp = new JPanel();
		tp.setBorder(BorderFactory.createTitledBorder("(U.I.)"));
		tp.setLayout(new BoxLayout(tp, BoxLayout.Y_AXIS));

		// Create two radio buttons for the look-and-feel switcher.
		JRadioButton basicButton = new JRadioButton("Ugly");
		basicButton.setEnabled(false);
		basicButton.addActionListener(new SwitchUIListener(
			"com.sun.java.swing.plaf.organic.OrganicLookAndFeel"));
		basicButton.setToolTipText("Just to make it ugly");
		basicButton.setSelected(false);
		tp.add(basicButton);

		JRadioButton roseButton = new JRadioButton("Metal");
		roseButton.addActionListener(new SwitchUIListener(
			"com.sun.java.swing.plaf.metal.MetalLookAndFeel"));
		roseButton.setToolTipText("Just to make it pretty");
		basicButton.setSelected(true);	// we hope it got set.
		tp.add(roseButton);

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(basicButton);
		group.add(roseButton);

		cont.add(tp);

		// The alternate way out of this program.
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});
			
		pack();
		centre();
	}

	private void createStartupLink() {
		File sl = new File(STARTUP_LINK);
		if (sl.exists()) {
			JOptionPane.showMessageDialog(this,
				"I think I'm already linked there.",
				"Already linked?", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		// copy startup file (/windows/startup or /etc/rc2.d?)
		if (fileCopy(STARTUP_LINK_NAME, STARTUP_LINK))
			JOptionPane.showMessageDialog(this, 
				"OK, see you soon...",
				"Creation succeeded, will run again at reboot",
				JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(this,
				"Tried to create, but failed.",
				"Creation Failed", JOptionPane.WARNING_MESSAGE);
	}

	private void removeStartupLink(boolean chatty) {
			File sl = new File(STARTUP_LINK);
			if (!sl.exists()) {
				if (chatty)
					JOptionPane.showMessageDialog(this,
						"I think I'm already outa here.",
						"Already removed?", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if (sl.delete()) {
				if (chatty)
					JOptionPane.showMessageDialog(this, 
						"OK, goodbye forever...",
						"Removal Succeeded", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(this,
					"Tried to unlink, but failed.",
					"Removal Failed", JOptionPane.WARNING_MESSAGE);
	}

	private void centre() {
		// After packing the Frame, centre it on the screen.
		Dimension us = getSize(), 
			them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		setLocation(newX, newY);
	}

	/** Called to do all the work when the Apply button is pushed.
	 * Apply it, and then terminate the program.
	 */
	void doApply(boolean forRealP){
			ButtonModel t = CrsSetup.this.cbg.getSelection();
			// System.out.println("ButtonModel = " + t);
			String cName = t.getActionCommand();
			if (cName == null) {
				JOptionPane.showMessageDialog(this,
					"ERROR: getActionCommand() returns null!",
					"Internal Error", JOptionPane.ERROR_MESSAGE);
				cName = defCOURSE;
			}

			if (netName == null) {
				JOptionPane.showMessageDialog(CrsSetup.this, 
					"No Network Number chosen", 
					"User Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (machNameber == 0) {
				JOptionPane.showMessageDialog(CrsSetup.this,
					"No Machine \"Nameber\" chosen",
					"User Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			doTheWork(forRealP, cName, netName, machNameber);
	}

	/**
	 * Last stage of processing - do the work.
	 * Called when we have gathered ALL the information in the GUI
	 * Needs to:
	 * <UL>
	 * <LI>Set the Netscape (and other?) Home Pages to crs4XX/index.htm.
	 * <LI>Rewrite the \windows\hosts file using the new network number.
	 * <LI>Munge the .lnk files to start apps like CmdWindow in crs4XX.
	 * <LI>Set the default user name to userN. This is username in
	 * HKEY_LOCAL_MACHINE\Network\Logon\
	 * <LI>Set the machine's identity to machName. This is ComputerName in
	 * HKEY_LOCAL_MACHINE\System\CurrentControlSet\control\ComputerName\ComputerName
	 * <LI>Set the IP address to netName + number portion of machName.
	 * This is IPAddress in 
	 * HKEY_LOCAL_MACHINE\System\CurrentControlSet\Services\Class\NetTrans\0000
	 * on a machine with only one TCP adapter (so don't go installing DialUp!)
	 * We stuff this into 0001 and 0002too, just in case we get a machine 
	 * with more than one (like, SMC and 3C!).
	 * </UL>
	 */
	public void doTheWork(boolean applyForReal, String cName, 
		String netName, int machNum) {
		// System.out.println("Setting Course to " + cName);
		// System.out.println("Setting Network to " + netName);
		// System.out.println("Setting MachineID to " + machNum);

		// Run any course-specific fixups
		doCourseScript(applyForReal, cName);

		// setup the TCP hosts files
		doHostFile(applyForReal, netName, machNum, NUSERS);

		// Update the Registry with computer/networking info.
		// THIS IS FOR WINDOWS 95 AND WILL NOT WORK ON WINDOWS NT
		startReg();
		setReg("HKEY_LOCAL_MACHINE\\Network\\Logon",
			"username", "user"+machNum);
		setReg("HKEY_LOCAL_MACHINE\\System\\CurrentControlSet\\control\\ComputerName\\ComputerName", "ComputerName", "user"+machNum);
		for (int i=0; i<=5; i++) {
			// To format a non-localized four-digit number
			NumberFormat form = new DecimalFormat("0000");
			String thisNum = "0000";
			try {
				thisNum = form.format(i);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(this,
					"Error in DecimalFormat " + iae,
					"Error", JOptionPane.ERROR_MESSAGE);
			}
			setReg("HKEY_LOCAL_MACHINE\\System\\CurrentControlSet\\Services\\Class\\NetTrans\\"+thisNum, "IPAddress", netName+"."+machNum);
			setReg("HKEY_LOCAL_MACHINE\\System\\CurrentControlSet\\Services\\Class\\NetTrans\\"+thisNum, "IPMask", "255.255.255.0");
		}
		endReg(applyForReal);

		if (!applyForReal)
			return;

		// Still here, presume successful, so unlink.
		removeStartupLink(false);

		// Suggest a reboot
		String choices[] = { "Reboot now", "Reboot later" };
		// Due to contention in this pre-beta Swing API:
		class NullIcon implements Icon {
			public void paintIcon(Component c, Graphics g, int x, int y) { }
			public int getIconWidth() { return 16; };
			public int getIconHeight() { return 16; };
		}
		int result = JOptionPane.showOptionDialog(this,
					"You must reboot for network changes to take effect",
					"Reboot now?", JOptionPane.YES_NO_OPTION,
					JOptionPane.INFORMATION_MESSAGE,
					new NullIcon(), choices, choices[0]);
		switch (result) {
			case -1:
				break;
			case 0:
				try {
					Runtime.getRuntime().exec("rebooter");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this,
						"Error running rebooter: " + e,
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 1:
				System.out.println("OK, do it later");
				break;
			default:
				System.out.println("Unexpected return " + result + 
					" from JOptionPane.showOptionDialog()");
		}
		// All done!
	}

	/** Run any course-specific customization script in \build */
	void doCourseScript(boolean applyForReal, String course) {
		String csfn = "C:\\build\\" + course + ".bat";
		File cs = new File(csfn);
		if (cs.exists()) {
			if (applyForReal) {
				try {
					Runtime.getRuntime().exec(csfn);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(this,
						"Error running course script: " + e,
						"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(this,
					"Ran Course script " + csfn,
					"File " + csfn, JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this,
				"Would run Course script " +csfn+ " - ignoring in test mode",
				"File "+csfn, JOptionPane.WARNING_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this,
			"Course script " +csfn+ " not found - ignoring",
			"Error", JOptionPane.WARNING_MESSAGE);
		}
	}

	/** Rewrite the Hosts file with the new network number */
	void doHostFile(boolean applyForReal, String net, int thisNum, int maxNum) {
		PrintWriter hf;
		try {
			if (applyForReal)
				hf = new PrintWriter(new FileWriter(HOSTS));
			else
				hf = new PrintWriter(System.out);
			hf.println("# This host file built by Java CrsSetup ");
			hf.println("127.0.0.1\tlocalhost");
			for (int i=1; i<=maxNum; i++) {
				hf.print(net+"."+i+"\tuser"+i);
				if (i == thisNum)
					hf.print("\tlocal");
				hf.println("");
			}
			hf.println(net+".44\tdaroad user44 instructor-notebook");
			hf.println(net+".50\tserver instructor-server");
			hf.println(net+".51\ttest-server instructor-test-server");
			hf.println("# For testing timeouts in 478 Threaded HandsOn:");
			hf.println(net+".99\terewhon");
			hf.close();
		} catch(IOException e) {
			JOptionPane.showMessageDialog(this,
				"Hosts file " + HOSTS + " write failed " + e,
				"Input-Output Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}

	// Set of q+d routines for setting registry values...
	// Should do native code calls on Registry API. Real Soon Now(tm).

	/** Temporary filename for holding registry data */
	final String REG_TMP = "C:\\build\\system.reg";

	/** File for writing the registry data into */
	PrintWriter reg = null;

	void startReg(){
		try {
			reg = new PrintWriter(new FileWriter(REG_TMP));
		} catch(IOException e) {
			JOptionPane.showMessageDialog(this,
				"Write failed " + REG_TMP + " " + e,
				"Write error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		reg.println("REGEDIT4");
		reg.println("");
	}
	void setReg(String key, String part, String value){
		reg.println("["+key+"]");
		reg.println("\""+part+"\"=\""+value+"\"");
		reg.println("");
	}

	void endReg(boolean applyForReal){
		reg.println("");
		reg.close();
		if (applyForReal)
			try {
				Process p = Runtime.getRuntime().exec("regedit " + REG_TMP);
				p.waitFor();
			} catch(Exception e) {
				JOptionPane.showMessageDialog(this,
					"Running regedit " + REG_TMP + "failed (" + e + ")",
					"RegEdit error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		else
			JOptionPane.showMessageDialog(this,
				"Would run regedit " + REG_TMP,
				"RegEdit Not Run", JOptionPane.INFORMATION_MESSAGE);
	}

    /** An ActionListener that listens to the radio buttons. */
    class SwitchUIListener implements ActionListener {
		String lnfName;
		public SwitchUIListener(String name) {
			lnfName = name;
		}
		public void actionPerformed(ActionEvent e) {

			try {
				UIManager.setLookAndFeel(lnfName);
				SwingUtilities.updateComponentTreeUI(CrsSetup.this);
				CrsSetup.this.pack();
				CrsSetup.this.centre();
			} catch (Exception exc) {
				// Don't care, Look and Feel is not critical to program.
				// System.err.println("could not load LookAndFeel: " + lnfName);
				JOptionPane.showMessageDialog(CrsSetup.this,
					"Change to " + lnfName + " failed",
					"No UI change",
					JOptionPane.WARNING_MESSAGE);
			}
		}
    }

    /** Copy a file from one filename to another */
    public boolean fileCopy(String inName, String outName) {
		try {
			BufferedInputStream is = 
				new BufferedInputStream(new FileInputStream(inName));
			DataOutputStream os = 
				new DataOutputStream(new FileOutputStream(outName));
			byte b[] = new byte[1024];		// the bytes read from the file
			int count;
			while ((count = is.read(b)) != -1) {
				os.write(b, 0, count);
			}
			is.close();
			os.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		return true;
    }
}
