package network;

import java.io.*;
import java.net.*;

/** EchoClient - simple line-mode echo client. Reads from stdin,
 * writes to console.
 * Talks to a UNIX "echo" server or a surrogate for it (EchoServer.java).
 * @author	Ian Darwin, Learning Tree, Course 471/478 author.
 * Copyright (C) 1995, 1996 Ian F. Darwin
 */
public class EchoClient {
	/** Main program: construct an EchoClient object and use
	 * its "converse" method to call the Echo server.
	 */
	public static void main(String[] argv) {
		EchoClient c = new EchoClient();
		c.converse(argv.length==1?argv[0]:"localhost");
	}

	/** Hold one conversation with the named hosts echo server */
	protected void converse(String hostname) {
		Socket sock = null;
		try {
			sock = new Socket(hostname, 7);	// echo server.
			BufferedReader stdin = new BufferedReader(
				new InputStreamReader(System.in));
			BufferedReader is = new BufferedReader(
				new InputStreamReader(sock.getInputStream(), "8859_1"));
			PrintWriter os = new PrintWriter(
				new OutputStreamWriter(
					sock.getOutputStream(), "8859_1"), true);

			String line;
			do {
				System.out.print(">> ");
				if ((line = stdin.readLine()) == null)
					break;
				// Do the CRLF ourself since println appends only a \r on
				// platforms where that is the native line ending.
				os.print(line + "\r\n");
				os.flush();
				String reply = is.readLine();
				System.out.print("<< ");
				System.out.println(reply);
			} while (line != null);
		} catch (IOException e) {	// handles all input/output errors
			System.err.println(e);
		} finally {					// cleanup
			try {
				if (sock != null)
					sock.close();
			} catch (IOException ignoreMe) {
				// nothing
			}
		}
	}
}
