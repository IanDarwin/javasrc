import java.io.*;
import java.net.*;

/**
 * ListenInside -- make a server socket that listens only on
 * a particular interface, in this case, one called "inside".
 * @author Ian F. Darwin
 * @version $Id$
 */
public class ListenInside {
	/** The TCP port for the service. */
	public static final short PORT = 9999;
	/** The name of the network interface. */
	public static final String INSIDE_HOST = "acmewidgets-inside";
	/** The number of clients allowed to queue */
	public static final int BACKLOG = 10;

	public static void main(String[] argv) throws IOException {
		ServerSocket sock;
		Socket  clientSock;
		try {
			sock = new ServerSocket(PORT, BACKLOG, 
				InetAddress.getByName(INSIDE_HOST));
			while ((clientSock = sock.accept()) != null) {

				// Process it.
				process(clientSock);
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/** This would do something with one client. */
	static void process(Socket s) throws IOException {
		System.out.println("Accept from inside " + s.getInetAddress());
		// The conversation would be here.
		s.close();
	}
}
