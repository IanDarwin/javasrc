import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/** Main GUI component for the NetWatch program.
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @version	$Id$
 * Copyright (c) 2000, Ian F. Darwin. See LEGAL.NOTICE for licensing.
 */
public class NetFrame extends JFrame {
	Properties props;
	Container cp;

	/** Constructor */
	public NetFrame(String title, Properties p) {
		super(title);

		props = p;
		cp = getContentPane();

		JMenuBar jb = new JMenuBar();
		JMenu jm;
		JMenuItem mi;

		// FILE MENU
		jb.add(jm = new JMenu("File"));
		jm.add(mi = new JMenuItem("Exit"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// VIEW MENU
		jb.add(jm = new JMenu("View"));
		jm.add(mi = new JMenuItem("Ping"));
		mi.setEnabled(false);
		jm.add(mi = new JMenuItem("Socket"));
		mi.setEnabled(false);
		jm.add(mi = new JMenuItem("RMI"));
		jm.add(mi = new JMenuItem("CORBA"));
		mi.setEnabled(false);

		// HELP MENU
		jb.add(jm = new JMenu("Help"));
		jm.add(mi = new JMenuItem("About"));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(NetFrame.this,
					"NetWatch Application -- $Version$\n" +
					"Copyright (c) 2000 Ian F. Darwin\n" +
					"See LEGAL.NOTICE for licensing.",
					"RMIWatch",
					JOptionPane.INFORMATION_MESSAGE);
			}
		});
		this.setJMenuBar(jb);

		cp.setLayout(new GridLayout(0,1));
	}

	/** CONSTRUCT PANELS, ONE FOR EACH HOST. */ 
	protected void addHost(String hostName, Properties props) {
		cp.add(new RMIPanel(hostName, props));
			
		// If it got too tall, make it multi columns.
		if (cp.getComponents().length > 12)
			cp.setLayout(new GridLayout(0,3));
		else if (cp.getComponents().length > 6)
			cp.setLayout(new GridLayout(0,2));
	}
}
