package network;

import java.net.*;
import java.io.*;

/**
 * Telnet - connect to a given host and service
 * This does not hold a candle to a real Telnet client, but
 * shows some ideas on how to implement such a thing.
 */
// BEGIN main
public class Telnet {
	String host;
	int portNum;
	public static void main(String[] argv) {
		new Telnet().talkTo(argv);
	}
	private void talkTo(String av[]) {
		if (av.length >= 1)
			host = av[0];
		else
			host = "localhost";
		if (av.length >= 2)
			portNum = Integer.parseInt(av[1]);
		else portNum = 23;
		System.out.println("Host " + host + "; port " + portNum);
		try (Socket s = new Socket(host, portNum);) {

			// Connect the remote to our stdout
			new Pipe(s.getInputStream(), System.out).start();

			// Connect our stdin to the remote
			new Pipe(System.in, s.getOutputStream()).start();

		} catch(IOException e) {
			System.out.println(e);
			return;
		}
		System.out.println("Connected OK");
	}

	/* This class handles one half of a full-duplex connection.
	 * Line-at-a-time mode.
	 */
	class Pipe extends Thread {
		BufferedReader is;
		PrintStream os;

		/** Construct a Pipe to read from is and write to os */
		Pipe(InputStream is, OutputStream os) {
			this.is = new BufferedReader(new InputStreamReader(is));
			this.os = new PrintStream(os);
		}

		/** Do the reading and writing. */
		public void run() {
			String line;
			try {
				while ((line = is.readLine()) != null) {
					os.print(line);
					os.print("\r\n");
					os.flush();
				}
			} catch(IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
}
// END main
