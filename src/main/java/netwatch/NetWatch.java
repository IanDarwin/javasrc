package netwatch;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JOptionPane;

/** Main program for the NetWatch program: watch the network status
 * on a bunch of machines (i.e., in a classroom or lab). Currently only
 * for RMI, but can be extended for TCP socket, CORBA ORB, etc.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 * Copyright (c) 2000, Ian F. Darwin. See LEGAL.NOTICE for licensing.
 */
// BEGIN main
public class NetWatch {
	public static void main(String[] argv) {

		Properties p = null;

		NetFrame f = new NetFrame("Network Watcher", p);

		try {
			FileInputStream is = new FileInputStream("NetWatch.properties");
			p = new Properties();
			p.load(is);
			is.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(f,
				e.toString(), "Properties error",
				JOptionPane.ERROR_MESSAGE);
		}

		// NOW CONSTRUCT PANELS, ONE FOR EACH HOST.

		// If arguments, use them as hostnames.
		if (argv.length!=0) {
			for (int i=0; i<argv.length; i++) {
				f.addHost(argv[i], p);
			}
		// No arguments. Can we use properties?
		} else if (p != null && p.size() > 0) {
			String net = p.getProperty("netwatch.net");
			int start = Integer.parseInt(p.getProperty("netwatch.start"));
			int end = Integer.parseInt(p.getProperty("netwatch.end"));
			for (int i=start; i<=end; i++) {
				f.addHost(net + "." + i, p);
			}
			for (int i=0; ; i++) {
				String nextHost = p.getProperty("nethost" + i);
				if (nextHost == null)
					break;
				f.addHost(nextHost, p);
			}
		}
		// None of the above. Fall back to localhost
		else {
			f.addHost("localhost", p);
		}

		// All done. Pack the Frame and show it.
		f.pack();
		// UtilGUI.centre(f);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
// END main
