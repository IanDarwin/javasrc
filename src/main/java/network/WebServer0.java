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
 * tested with various browsers.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
public class WebServer0 {
	public static final int HTTP = 80;
	public static final String CRLF = "\r\n";
	ServerSocket s;
	static final String VIEW_SOURCE_URL =
	  "https://github.com/IanDarwin/javasrc/tree/master/src/main/java/network";

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
			os.print("HTTP/1.0 200 Here is your data" + CRLF);
			os.print("Content-type: text/html" + CRLF);
			os.print("Server-name: DarwinSys NULL Java WebServer 0" + CRLF);
			String reply1 = "<html><head>" +
				"<title>Wrong System Reached</title></head>\n" +
				"<h1>Welcome, ";
			String reply2 = ", but...</h1>\n" +
				"<p>You have reached a desktop machine " +
				"that does not run a real Web service.\n" +
				"<p>Please pick another system!</p>\n" +
				"<p>Or view <a href=\"" + VIEW_SOURCE_URL + "\">" +
				"the WebServer0 source on github</a>.</p>\n" +
				"<hr/><em>Java-based WebServer0</em><hr/>\n" +
				"</html>\n";
			os.print("Content-length: " + 
				(reply1.length() + from.length() + reply2.length()) + CRLF);
			os.print(CRLF);
			os.print(reply1 + from + reply2 + CRLF);
			os.flush();
			s.close();
		} catch (IOException e) {
			System.out.println("IOException " + e);
		}
		return;
	}
}
// END main
