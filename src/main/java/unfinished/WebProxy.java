package unfinished;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

/**
 * WebProxy - simple http proxy.
 *
 * @author  Ian Darwin
 * Copyright (c) 1995, 1996, 1997, 2000 Ian F. Darwin
 */
public class WebProxy {
	/** Our server-side rendezvous socket */
	protected ServerSocket sock;
	/** The port number to use by default */
	public final static int HTTPPORT = 80;
	/** Flag to control debugging */
	protected boolean debug = true;
	/** The "listen" backlog */
	protected final int BACKLOG = 5;

	/** main: construct and run */
	public static void main(String[] argv) throws IOException {
		int port = HTTPPORT;
		if (argv.length == 1) {
			port = Integer.parseInt(argv[0]);
		}
		new WebProxy(port).handle();
	}

	/** Construct an WebProxy on the given port number.
	 * Make it on Localhost ONLY since we are tunneled into
	 */
	public WebProxy(int port) throws IOException {
		sock = new ServerSocket(
			port, BACKLOG, InetAddress.getByName("127.0.0.1"));
	}

	/** This handles the connections */
	protected void handle() {
		Socket ios = null;
		BufferedReader is = null;
		PrintWriter os = null;
		while (true) {
			try {
				System.out.println("Waiting for client...");
				ios = sock.accept();
				System.err.println("Accepted from " +
					ios.getInetAddress().getHostName());
				is = new BufferedReader(
					new InputStreamReader(ios.getInputStream(), "8859_1"));
				OutputStream out = ios.getOutputStream();
				String request = is.readLine();
				// loop, parsing headers and adding to URL
				StringTokenizer st = new StringTokenizer(request);
				String command = st.nextToken();
				String URL = st.nextToken();
				String proto = st.nextToken();
				System.out.println("URL = " + URL);

				URL url = new URL(URL);	// map String to java.net.URL
				
				// InputStream remoteIn = url.openStream();

				// int ch;
				// while ((ch = is.read()) != -1) {
					// System.out.print((byte)ch);	// DEBUG
					// out.write((byte)ch);
				// }

				Object o = url.getContent();
				System.out.println("Content object = " +
					o.getClass().getName());

				System.out.println(o);

				is.close();
				// remoteIn.close();

				System.err.println("All done!");
			} catch (IOException ex) {
				// System.err.println(ex);
				ex.printStackTrace();
			}
		}
		/*NOTREACHED*/
	}
}
