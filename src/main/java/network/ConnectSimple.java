package network;

import java.net.Socket;

/* Client with NO error handling */
public class ConnectSimple {

	public static void main(String[] argv) throws Exception {

		Socket sock = new Socket("localhost", 8080);

		System.out.println(" *** Connected OK ***");

		sock.close();
	}
}
