package network;

import java.io.*;
import java.net.*;

/**
 * Listen -- make a ServerSocket and wait for connections.
 * @author Ian F. Darwin
 */
// BEGIN main
public class Listen {
	/** The TCP port for the service. */
	public static final short PORT = 9999;

	public static void main(String[] argv) throws IOException {
		ServerSocket sock;
		Socket  clientSock;
		try {
			sock = new ServerSocket(PORT);
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
		System.out.println("Accept from client " + s.getInetAddress());
		// The conversation would be here.
		s.close();
	}
}
// END main
