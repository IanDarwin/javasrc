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
 * @author	Ian Darwin, https://darwinsys.com/
 */
// tag::main[]
public class WebServer0 {
	public static final int HTTP = 8080;
	public static final String CRLF = "\r\n";
	ServerSocket s;
	/** A link to the source of this program, used in error message */
	static final String VIEW_SOURCE_URL =
	"https://github.com/IanDarwin/javasrc/tree/master/main/src/main/java/network";

	/**
	 * Main method, just creates a server and call its runServer().
	 */
	public static void main(String[] args) throws Exception {
		WebServer0 w = new WebServer0();
		int port = HTTP;
		if (args.length == 1) {
			port = Integer.parseInt(args[0]);
			}
		System.out.println("DarwinSys JavaWeb Server 0.0 starting on " + port);
		w.runServer(port);		// never returns!!
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
			System.out.printf("Request: %s from %s\n", request, from);
			
			os = new PrintWriter(s.getOutputStream(), true);
			os.print("HTTP/1.0 200 Here is your data" + CRLF);
			os.print("Content-type: text/html" + CRLF);
			os.print("Server-name: DarwinSys NULL Java WebServer 0" + CRLF);
			final String replyTemplate = """
				<html>
				<head>
				  <title>Wrong System Reached</title>
				  <meta name='viewport' content='width=device-width, initial-scale=1'/>
				</head>
				<body>
				<h1>Welcome, %s, but...</h1>
				<p>You have reached a desktop machine 
				that does not run a real Web service.
				<p>Please pick another system!</p>
				<p>Or view <a href='%s'>
				this WebServer0 source on github</a>
				(scroll down to WebServer0.java).</p>
				<hr/>
				<em>Java-based WebServer0</em><hr/>
				</body>
				</html>
				""";
			String reply = replyTemplate.formatted(from, VIEW_SOURCE_URL);
			os.print("Content-length: " + reply.length() + CRLF);
			os.print(CRLF);
			os.print(reply + CRLF);
			os.flush();
			s.close();
		} catch (IOException e) {
			System.out.println("IOException " + e);
		}
		return;
	}
}
// end::main[]
