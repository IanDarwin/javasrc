package newswire;

import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.util.*;

/** Newswire Applet, inspired by
 * "http://industry.java.sun.com/javanews/classes/jic10" 
 * 
 * Downloads a list of items in three columns
 *	keyword|text|URL
 *
 * Could be made much fancier with getParameter("FontName"), "FontSize",
 * adjusting width with fontMetrics, etc.  Works adequately for now.
 */
public class NewsWire extends Applet {
	/** The hashtable maps from curText to URL */
	protected Hashtable hash;
	/** The Vector contains all the Texts */
	protected Vector vect;
	/** Stop the Thread when we are done */
	boolean done = false;
	/** The current Text */
	protected String curText;
	/** The current Text's index into vect */
	protected int index;

	// Initialize this NewsWire applet
	public void init() {

		showStatus("NewsWire initializing...");

		hash = new Hashtable();
		vect = new Vector();

		// Read the configuration file from the URL.
		try {
			String txt;
			String fn;	// Control file name
			if ((fn = getParameter("filename")) == null)
				fn = "NewsWire.txt";
			URL ctlFile = new URL(getCodeBase(), fn);
			BufferedReader is =
				new BufferedReader(
					new InputStreamReader(ctlFile.openStream()));

			// Read the control file a line at a time, parse
			// it, and save the links in the Hashtable indexed by 
			// their text

			while ((txt = is.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(txt, "|");
				if (st.countTokens() < 3) {
					eprintln("NewsWire: Bad input: " + txt);
					continue;
				}
				String origin = st.nextToken();
				String text = st.nextToken();
				String bURL = st.nextToken();
				URL u = new URL(bURL);
				hash.put(text, u);
				vect.addElement(text);
			}
		} catch (MalformedURLException mfc) {
			eprintln("NewsWire: Error: " + mfc);
		} catch (IOException billg) {
			eprintln("NewsWire: Error: " + billg);
		}

		System.out.println("Size now " + getSize());

		showStatus("NewsWire ready");
	}

	public void start() {
		done = false;
		new Thread(new Runnable() {
			public void run() {
				while (!done) {
					try {
						Thread.sleep(1000);
						curText = (String)vect.elementAt((++index)%vect.size());
						repaint();
					} catch (InterruptedException e) {
						// nothing to do
					}
				}
			}
		}).start();
	}
	public void stop() {
		done = true;
	}

	/** Paint -- called periodically */
	public void paint(Graphics g) {
		g.drawString(curText, 0, 0);
	}

	// Convenience Routine
	protected void eprintln(String s) {
		System.err.println(s);
		showStatus("Error, see Java Console");
	}

	/** Return information about this applet. */
	public String getAppletInfo() {
		return "NewsWire Demo Applet\n" +
			"Copyright 1998 Ian F. Darwin";
	}

	/** Return list of allowable parameters. */
	public String[][] getParameterInfo() {
		String param_info[][] = {
			{"filename",    "filename",    "List of links"},
		};
		return param_info;
	}
}
