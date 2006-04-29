package applet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * <p>
 * SumUp is a simple applet that adds up some numbers.
 * To make this really useful for production, should implement a "little
 * language" either in HTML PARAMs, such as
 * <pre>
 *	&ltparam name="title1" value="Option One">
 *	&ltparam name="values1" value="0|100|200|300|400">
 *	&ltparam name="title1" value="Option Two">
 *	&ltparam name="values1" value="0|400|600|800|1000">
 * </pre>
 * <br/>or<br/>
 * in a configuration file which we download and parse (see
 * TreeLink.java in this directory) or load as a Properties file
 * (see MenuIntl.java). </p>
 * <p>
 * Also, of course, the URL to go to should be a PARAM.
 * Not to mention the colors (see ColorName and/or XColor).</p>
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */
public class SumUp extends Applet implements ActionListener {

	private static final long serialVersionUID = -3804328494521053212L;
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
		sendButton.addActionListener(this);		// connect it back to Applet
	}

	/** actionPerforformed() is called when a "high level" action happens
	 *  (like the user pushing a Button!) in one of the components
	 *  added to this Applet. 
	 */
	public void actionPerformed(ActionEvent e) {	// 1.1
		int total = 0;
		for (int i=0; i<numChoices; i++) {
			String text = cs[i].getSelectedItem();
			// System.err.println("Selection " + i + " = " + text);
			int value = Integer.parseInt(text);
			total += value;
		}
		resultField.setText(Integer.toString(total));

		try {
			URL myNewURL = new URL(
				"http://server/cgi-bin/credit?sum=" + total);

			// System.out.println("URL = " + myNewURL); // debug...

			// "And then a miracle occurs..."
			getAppletContext().showDocument(myNewURL);

		} catch (Exception err) {
			System.err.println("Error!\n" + err);
			showStatus("Error, look in Java Console for details!");
		}
	}
}
