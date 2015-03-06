package netwatch;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/** Main GUI component for the NetWatch program.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class NetFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	Properties props;
	Container cp;
	JDialog propsDialog;

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
		jb.add(jm = new JMenu("Edit"));
		jm.add(mi = new JMenuItem("Properties..."));
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (propsDialog == null) {
					propsDialog = new ProtoDialog(NetFrame.this, "Properties");
				}
				propsDialog.setVisible(true);
				// TODO fetch protocol

			}
		});

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

		// If asked for ncolumns, make it so.
		// Else If it got too tall, make it multi columns.
		String nc = props.getProperty("netwatch.gui.columns");
		if (nc != null) {
			int n = Integer.parseInt(nc);
			cp.setLayout(new GridLayout(0, n));
		} else if (cp.getComponents().length > 12)
			cp.setLayout(new GridLayout(0,3));
		else if (cp.getComponents().length > 6)
			cp.setLayout(new GridLayout(0,2));
	}
}
// END main
