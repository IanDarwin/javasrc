package applet;

import java.applet.Applet;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

/**
 * Socket Applet for "legacy" server connection via Socket.
 * @author	Ian F. Darwin, http://www.darwinsys.com/
 */
public class SocketApplet extends Applet {

	private static final long serialVersionUID = -5658315339692195076L;
	TextField nameTF, passTF, domainTF;
	Image im;
	Button sendButton;
	/** Where the Applet came from */
	URL whence;

	/** Initialize the GUI nicely. */
	public void init() {

		setLayout(new GridBagLayout());
		// int LOGO_COL = 1;
		int LABEL_COL = 2;
		int TEXT_COL = 3;
		int BUTTON_COL = 1;
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 100.0; gbc.weighty = 100.0;

		gbc.gridx = LABEL_COL; gbc.gridy = 0; 
		gbc.anchor = GridBagConstraints.EAST;
		add(new Label("Name:", Label.CENTER), gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = TEXT_COL; gbc.gridy = 0;
		add(nameTF=new TextField(10), gbc);

		gbc.gridx = LABEL_COL; gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		add(new Label("Password:", Label.CENTER), gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = TEXT_COL; gbc.gridy = 1;
		add(passTF=new TextField(10), gbc);
		passTF.setEchoChar('*');

		gbc.gridx = LABEL_COL; gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		add(new Label("Domain:", Label.CENTER), gbc);
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

				try (Socket s = new Socket(getCodeBase().getHost(), 
						SocketServer.PORT);)  {
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
}

