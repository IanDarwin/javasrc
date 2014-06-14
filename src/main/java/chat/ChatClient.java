package chat;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;

/** 
 * <p>
 * Simple Chat Room GUI.
 * Writing a Chat Room seems to be one of many obligatory rites (or wrongs)
 * of passage for Java experts these days.</p>
 * <p>
 * This one is a toy because it doesn't have much of a protocol, which
 * means we can't query the server as to who's logged in,
 * or anything fancy like that. However, it works OK for small groups.</p>
 * <p>
 * Uses client socket w/ two Threads (main and one constructed),
 * one for reading and one for writing.</p>
 * <p>
 * Server multiplexes messages back to all clients.</p>
 * @author Ian Darwin
 */
// BEGIN main
public class ChatClient extends JFrame {

	private static final long serialVersionUID = -3686334002367908392L;
	private static final String userName = 
		System.getProperty("user.name", "User With No Name");
	/** The state of logged-in-ness */
	protected boolean loggedIn;
	/* The main Frame. */
	protected JFrame cp;
	/** The default port number */
	protected static final int PORTNUM = ChatProtocol.PORTNUM;
	/** The actual port number */
	protected int port;
	/** The network socket */
	protected Socket sock;
	/** BufferedReader for reading from socket */
	protected BufferedReader is;
	/** PrintWriter for sending lines on socket */
	protected PrintWriter pw;
	/** TextField for input */
	protected TextField tf;
	/** TextArea to display conversations */
	protected TextArea ta;
	/** The Login button */
	protected Button loginButton;
	/** The LogOUT button */
	protected Button logoutButton;
	/** The TitleBar title */
	final static String TITLE = "ChatClient: Ian Darwin's Toy Chat Room Client";

	/** set up the GUI */
	public ChatClient() {
		cp = this;
		cp.setTitle(TITLE);
		cp.setLayout(new BorderLayout());
		port = PORTNUM;
		
		// The GUI
		ta = new TextArea(14, 80);
		ta.setEditable(false);		// readonly
		ta.setFont(new Font("Monospaced", Font.PLAIN, 11));
		cp.add(BorderLayout.NORTH, ta);

		Panel p = new Panel();

		// The login button
		p.add(loginButton = new Button("Login"));
		loginButton.setEnabled(true);
		loginButton.requestFocus();
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
				loginButton.setEnabled(false);
				logoutButton.setEnabled(true);
				tf.requestFocus();	// set keyboard focus in right place!
			}
		});

		// The logout button
		p.add(logoutButton = new Button("Logout"));
		logoutButton.setEnabled(false);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
				loginButton.setEnabled(true);
				logoutButton.setEnabled(false);
				loginButton.requestFocus();
			}
		});

		p.add(new Label("Message here:"));
		tf = new TextField(40);
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loggedIn) {
					pw.println(ChatProtocol.CMD_BCAST+tf.getText());
					tf.setText(""); 
				}
			}
		});
		p.add(tf);

		cp.add(BorderLayout.SOUTH, p);

        cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp.pack();
	}

	protected String serverHost = "localhost";

	/** LOG ME IN TO THE CHAT */
	public void login() {
		showStatus("In login!");
		if (loggedIn)
			return;
		try {
			sock = new Socket(serverHost, port);
			is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(sock.getOutputStream(), true);
			showStatus("Got socket");

			// FAKE LOGIN FOR NOW - no password needed
			pw.println(ChatProtocol.CMD_LOGIN + userName);

			loggedIn = true;

		} catch(IOException e) {
			showStatus("Can't get socket to " + 
				serverHost + "/" + port + ": " + e);
			cp.add(new Label("Can't get socket: " + e));
			return;
		}

		// Construct and start the reader: from server to textarea.
		// Make a Thread to avoid lockups.
		new Thread(new Runnable() {
			public void run() {
				String line;
				try {
					while (loggedIn && ((line = is.readLine()) != null))
						ta.append(line + "\n");
				} catch(IOException e) {
					showStatus("Lost another client!\n" + e);
					return;
				}
			}
		}).start();
	}

	/** Log me out, Scotty, there's no intelligent life here! */
	public void logout() {
		if (!loggedIn)
			return;
		loggedIn = false;
		try {
			if (sock != null)
				sock.close();
		} catch (IOException ign) {
			// so what?
		}
	}

	public void showStatus(String message) {
		System.out.println(message);
	}

	/** A main method to allow the client to be run as an Application */
	public static void main(String[] args) {
		ChatClient room101 = new ChatClient();
		room101.pack();
		room101.setVisible(true);
	}
}
// END main
