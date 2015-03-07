package network;

import java.io.*;
import java.net.*;
/**
 * EchoClientOneLine - create client socket, send one line,
 * read it back. See also EchoClient.java, slightly fancier.
 */
// BEGIN main
public class EchoClientOneLine {
	/** What we send across the net */
	String mesg = "Hello across the net";

	public static void main(String[] argv) {
		if (argv.length == 0)
			new EchoClientOneLine().converse("localhost");
		else
			new EchoClientOneLine().converse(argv[0]);
	}

	/** Hold one conversation across the net */
	protected void converse(String hostName) {
		try (Socket sock = new Socket(hostName, 7);) { // echo server.
			BufferedReader is = new BufferedReader(new 
				InputStreamReader(sock.getInputStream()));
			PrintWriter os = new PrintWriter(sock.getOutputStream(), true);
			// Do the CRLF ourself since println appends only a \r on
			// platforms where that is the native line ending.
			os.print(mesg + "\r\n"); os.flush();
			String reply = is.readLine();
			System.out.println("Sent \"" + mesg  + "\"");
			System.out.println("Got  \"" + reply + "\"");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
// END main
