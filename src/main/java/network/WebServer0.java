import java.net.*;
import java.io.*;

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
 * @author	Ian Darwin, ian@darwinsys.com
 * @version	$Id$
 * @see		webserver/* for more fully-fleshed-out version(s).
 */
public class WebServer0 {
	public static final int HTTP = 80;
	ServerSocket s;

	/**
	 * Main method, just creates a server and call its runServer().
	 */
	public static void main(String argv[]) {
		System.out.println("DarwinSys JavaWeb Server 0.0 starting...");
		WebServer0 w = new WebServer0();
		w.runServer();		// never returns!!
	}

	/**
	 * Constructor, just create the server socket.
	 */
	WebServer0() {
		try {
			s = new ServerSocket(HTTP);
		} catch(IOException e) {
			System.err.println(e);
			System.exit(0);
			return;
		}
	}

	/** RunServer accepts connections and passes each one to handler. */
	public void runServer() {
		Socket us;		// user socket, gotten from accept()
		while (true) {
			try {
				us = s.accept();
			} catch(IOException e) {
				System.err.println(e);
				System.exit(0);
				return;
			}
			Handler(us);
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
			// TODO new StreamTokenizer(request); to parse it
			System.out.println("Request: " + request);
			String nullLine = is.readLine();
			os = new PrintWriter(s.getOutputStream(), true);
			os.println("HTTP/1.0 200 Here is your data");
			os.println("Content-type: text/html");
			os.println("Server-name: Java WebServer 0");
			String reply = "<HTML><HEAD>" +
				"<TITLE>Wrong System Reached</TITLE></HEAD>\n" +
				"<H1>Welcome, " + from + ", but...</H1>\n" +
				"<P>You have reached a desktop machine " +
				"that does not run a real Web service.\n" +
				"<P>Please pick another system!\n" +
				"<P>Or view <A HREF=\"file:///C:/javasrc/WebServer0.java\">" +
				"the WebServer0 source (at a Course 47x site only)</A> " +
				"or <A HREF=\"http://www.darwinsys.com/java/server.html\">" +
				" at the Course Author's Web Site</A>.\n" +
				"<HR><I>Java-based WebServer0</I><HR>\n" +
				"</HTML>\n";
			os.println("Content-length: " + reply.length());
			os.println("");
			os.println(reply);
			os.flush();
			s.close();
		} catch (IOException e) {
			System.out.println("IOException " + e);
		}
		return;
	}
}
