package netweb;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.JFrame;

/**
 * Simple demonstration of posting to a query form cgi on a Web server.
 * @author 		Ian Darwin, https://darwinsys.com/
 * @copyright 	1997, Ian Darwin, Ontario, Canada
 */
public class PostQuery {

	private static final long serialVersionUID = 3258128046665315634L;
	private static Button goButton;
	private static String serverURL;

	public static void main(String[] args) {
		PostQuery postQuery = new PostQuery(args.length == 1 ? args[0] : null);
		JFrame jf = new JFrame("PostQuery");
		jf.add(goButton = new Button("Go for it!"));
		goButton.addActionListener(postQuery::actionPerformed);
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

	public void actionPerformed(ActionEvent evt) {
		try {
			URL myNewURL;
			showStatus("Building URL " + serverURL);
			myNewURL = URI.create(serverURL).toURL();

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
