package network;

import java.io.*;
import java.net.*;

/**
 * EchoServer - create server socket, do I-O on it.
 *
 * @author  Ian Darwin
 * Copyright (c) 1995, 1996, 1997, 2000 Ian F. Darwin
 */
// BEGIN main
public class EchoServer {
	/** Our server-side rendezvous socket */
	protected ServerSocket sock;
	/** The port number to use by default */
	public final static int ECHOPORT = 7;
	/** Flag to control debugging */
	protected boolean debug = true;

	/** main: construct and run */
	public static void main(String[] args) {
		int p = ECHOPORT;
		if (args.length == 1) {
			try {
				p = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Usage: EchoServer [port#]");
				System.exit(1);
			}
		}
		new EchoServer(p).handle();
	}

	/** Construct an EchoServer on the given port number */
	public EchoServer(int port) {
		try {
			sock = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("I/O error in setup");
			System.err.println(e);
			System.exit(1);
		}
	}

	/** This handles the connections */
	protected void handle() {
		Socket ios = null;
		BufferedReader is = null;
		PrintWriter os = null;
		while (true) {
			try {
				System.out.println("Waiting for client...");
				ios = sock.accept();
				System.err.println("Accepted from " +
					ios.getInetAddress().getHostName());
				is = new BufferedReader(
					new InputStreamReader(ios.getInputStream(), "8859_1"));
				os = new PrintWriter(
						new OutputStreamWriter(
							ios.getOutputStream(), "8859_1"), true);
				String echoLine;
				while ((echoLine = is.readLine()) != null) {
					System.err.println("Read " + echoLine);
					os.print(echoLine + "\r\n");
					os.flush();
					System.err.println("Wrote " + echoLine);
				}
				System.err.println("All done!");
			} catch (IOException e) {
				System.err.println(e);
			} finally {
				try {
					if (is != null)
						is.close();
					if (os != null)
						os.close();
					if (ios != null)
						ios.close();
				} catch (IOException e) {
					// These are unlikely, but might indicate that
					// the other end shut down early, a disk filled up
					// but wasn't detected until close, etc.
					System.err.println("IO Error in close");
				}
			}
		}
		/*NOTREACHED*/
	}
}
// END main
