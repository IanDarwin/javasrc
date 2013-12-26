package network;

import java.io.*;
import java.net.*;

/**
 * DaytimeServer - server for the standard binary time service.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
// BEGIN main
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

				time /= RDateClient.MSEC;	// Daytime Protocol is in seconds

				// Convert to Java time base.
				time += RDateClient.BASE_DIFF;

				// Write it, truncating cast to int since it is using
				// the Internet Daytime protocol which uses 4 bytes.
				// This will fail in the year 2038, along with all
				// 32-bit timekeeping systems based from 1970.
				// Remember, you read about the Y2038 crisis here first!
				os.writeInt((int)time);
				os.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
// END main
