import java.io.*;
import java.net.*;

public class SocketServer extends Thread {
	public static void main(String[] argv) throws IOException {
		new SocketServer().start();
	}

	/** The hang-around time */
	final static int MINUTES = 1;
	/** The port number */
	public final static int PORT = 64208;
	/** The server socket. */
	ServerSocket ss;

	/** Constructor -- just creates the ServerSocket */
	public SocketServer() throws IOException {
		ss = new ServerSocket(PORT);
	}

	public void run() {
		while (true) {
			try {
			System.out.println("SocketServer waiting for connection");
			Socket s = ss.accept();
			BufferedReader is = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
			String name = is.readLine();
			String passwd = is.readLine();
			String domain = is.readLine();
			PrintWriter pout = new PrintWriter(s.getOutputStream(), true);
			pout.println("Welcome to " + domain + ", " + name);
			} catch (IOException e) {
				System.err.println("Oh, dear me! " + e);
			}
		} 
	}
}
