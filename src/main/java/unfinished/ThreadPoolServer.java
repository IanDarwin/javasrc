import java.net.*;
import java.io.*;

/**
 * A very simple network server using a simple Thread pool.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 * @version	$Id$
 */
public class ThreadPoolServer implements Runnable {
	public static final int PORT = 2000;
	public static final int POOL_MIN = 10;
	ServerSocket s;

	/**
	 * Main method, just creates a server and call its runServer().
	 */
	public static void main(String[] argv) {
		System.out.println("Thread Pool Server 0.0 starting...");
		ThreadPoolServer w = new ThreadPoolServer();
		w.runServer();		// never returns!!
	}

	/**
	 * Constructor, just create the server socket.
	 */
	ThreadPoolServer() {
		try {
			s = new ServerSocket(HTTP);
		} catch(IOException e) {
			System.err.println(e);
			System.exit(0);
			return;
		}
		for (int i = 0; i<POOL_MIN; i++) {
			new Thread(this).start();
		}
	}

	/** RunServer accepts connections and passes each one to handler. */
	public void run() {
		Socket us;		// user socket, gotten from accept()
		while (true) {
			try {
				us = s.accept();
			} catch(IOException e) {
				System.err.println(e);
				System.exit(0);
				return;
			}
			getThread();
			handler(us);
			freeThread();
		}
	}

	/** handler() handles one conversation with a client.
	 */
	public void handle(Socket s) {
		BufferedReader is;	// inputStream, from Viewer
		PrintWriter os;		// outputStream, to Viewer
		try {
			String from = s.getInetAddress().toString();
			System.out.println(Thread.getCurrent().getName() +
				"Accepted connection from " + from);
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
				" at the Course Authors Web Site</A>.\n" +
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
