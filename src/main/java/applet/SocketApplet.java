package applet;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/**
 * Socket Applet for "legacy" server connection via Socket.
 * @version $Id$
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class SocketApplet extends Applet {
	TextField nameTF, passTF, domainTF;
	Image im;
	Button sendButton;
	/** Where the Applet came from */
	URL whence;

	//+
	/** Initialize the GUI nicely. */
	public void init() {
		Label aLabel;

		setLayout(new GridBagLayout());
		int LOGO_COL = 1;
		int LABEL_COL = 2;
		int TEXT_COL = 3;
		int BUTTON_COL = 1;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 100.0; gbc.weighty = 100.0;

		gbc.gridx = LABEL_COL; gbc.gridy = 0; 
		gbc.anchor = GridBagConstraints.EAST;
		add(aLabel = new Label("Name:", Label.CENTER), gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = TEXT_COL; gbc.gridy = 0;
		add(nameTF=new TextField(10), gbc);

		gbc.gridx = LABEL_COL; gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(aLabel = new Label("Password:", Label.CENTER), gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = TEXT_COL; gbc.gridy = 1;
		add(passTF=new TextField(10), gbc);
		passTF.setEchoChar('*');

		gbc.gridx = LABEL_COL; gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		add(aLabel = new Label("Domain:", Label.CENTER), gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = TEXT_COL; gbc.gridy = 2;
		add(domainTF=new TextField(10), gbc);
		sendButton = new Button("Send data");
		gbc.gridx = BUTTON_COL; gbc.gridy = 3;
		gbc.gridwidth = 3;
		add(sendButton, gbc);

		whence = getCodeBase();

		// Now the action begins...
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String name = nameTF.getText();
				if (name.length() == 0) {
					showStatus("Name required");
					return;
				}
				String domain = domainTF.getText();
				if (domain.length() == 0) {
					showStatus("Domain required");
					return;
				}
				showStatus("Connecting to host " + whence.getHost() +
					" as " + nameTF.getText());

				try {
					Socket s = new Socket(getCodeBase().getHost(), 
						SocketServer.PORT);
					PrintWriter pf = new PrintWriter(s.getOutputStream(), true);
					// send login name
					pf.println(nameTF.getText());
					// passwd
					pf.println(passTF.getText());
					// and domain
					pf.println(domainTF.getText());

					BufferedReader is = new BufferedReader(
						new InputStreamReader(s.getInputStream()));
					String response = is.readLine();
					showStatus(response);
				} catch (IOException e) {
					showStatus("ERROR: " + e.getMessage());
				}
			}
		});
	}
	//-
}

