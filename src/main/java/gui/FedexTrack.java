import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;
import java.text.*;
import java.util.*;

/**
 * Simple Applet to track a Fedex shipment without having to wait
 * for all of FedEx's bloated images to download.
 *
 * @Note: The URL used was garnered empirically, is not documented,
 * and is subject to change without notice! USE AT OWN RISK.
 *
 * @author Ian F. Darwin, ian@darwinsys.com
 */

public class FXTrack extends Applet {
	GregorianCalendar gc = new GregorianCalendar(); // today
	DecimalFormat nf = new DecimalFormat("00");
	String shipDate;
	/** The choice item for destination country */
	Choice destChooser;

	ActionListener handler = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			URL destURL = null;
			try {
				destURL = new URL("http://www.fedex.com/cgi-bin/track_it?" +
				"&kurrent_airbill=" + numField.getText() + "|" +
					destChooser.getSelectedItem() + "|" + shipDate +
					// following boilerplate seems needed
					"&language=english&cntry_code=us&state=0");
			} catch (MalformedURLException err) {
				System.err.println("Error!\n" + err);
				showStatus("Error, look in Java Console for details!");
			}
			// debug...
			System.out.println("URL = " + destURL);

			// "And then a miracle occurs..."
			FXTrack.this.getAppletContext().showDocument(destURL);
		}
	};

	protected Button goButton;
	protected TextField numField;

	public void init() {
		setBackground(Color.pink);
		setLayout(new GridLayout(3,2));
		add(new Label("Dest. Country:", Label.RIGHT));
		add(destChooser = new Choice());
		destChooser.add("ca");
		destChooser.select(0);
		destChooser.add("sv");
		destChooser.add("uk");
		destChooser.add("us");
		add(new Label("Waybill #", Label.RIGHT));
		add(numField = new TextField(12));
		numField.addActionListener(handler);
		add(new Label());	// filler, for grid
		add(goButton = new Button("Go for it!"));
		goButton.addActionListener(handler);

		gc.roll(Calendar.DAY_OF_MONTH, false);			// yesterday
		shipDate = 	// mmddyy format
			nf.format(gc.get(Calendar.MONTH)+1) +		/* zero-based */
			nf.format(gc.get(Calendar.DAY_OF_MONTH)) +
			nf.format(gc.get(Calendar.YEAR)%100);
	}
}
