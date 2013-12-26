package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * A Look-and-feel switcher.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class LNFSwitcher {
	/** The frame. */
	protected JFrame theFrame;
	/** Its content pane */
	protected Container cp;

	/** Start with the Java look-and-feel, if possible */
	final static String PREFERREDLOOKANDFEELNAME =
		"javax.swing.plaf.metal.MetalLookAndFeel";
	protected String curLF = PREFERREDLOOKANDFEELNAME;
	protected JRadioButton previousButton;

	/** Construct a program... */
	public LNFSwitcher() {
		super();
		theFrame = new JFrame("LNF Switcher");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = theFrame.getContentPane();
		cp.setLayout(new FlowLayout());

		ButtonGroup bg = new ButtonGroup();

		JRadioButton bJava = new JRadioButton("Java");
		bJava.addActionListener(new LNFSetter(
			"javax.swing.plaf.metal.MetalLookAndFeel", bJava));
		bg.add(bJava);
		cp.add(bJava);

		JRadioButton bMSW  = new JRadioButton("MS-Windows");
		bMSW.addActionListener(new LNFSetter(
			"com.sun.java.swing.plaf.windows.WindowsLookAndFeel", bMSW));
		bg.add(bMSW);
		cp.add(bMSW);

		JRadioButton bMotif = new JRadioButton("Motif");
		bMotif.addActionListener(new LNFSetter(
			"com.sun.java.swing.plaf.motif.MotifLookAndFeel", bMotif));
		bg.add(bMotif);
		cp.add(bMotif);

		JRadioButton bMac = new JRadioButton("Sun-MacOS");
		bMac.addActionListener(new LNFSetter(
			"com.sun.java.swing.plaf.mac.MacLookAndFeel", bMac));
		bg.add(bMac);
		cp.add(bMac);

		String defaultLookAndFeel = UIManager.getSystemLookAndFeelClassName();
		// System.out.println(defaultLookAndFeel);
		JRadioButton bDefault = new JRadioButton("Default");
		bDefault.addActionListener(new LNFSetter(
			 defaultLookAndFeel, bDefault));
		bg.add(bDefault);
		cp.add(bDefault);

		(previousButton = bDefault).setSelected(true);

		theFrame.pack();
	}

	/* Class to set the Look and Feel on a frame */
	class LNFSetter implements ActionListener {
		String theLNFName;
		JRadioButton thisButton;

		/** Called to setup for button handling */
		LNFSetter(String lnfName, JRadioButton me) {
			theLNFName = lnfName;
			thisButton = me;
		}

		/** Called when the button actually gets pressed. */
		public void actionPerformed(ActionEvent e) {
			try {
				UIManager.setLookAndFeel(theLNFName);
				SwingUtilities.updateComponentTreeUI(theFrame);
				theFrame.pack();
			} catch (Exception evt) {
				JOptionPane.showMessageDialog(null,
					"setLookAndFeel didn't work: " + evt,
					"UI Failure", JOptionPane.INFORMATION_MESSAGE);
				previousButton.setSelected(true);		// reset the GUI to agree
			}
			previousButton = thisButton;
		}
	}

	public static void main(String[] argv) {
		LNFSwitcher o = new LNFSwitcher();
		o.theFrame.setVisible(true);
	}
}
// END main
