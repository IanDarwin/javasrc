import java.io.*;
import java.net.*;

/**
 * EchoServer - create server socket, do I-O on it.
 *
 * @author	Ian Darwin, Learning Tree, Course 471/478
 * @version Copyright (c) 1995, 1996, 1997 Learning Tree International
 */
public class EchoServer {
	/** Our server-side rendezvous socket */
	ServerSocket sock;
	/** The port number to use by default */
	public final static int ECHOPORT = 7;
	/** Flag to control debugging */
	protected boolean debug = true;

	/** main: construct and run */
	public static void main(String[] argv) {
		new EchoServer(ECHOPORT).handle();
	}

	/** Construct an EchoServer on the given port number */
	public EchoServer(int port) {
		try {
			sock = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("I/O error in setup\n" + e);
			System.exit(1);
		}
	}

	/** This handles the connections */
	protected void handle() {
		Socket ios = null;
		BufferedReader is = null;
		PrintWriter os = null;
		while (true) {
			try {
				ios = sock.accept();
				System.err.println("Accepted from " +
					ios.getInetAddress().getHostName());
				is = new BufferedReader(
					new InputStreamReader(ios.getInputStream(), "8859_1"));
				os = new PrintWriter(
						new OutputStreamWriter(
							ios.getOutputStream(), "8859_1"), true);
				String echoLine;
				while ((echoLine = is.readLine()) != null) {
					System.err.println("Read " + echoLine);
					os.print(unicodeLine + "\r\n");
					System.err.println("Wrote " + echoLine);
				}
				System.err.println("All done!");
			} catch (IOException e) {
				System.err.println(e);
			} finally {
				try {
					if (is != null)
						is.close();
					if (os != null)
						os.close();
					if (ios != null)
						ios.close();
				} catch (IOException e) {
					// nothing
				}
			}
		}
		/*NOTREACHED*/
	}
}
