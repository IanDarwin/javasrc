package netwatch;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Properties;

/** Displays one machines status, for RMI..
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 * Copyright (c) 2000, Ian F. Darwin. See LEGAL.NOTICE for licensing.
 */
// BEGIN main
public class RMIPanel extends NetPanel implements Runnable {

	private static final long serialVersionUID = 1L;

	public RMIPanel(String host, Properties p) {
		super(host, p);
	}

	/** Keep the screen updated forever, unless stop()ped. */
	public void run() {
		String thePort = props.getProperty("rmiwatch.port", "");
		String theURL = "rmi://" + hostName + ":" + thePort;
		while (!done) {
			try {
				String[] names = Naming.list(theURL);
				ta.setText("");
				for (int i=0; i<names.length; i++) {
					ta.append(i + ": " + names[i] + "\n");
				}
				// If we didnt get an exception, host is up.
				String expect = props.getProperty("rmiwatch.expect");
				String fullText = ta.getText();
				if (fullText.length() == 0) {
					ta.setText("(nothing registered!)");
					setState(EMPTY);
				} else if (expect != null && fullText.indexOf(expect)==-1) {
					setState(DUBIOUS);
				} else setState(FINE);
			} catch (java.rmi.ConnectIOException e) {
				setState(DOWN);
				ta.setText("Net error: " + e.detail.getClass());
			} catch (java.rmi.ConnectException e) {
				setState(NOREG);
				ta.setText("RMI error: " + e.getClass().getName() + "\n" +
					"  " + e.detail.getClass());
				// System.err.println(hostName + ":" + e);
			} catch (RemoteException e) {
				setState(NOREG);
				ta.setText("RMI error: " + e.getClass().getName() + "\n" +
					"  " + e.detail.getClass());
			} catch (MalformedURLException e) {
				setState(DOWN);
				ta.setText("Invalid host: " + e.toString());
			} finally {
				// sleep() in "finally" so common "down" states dont bypass.
				// Randomize time so we dont make net load bursty.
				try {
					Thread.sleep((int)(sleepTime * MSEC * 2 * Math.random()));
				} catch (InterruptedException e) {
					/*CANTHAPPEN*/
				}
			}
		}
	}
}
// END main
