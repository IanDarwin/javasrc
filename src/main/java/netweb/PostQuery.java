import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import java.net.*;

/**
 * Simple demonstration of posting to a query form cgi on a Web server.
 * @author 		Ian Darwin, http://www.darwinsys.com/
 * @copyright 	1997, Ian Darwin, Ontario, Canada
 * @see			http://www.learningtree.com/us/courses/471.htm
 */
public class PostQuery extends Applet implements ActionListener{
	protected Button goButton;
	protected URLConnection conn;
	protected PrintWriter ps;
	protected BufferedReader is;

	public PostQuery() {
		add(goButton = new Button("Go for it!"));
		goButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			URL myNewURL;
			String serverURL = getParameter("serverURL");
			if (serverURL == null)
				serverURL = "http://server/cgi-bin/test-cgi.pl";
			showStatus("Building URL " + serverURL);
			myNewURL = new URL(serverURL);

			showStatus("Connecting to " + myNewURL);

			// Try to open the connection...
			conn = myNewURL.openConnection();
			showStatus("Connected! to " + myNewURL);
			conn.setDoOutput(true);
			conn.setUseCaches(false);	// ensure response always from server

			ps = new PrintWriter(
				new OutputStreamWriter(conn.getOutputStream()));

			showStatus("Sending...");

			ps.println(URLEncoder.encode("key") + "=" +
					URLEncoder.encode("some value"));
			ps.println(URLEncoder.encode("key 2") + "=" +
					URLEncoder.encode("another value"));
			ps.close();

			conn.setDoInput(true);
			is = new BufferedReader(
				new InputStreamReader(conn.getInputStream()));

			showStatus("Receiving...");
			String newReq;
			while ((newReq = is.readLine()) != null) {
				System.out.println("Response = " + newReq);
			}
			showStatus("Look for results in the console window");
			is.close();

		} catch (Exception err) {
			showStatus("Error, look in Java Console for details!");
			System.err.println("Error!\n" + err);
		}
	}
}
