import java.applet.*;
import java.awt.*;
// import java.awt.event.*;		// only in JDK1.1
import java.net.*;

/**
 * SumUp is a simple applet that adds up some numbers.
 * This version is for JDK 1.0 and later; code for 1.1 is present
 * but commented out.
 * <P>
 * To make this ready for production, we should implement a little
 * language either in HTML PARAMs, such as
 * <PRE>
 *	&ltPARAM NAME=title1 value="Option One">
 *	&ltPARAM NAME=values1 value="0|100|200|300|400">
 *	&ltPARAM NAME=title1 value="Option Two">
 *	&ltPARAM NAME=values1 value="0|400|600|800|1000">
 * </PRE>
 * <BR>or<BR>
 * in a configuration file which we download and parse (see
 * TreeLink.java in this directory) or load as a Properties file
 * (see MenuIntl.java) - note that as of today we can't load
 * Properties files into an Applet for some reason.
 * <P>
 * Also, of course, the URL to go to should be a PARAM.
 * Not to mention the colors (see ColorName and/or XColor).
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 */
public class SumUp extends Applet /* 1.1: implements ActionListener */ {
	/** The array of Choice items */
	protected Choice cs[] = new Choice[10];
	/** How many are actually in the array. */
	protected int numChoices = 0;
	/** The result of the summation */
	protected Label resultField;
	/** The pushbutton to send the form in */
	protected Button sendButton;

	/** init() is an Applet method called by the browser to initialize */
	public void init() {
		setBackground(Color.magenta);
		// The layout of the Applet is a Grid; always add things in pairs!
		setLayout(new GridLayout(0,2));
		Choice c;
		add(new Label("Option 1"));
		add(c = new Choice());
			c.addItem("0");
			c.addItem("100");
			c.addItem("200");
			c.addItem("400");
		cs[numChoices++] = c;

		add(new Label("Option 2"));
		add(c = new Choice());
			c.addItem("0");
			c.addItem("100");
			c.addItem("200");
			c.addItem("400");
		cs[numChoices++] = c;

		Panel p = new Panel();
		p.setBackground(Color.pink);
		p.add(new Label("Total:"));
		p.add(resultField = new Label("000000"));
		add(p);

		sendButton = new Button("Send it");
		add(sendButton);						// connect Button into Applet
		// Next line needed for 1.1 version
		// sendButton.addActionListener(this);	// connect it back to Applet
		// or, better yet, have its own actionListener separate from that
		// used by all the Choice items.
	}

	/** action() is called when a "high level" action happens
	 *  (like the user pushing a Button!) in one of the components
	 *  added to this Applet. In JDK1.1 this would be actionPerformed(),
	 *  but the customer needed this Applet to work on the Internet at
	 *  a time when many users' browsers were still at JDK1.0.
	 */
	//public void actionPerformed(ActionEvent e) {	// 1.1
	public boolean action(Event e, Object name) {	// 1.0
		int total = 0;
		for (int i=0; i<numChoices; i++) {
			String text = cs[i].getSelectedItem();
			// System.err.println("Selection " + i + " = " + text);
			int value = Integer.parseInt(text);
			total += value;
		}
		resultField.setText(Integer.toString(total));

		// Now, if that was the Send button, do it.
		if (sendButton == e.target) {
			try {
				URL myNewURL = new URL(
					"http://server/cgi-bin/credit?SUM=" + total);

				// System.out.println("URL = " + myNewURL); // debug...

				// "And then a miracle occurs..."
				getAppletContext().showDocument(myNewURL);

			} catch (Exception err) {
				System.err.println("Error!\n" + err);
				showStatus("Error, look in Java Console for details!");
			}
		}
		return true;		// (1.0) yes, we did something, thank you.
	}
}
