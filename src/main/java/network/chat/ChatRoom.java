package chat;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

/** 
 * <p>
 * Simple Chat Room Applet.
 * Writing a Chat Room seems to be one of many obligatory rites (or wrongs)
 * of passage for Java experts these days.</p>
 * <p>
 * This one is a toy because it doesn't have much of a protocol, which
 * means we can't query the server as to * who's logged in,
 * or anything fancy like that. However, it works OK for small groups.</p>
 * <p>
 * Uses client socket w/ two Threads (main and one constructed),
 * one for reading and one for writing.</p>
 * <p>
 * Server multiplexes messages back to all clients.</p>
 * <p>
 * TODO in V2: consider using Java's MultiCastSocket.</p>
 * @author Ian Darwin
 * @version $Id$
 */
public class ChatRoom extends Applet {
	/** Whether we are being run as an Applet or an Application */
	protected boolean inAnApplet = true;
	/** The state of logged-in-ness */
	protected boolean loggedIn;
	/* The Frame, for a pop-up, durable Chat Room. */
	protected Frame cp;
	/** The default port number */
	protected static int PORTNUM = Chat.PORTNUM;
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
	protected Button lib;
	/** The LogOUT button */
	protected Button lob;
	/** The TitleBar title */
	final static String TITLE = "Chat: Ian Darwin's Toy Chat Room Client";
	/** The message that we paint */
	protected String paintMessage;

	/** init, overriding the version inherited from Applet */
	public void init() {
		paintMessage = "Creating Window for Chat";
		repaint();
		cp = new Frame(TITLE);
		cp.setLayout(new BorderLayout());
		String portNum = null;
		if (inAnApplet)
			portNum = getParameter("port");
		port = PORTNUM;
		if (portNum != null)
			port = Integer.parseInt(portNum);

		// The GUI
		ta = new TextArea(14, 80);
		ta.setEditable(false);		// readonly
		ta.setFont(new Font("Monospaced", Font.PLAIN, 11));
		cp.add(BorderLayout.NORTH, ta);

		Panel p = new Panel();
		Button b;

		// The login button
		p.add(lib = new Button("Login"));
		lib.setEnabled(true);
		lib.requestFocus();
		lib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
				lib.setEnabled(false);
				lob.setEnabled(true);
				tf.requestFocus();	// set keyboard focus in right place!
			}
		});

		// The logout button
		p.add(lob = new Button("Logout"));
		lob.setEnabled(false);
		lob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
				lib.setEnabled(true);
				lob.setEnabled(false);
				lib.requestFocus();
			}
		});

		p.add(new Label("Message here:"));
		tf = new TextField(40);
		tf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (loggedIn) {
					pw.println(Chat.CMD_BCAST+tf.getText());
					tf.setText(""); 
				}
			}
		});
		p.add(tf);

		cp.add(BorderLayout.SOUTH, p);

        cp.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// If we do setVisible and dispose, then the Close completes
				ChatRoom.this.cp.setVisible(false);
				ChatRoom.this.cp.dispose();
				logout();
			}
		});
		cp.pack();
		// After packing the Frame, centre it on the screen.
		Dimension us = cp.getSize(), 
			them = Toolkit.getDefaultToolkit().getScreenSize();
		int newX = (them.width - us.width) / 2;
		int newY = (them.height- us.height)/ 2;
		cp.setLocation(newX, newY);
		cp.setVisible(true);
		paintMessage = "Window should now be visible";
		repaint();
	}

	protected String serverHost = "localhost";

	/** LOG ME IN TO THE CHAT */
	public void login() {
		showStatus("In login!");
		if (loggedIn)
			return;
		if (inAnApplet)
			serverHost = getCodeBase().getHost();
		try {
			sock = new Socket(serverHost, port);
			is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(sock.getOutputStream(), true);
		} catch(IOException e) {
			showStatus("Can't get socket to " + 
				serverHost + "/" + port + ": " + e);
			cp.add(new Label("Can't get socket: " + e));
			return;
		}
		showStatus("Got socket");

		// Construct and start the reader: from server to textarea.
		// Make a Thread to avoid lockups.
		new Thread(new Runnable() {
			public void run() {
				String line;
				try {
					while (loggedIn && ((line = is.readLine()) != null))
						ta.append(line + "\n");
				} catch(IOException e) {
					showStatus("GAA! LOST THE LINK!!");
					return;
				}
			}
		}).start();

		// FAKE LOGIN FOR NOW
		pw.println(Chat.CMD_LOGIN + "AppletUser");
		loggedIn = true;
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

	// It is deliberate that there is no STOP method - we want to keep
	// going even if the user moves the browser to another page.
	// Anti-social? Maybe, but you can use the CLOSE button to kill 
	// the Frame, or you can exit the Browser.

	/** Paint paints the small window that appears in the HTML,
	 * telling the user to look elsewhere!
	 */
	public void paint(Graphics g) {
		Dimension d = getSize();
		int h = d.height;
		int w = d.width;
		g.fillRect(0, 0, w, 0);
		g.setColor(Color.black);
		g.drawString(paintMessage, 10, (h/2)-5);
	}


	/** a showStatus that works for Applets or non-Applets alike */
	public void showStatus(String mesg) {
		if (inAnApplet)
			super.showStatus(mesg);
		System.out.println(mesg);
	}

	/** A main method to allow the client to be run as an Application */
	public static void main(String[] args) {
		ChatRoom room101 = new ChatRoom();
		room101.inAnApplet = false;
		room101.init();
		room101.start();
	}
}
