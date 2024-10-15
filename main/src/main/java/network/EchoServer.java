package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * EchoServer - create server socket, do I-O on it.
 *
 * @author Ian Darwin Copyright (c) 1995-2020 Ian F. Darwin
 */
// tag::main[]
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
		while (true) {
			try {
				System.out.println("Waiting for client...");
				ios = sock.accept();
				System.err.println("Accepted from " +
					ios.getInetAddress().getHostName());
				try (BufferedReader is = new BufferedReader(
							new InputStreamReader(ios.getInputStream(), "8859_1"));
						PrintWriter os = new PrintWriter(
							new OutputStreamWriter(ios.getOutputStream(), "8859_1"),
							true);) {
					String echoLine;
					while ((echoLine = is.readLine()) != null) {
						System.err.println("Read " + echoLine);
						os.print(echoLine + "\r\n");
						os.flush();
						System.err.println("Wrote " + echoLine);
					}
					System.err.println("All done!");
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
// end::main[]
