import java.net.*;
import java.io.*;

/**
 * Telnet - connect to a given host and service
 * This does not hold a candle to a real Telnet client, but
 * shows some ideas on how to implement such a thing.
 * @version $Id$
 */
public class Telnet {
	public static void main(String argv[]) {
		new Telnet().talkTo(argv);
	}
	private void talkTo(String av[]) {
		String host = av[0];
		String serv = av[1];
		int servNum = Integer.parseInt(serv);
		System.out.println("Host" + host + "; serv " + serv + "/" +
			servNum);
		try {
			Socket s = new Socket(host, servNum);

			// Connect the remote to our stdout
			new Pipe(new DataInputStream(s.getInputStream()),
				new DataOutputStream(System.out)).start();

			// Connect out stdin to the remote
			new Pipe(new DataInputStream(System.in),
			new DataOutputStream(s.getOutputStream())).start();

		} catch(IOException e) {
			System.out.println(e);
			return;
		}
		System.out.println("Connected OK");
	}
}

/** This class handles one side of the connection. */
class Pipe extends Thread {
	DataInputStream is;
	DataOutputStream os;

	// Constructor
	Pipe(DataInputStream is, DataOutputStream os) {
		this.is = is;
		this.os = os;
	}

	// Do something method
	public void run() {
		String line;
		try {
			while ((line = is.readLine()) != null)
				os.writeChars(line + "\r\n");
		} catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}

