import com.darwinsys.util.*;

import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.net.*;
import java.awt.*;

/**
 * ExecDemoNS shows how to execute a 32-bit Windows program from within Java.
 */
public class ExecDemoNS extends JFrame {
	/** The name of the help file. */
	protected final static String HELPFILE = "./help/index.html";

	/** The path to the Netscape binary  */
	protected static String netscape;

	/** A process object tracks one external running process */
	Process p;

	/** main - instantiate and run */
	public static void main(String av[]) throws Exception { 
		new ExecDemoNS().setVisible(true);
	}

	/** Constructor - set up strings and things. */
	public ExecDemoNS() {
		super("ExecDemo: Netscape");
		String osname = System.getProperty("os.name");
		if (osname == null) 
			throw new IllegalArgumentException("no os.name");
		netscape = // Windows or UNIX only for now, sorry Mac fans
			(osname.toLowerCase().indexOf("windows")!=-1) ?
			"c:/program files/netscape/communicator/program/netscape.exe" :
			"/usr/local/netscape/netscape";

		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		JButton b;
		cp.add(b=new JButton("Exec"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				doHelp();
			}
		});
		cp.add(b=new JButton("Wait"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				doWait();
			}
		});
		cp.add(b=new JButton("Exit"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		pack();
	}

	/** Start the help, in its own Thread. */
	public void doHelp() {

		new Thread() {
			public void run() {

				try {
					// Get the URL for the Help File
					URL helpURL = this.getClass().getClassLoader().
						getResource(HELPFILE);

					// Start Netscape from the Java Application. A Java
					// Applet would not be allowed to, nor need to :-)

					p = Runtime.getRuntime().exec(netscape + " " + helpURL);

					Debug.println("trace", "In main after exec");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(ExecDemoNS.this,
						"Error" + ex, "Error",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		}.start();

	}

	public void doWait() {
		try {
			p.waitFor();	// wait for process to complete
			Debug.println("trace", "Process is done");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this,
				"Error" + ex, "Error",
				JOptionPane.ERROR_MESSAGE);
		}
	}

}
