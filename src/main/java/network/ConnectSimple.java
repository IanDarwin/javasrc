package network;

// BEGIN main
import java.net.Socket;

/* Client with NO error handling */
public class ConnectSimple {

	public static void main(String[] argv) throws Exception {

		try (Socket sock = new Socket("localhost", 8080)) {

			/* If we get here, we can read and write on the socket "sock" */
			System.out.println(" *** Connected OK ***");

			/* Do some I/O here... */

		}
	}
}
// END main
