package network;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.UnknownHostException;

/* Client with error handling */
public class ConnectFriendly {
	public static void main(String[] argv) {
		String server_name = argv.length == 1 ? argv[0] : "localhost";
		int tcp_port = 80;
		try {
			Socket sock = new Socket(server_name, tcp_port);

			/* Finally, we can read and write on the socket. */
			System.out.println(" *** Connected to " + server_name  + " ***");
			/* ... */

			sock.close();

		} catch (UnknownHostException e) {
			System.err.println(server_name + " Unknown host");
			return;
		} catch (NoRouteToHostException e) {
			System.err.println(server_name + " Unreachable" );
			return;
		} catch (ConnectException e) {
			System.err.println(server_name + " connect refused");
			return;
		} catch (java.io.IOException e) {
			System.err.println(server_name + ' ' + e.getMessage());
			return;
		}
	}
}
