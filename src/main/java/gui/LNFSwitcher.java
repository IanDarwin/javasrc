import com.darwinsys.util.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.plaf.metal.*;

/**
 * A Look-and-feel switcher.
 * @author	Ian Darwin, ian@darwinsys.com
 * @version	$Id$
 */
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
		theFrame.addWindowListener(new WindowCloser(theFrame, true));
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

		JRadioButton bMac = new JRadioButton("MacOS");
		bMac.addActionListener(new LNFSetter(
			"com.sun.java.swing.plaf.mac.MacLookAndFeel", bMac));
		bg.add(bMac);
		cp.add(bMac);

		// Following is a **hypothetical** addition!
		JRadioButton bOL = new JRadioButton("OPEN LOOK");
		bOL.addActionListener(new LNFSetter(
			 "com.darwinsys.openlook.OpenLookAndFeel", bOL));
		bOL.setEnabled(false);	// since it IS hypothetical
		bg.add(bOL);
		cp.add(bOL);

		// We "know" that the Java Look-and-feel is the default.
		previousButton = bJava;
		bJava.setSelected(true);

		theFrame.pack();
		theFrame.setVisible(true);
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
		new LNFSwitcher();
	}
}
