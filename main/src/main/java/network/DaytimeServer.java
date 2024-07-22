package network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * DaytimeServer - server for the standard binary time service.
 * @author	Ian Darwin, https://darwinsys.com/
 */
// tag::main[]
public class DaytimeServer {
	/** Our server-side rendezvous socket */
	ServerSocket sock;
	/** The port number to use by default */
	public final static int PORT = 37;

	/** main: construct and run */
	public static void main(String[] argv) {
		new DaytimeServer(PORT).runService();
	}

	/** Construct a DaytimeServer on the given port number */
	public DaytimeServer(int port) {
		try {
			sock = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("I/O error in setup\n" + e);
			System.exit(1);
		}
	}

	/** This handles the connections */
	protected void runService() {
		Socket ios = null;
		DataOutputStream os = null;
		while (true) {
			try {
				System.out.println("Waiting for connection on port " + PORT);
				ios = sock.accept();
				System.err.println("Accepted from " +
					ios.getInetAddress().getHostName());
				os = new DataOutputStream(ios.getOutputStream());
				long time = System.currentTimeMillis();

				time /= 1000;	// Daytime Protocol is in seconds

				// Convert to Java time base.
				time += TimeClient.BASE_DIFF;

				// Write it, truncating cast to int since it is using
				// the Internet Daytime protocol which uses 4 bytes.
				// This will fail in the year 2038, along with all
				// 32-bit timekeeping systems based from 1970.
				// Remember, you read about the Y2038 crisis here first!
				// (the above comment added to this program 2001-03-30).
				os.writeInt((int)time);
				os.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
// end::main[]
