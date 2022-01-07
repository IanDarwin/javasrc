package netweb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import java.net.*;

/**
 * Simple demonstration of posting to a query form cgi on a Web server.
 * @author 		Ian Darwin, http://www.darwinsys.com/
 * @copyright 	1997, Ian Darwin, Ontario, Canada
 */
public class PostQuery {

	private static final long serialVersionUID = 3258128046665315634L;
	private static Button goButton;
	private static String serverURL;

	public static void main(String[] args) {
		PostQuery program = new PostQuery(args.length == 1 ? args[0] : null);
		JFrame jf = new JFrame("PostQuery");
		jf.add(goButton = new Button("Go for it!"));
		goButton.addActionListener(PostQuery::actionPerformed);
		jf.setSize(200, 100);
		jf.setLocation(100, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		showStatus("Ready");
	}

	public PostQuery(String url) {
		this.serverURL = url != null ? url :
				"http://server/cgi-bin/test-cgi.pl";
	}

	public static void actionPerformed(ActionEvent evt) {
		try {
			URL myNewURL;
			showStatus("Building URL " + serverURL);
			myNewURL = new URL(serverURL);

			showStatus("Connecting to " + myNewURL);

			// Try to open the connection...
			URLConnection conn = myNewURL.openConnection();
			showStatus("Connected! to " + myNewURL);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);	// ensure response always from server

			PrintWriter pw = new PrintWriter(
				new OutputStreamWriter(conn.getOutputStream()));

			showStatus("Sending...");

			send(pw, "key", "some value");
			send(pw, "key 2", "another value");
			pw.println();
			pw.close();
			
			BufferedReader is = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));

			showStatus("Receiving...");
			String newReq;
			while ((newReq = is.readLine()) != null) {
				System.out.println("Response = " + newReq);
			}
			is.close();

		} catch (Exception err) {
			showStatus("Error, look in Java Console for details!");
			System.err.println("Error!\n" + err);
		}
	}

	public static void showStatus(String s) {
		System.out.println("Status: " + s);
	}
	
	private static boolean first = true;
	/** Send one parameter, given its name and value; put 
	 * '&' before second and subsequent parameters.
	 * @param pw
	 * @param name
	 * @param value
	 * @throws UnsupportedEncodingException
	 */
	private static void send(PrintWriter pw, String name, String value ) throws UnsupportedEncodingException {
		if (first) {
			first = false;
		} else {
			pw.print("&");
		}
		pw.print(URLEncoder.encode(name, "UTF-8") + "=" +
				URLEncoder.encode(value, "UTF-8"));
	}
}
