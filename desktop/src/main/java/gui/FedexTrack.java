package gui;

import javax.swing.*;
import java.awt.Desktop;
import java.awt.Choice;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

/**
 * Simple class to track a Fedex shipment without having to wait
 * for all of FedEx's bloated images to download.
 *
 * @Note: The URL used was garnered empirically, is not documented,
 * and is subject to change without notice!
 *
 * @author Ian Darwin, https://darwinsys.com/
 */

public class FedexTrack extends JPanel {

	protected Choice destChooser;
	protected JButton goButton;
	protected JTextField numField;

	ActionListener handler = evt -> {
			URI destURL = null;
			try {
				String urlStr = String.format(
					"https://www.fedex.com/apps/fedextrack/?action=track" +
					"&trackingnumber=%s&cntry_code=us&locale=en_US", 
					numField.getText());
				destURL = new URI(urlStr);
			} catch (URISyntaxException err) {
				throw new RuntimeException("Invalid input?", err);
			}
			// debug...
			System.out.println("URL = " + destURL);

			// "And then a miracle occurs..."
			try {
				showDocument(destURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
	};

	void showDocument(URI url) throws IOException {
		Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        } else {
            System.err.println("no desktop support");
            return;
        }

        if (desktop.isSupported(Desktop.Action.BROWSE)) {
            desktop.browse(url);
        } else {
        	System.err.println("no browser support");
        }

	}

	public FedexTrack() {
		setBackground(Color.pink);
		setLayout(new GridLayout(3,2));
		add(new JLabel("Dest. Country:", JLabel.RIGHT));
		add(destChooser = new Choice());
		destChooser.add("ca");
		destChooser.select(0);
		destChooser.add("sv");
		destChooser.add("uk");
		destChooser.add("us");
		add(new JLabel("Waybill #", JLabel.RIGHT));
		add(numField = new JTextField(12));
		numField.addActionListener(handler);
		add(new JLabel());	// filler, for grid
		add(goButton = new JButton("Go for it!"));
		goButton.addActionListener(handler);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame("Fedex Tracker");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setContentPane(new FedexTrack());
		jf.setSize(400, 350);
		jf.setVisible(true);
	}
}
