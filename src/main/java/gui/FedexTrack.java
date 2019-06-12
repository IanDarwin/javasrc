package gui;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Simple Applet to track a Fedex shipment without having to wait
 * for all of FedEx's bloated images to download.
 *
 * @Note: The URL used was garnered empirically, is not documented,
 * and is subject to change without notice! USE AT OWN RISK.
 *
 * @author Ian F. Darwin, http://www.darwinsys.com/
 */

public class FedexTrack extends Applet {

	protected Choice destChooser;
	protected Button goButton;
	protected TextField numField;

	ActionListener handler = e -> {
			URL destURL = null;
			try {
				String urlStr = String.format(
					"https://www.fedex.com/apps/fedextrack/?action=track" +
					"&trackingnumber=%s&cntry_code=us&locale=en_US", 
					numField.getText());
				destURL = new URL(urlStr);
			} catch (MalformedURLException err) {
				throw new RuntimeException("Invalid input?", err);
			}
			// debug...
			System.out.println("URL = " + destURL);

			// "And then a miracle occurs..."
			FedexTrack.this.getAppletContext().showDocument(destURL);
	};


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
	}
}
