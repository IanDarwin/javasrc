package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A very very very simple Web server.
 *
 * There is only one response to all requests, and it's hard-coded.
 * This version is not threaded and doesn't do very much.
 * Really just a proof of concept.
 * However, it is still useful on notebooks in case somebody connects
 * to you on the Web port by accident (or otherwise).
 *
 * Can't claim to be fully standards-conforming, but has been
 * tested with Netscape Communicator and with the Lynx text browser.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 * @see		webserver/* for more fully-fleshed-out version(s).
 */
public class WebServer0 {
	public static final int HTTP = 80;
	ServerSocket s;

	/**
	 * Main method, just creates a server and call its runServer().
	 */
	public static void main(String[] argv) throws Exception {
		System.out.println("DarwinSys JavaWeb Server 0.0 starting...");
		WebServer0 w = new WebServer0();
		w.runServer(HTTP);		// never returns!!
	}

	/** Get the actual ServerSocket; deferred until after Constructor
	 * so subclass can mess with ServerSocketFactory (e.g., to do SSL).
	 * @param port The port number to listen on
	 */
	protected ServerSocket getServerSocket(int port) throws Exception {
		return new ServerSocket(port);
	}

	/** RunServer accepts connections and passes each one to handler. */
	public void runServer(int port) throws Exception {
		s = getServerSocket(port);
		while (true) {
			try {
				Socket us = s.accept();
				Handler(us);
			} catch(IOException e) {
				System.err.println(e);
				return;
			}

		}
	}

	/** Handler() handles one conversation with a Web client.
	 * This is the only part of the program that "knows" HTTP.
	 */
	public void Handler(Socket s) {
		BufferedReader is;	// inputStream, from Viewer
		PrintWriter os;		// outputStream, to Viewer
		String request;		// what Viewer sends us.
		try {
			String from = s.getInetAddress().toString();
			System.out.println("Accepted connection from " + from);
			is = new BufferedReader(new InputStreamReader(s.getInputStream()));
			request = is.readLine();
			System.out.println("Request: " + request);
			
			os = new PrintWriter(s.getOutputStream(), true);
			os.println("HTTP/1.0 200 Here is your data");
			os.println("Content-type: text/html");
			os.println("Server-name: DarwinSys NULL Java WebServer 0");
			String reply1 = "<html><head>" +
				"<title>Wrong System Reached</title></head>\n" +
				"<h1>Welcome, ";
			String reply2 = ", but...</h1>\n" +
				"<p>You have reached a desktop machine " +
				"that does not run a real Web service.\n" +
				"<p>Please pick another system!</p>\n" +
				"<p>Or view <A HREF=\"http://www.darwinsys.com/java/server.html\">" +
				"the WebServer0 source (at the Authors Web Site)</A>.</p>\n" +
				"<hr/><em>Java-based WebServer0</em><hr/>\n" +
				"</html>\n";
			os.println("Content-length: " + 
				(reply1.length() + from.length() + reply2.length()));
			os.println("");
			os.println(reply1 + from + reply2);
			os.flush();
			s.close();
		} catch (IOException e) {
			System.out.println("IOException " + e);
		}
		return;
	}
}
