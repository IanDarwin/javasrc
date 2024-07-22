package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Listen -- make a ServerSocket and wait for connections.
 * @author Ian F. Darwin
 */
// tag::main[]
public class Listen {
	/** The TCP port for the service. */
	public static final short PORT = 9999;

	public static void main(String[] argv) throws IOException {
		ServerSocket sock;
		Socket  clientSock;
		try {
			sock = new ServerSocket(PORT);
			System.out.println("Listening on " + PORT);
			while ((clientSock = sock.accept()) != null) {

				// Process it, usually on a separate thread
				// to avoid blocking the accept() call.
				process(clientSock);
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/** This would do something with one client. */
	static void process(Socket s) throws IOException {
		System.out.println("Processing request from client " +
			s.getInetAddress());
		// The conversation would be here.
		s.close();
	}
}
// end::main[]
