import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.rmi.*;
import java.util.*;

/** Displays one machines status.
 * Part of the RMIWatch program: watch the RMI registry
 * on a bunch of machines (i.e., in a classroom or lab).
 * <P>Does a "Naming.list()" on each host, examines results to 
 * switch state among {FINE,DUBIOUS,EMPTY,NOREG,DOWN}
 * <UL>
 * <LI>FINE == Server has "expect"ed name registered.
 * <LI>DUBIOUS == Server does not have expected name registered.
 * <LI>EMPTY == Server has nothing registered.
 * <LI>NOREG == host is up but not running RMI
 * <LI>DOWN == host unreachable, not responding, ECONN, etc.
 * </UL>
 * @author	Ian F. Darwin, ian@darwinsys.com
 * Copyright (c) 2000, Ian F. Darwin. See LEGAL.NOTICE for licensing.
 * @version	$Id$
 */
public class RMIPanel extends JPanel implements Runnable {
	/** The name of this host */
	protected String hostName;
	/** The text area to display a list of stuff */
	protected JTextArea ta;
	/** Properties, passed in to constructor */
	protected Properties p;
	/** Default sleep time, in seconds. */
	protected static int DEFAULT_SLEEP = 30;
	/** Sleep time, in seconds. */
	protected int sleepTime = DEFAULT_SLEEP;
	/** The constant-width font, shared by all instances. */
	protected static Font cwFont;
	/** The states */
	/** The state for: has "expect"ed name registered. */
	protected final static int FINE = 1; 
	/** The state for: does not have expected name registered. */
	protected final static int DUBIOUS = 2;
	/** The state for: Server has nothing registered. */
	protected final static int EMPTY = 3;
	/** The state for: host is up but not running RMI */
	protected final static int NOREG = 4;
	/** The state for: host unreachable, not responding, ECONN, etc. */
	protected final static int DOWN = 5;
	/** The color for when a machine is FINE */
	protected static final Color COLOR_FINE = Color.green;
	/** The color for when a machine is DUBIOUS */
	protected static final Color COLOR_DUBIOUS = Color.yellow;
	/** The color for when a machine is EMPTY */
	protected static final Color COLOR_EMPTY = Color.white;
	/** The color for when a machine has NOREG */
	protected static final Color COLOR_NOREG = Color.red;
	/** The color for when a machine is NOREG */
	protected static final Color COLOR_DOWN = Color.black;

	/** State of the monitored hosts RMI registry, up or down.
	 * Initially set 0, which isnt one of the named states, to
	 * force the background color to be set on the first transition.
	 */
	protected int state = 0;
	
	public RMIPanel(String host, Properties p) {
		hostName = host;
		this.p = p;
		String s = p.getProperty("rmiwatch.sleep");
		if (s != null)
			sleepTime = Integer.parseInt(s);
		// System.out.println("Sleep time now " + sleepTime);

		// Maybe get font name and size from p?
		if (cwFont == null)
			cwFont = new Font("lucidasansTypewriter", Font.PLAIN, 10);

		// Gooey gooey stuff.
		ta = new JTextArea(2, 26);
		ta.setEditable(false);
		ta.setFont(cwFont);
		add(BorderLayout.CENTER, ta);
		setBorder(BorderFactory.createTitledBorder(hostName));

		// Sparks. Ignition!
		new Thread(this).start();
	}

	boolean done = false;
	/** Stop this Thread */
	public void stop() {
		done = true;
	}

	/** True if the given host is believed to be up. */
	protected int getState() {
		return state;
	}
	
	/** Record the new state of the current machine.
	 * If this machine has changed state, set its color
	 * @param newState - one of the five valid states in the introduction.
	 */
	protected void setState(int newState) {
		if (state /*already*/ == newState)
			return;		// nothing to do.
		switch(newState) {
			case FINE:		// Server has "expect"ed name registered.
				ta.setBackground(COLOR_FINE);
				ta.setForeground(Color.black);
				break;
			case DUBIOUS:	// Server does not have expected name registered.
				ta.setBackground(COLOR_DUBIOUS);
				ta.setForeground(Color.black);
				break;
			case EMPTY:		// Server has nothing registered.
				ta.setBackground(COLOR_EMPTY);
				ta.setForeground(Color.black);
				break;
			case NOREG:		// host is up but not running RMI
				ta.setBackground(COLOR_NOREG);
				ta.setForeground(Color.white);
				break;
			case DOWN:		// host unreachable, not responding, ECONN, etc.
				ta.setBackground(COLOR_DOWN);
				ta.setForeground(Color.white);
				break;
			default:
				throw new IllegalStateException("setState("+state+") invalid");
			}
		state = newState;
	}

	/** Keep the screen updated forever, unless stop()ped. */
	public void run() {
		String theURL = "rmi://" + hostName;
		while (!done) {
			try {
				String[] names = Naming.list(theURL);
				ta.setText("");
				for (int i=0; i<names.length; i++) {
					ta.append(i + ": " + names[i] + "\n");
				}
				// If we didnt get an exception, host is up.
				String expect = p.getProperty("rmiwatch.expect");
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
					Thread.sleep((int)(sleepTime * 1000 * 2 * Math.random()));
				} catch (InterruptedException e) {
					/*CANTHAPPEN*/
				}
			}
		}
	}
}
