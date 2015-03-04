package unfinished;

import java.net.*;
import java.io.*;

/**
 * A very very simple proxy server.
 *
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class Proxy {
	/** port number we bind to on machine where proxy runs */
	int localPort;
	/** destination we forward to: host name/address */
	String destIP;
	/** destination we forward to: TCP port number */
	int destPort;

	/**
	 * Main method, just creates a server and call its runServer().
	 */
	public static void main(String[] argv) {
		if (argv.length != 3) {
			System.err.println("usage: proxy port destIP destport");
			System.exit(0);
		}
		System.out.println("DarwinSys Proxy Server 0.0 starting...");
		Proxy p = new Proxy(argv[0], argv[1], argv[2]);
		p.runServer();		// never returns!!
	}

	/**
	 * Constructor, just create the server socket.
	 */
	Proxy(String port, String destIP, String destPort) {
		this.localPort = Integer.parseInt(port);
		this.destIP = destIP;
		this.destPort = Integer.parseInt(destPort);
	}

	/** RunServer accepts connections and passes each one to handler. */
	public void runServer() {
		try (ServerSocket s = new ServerSocket(localPort);) {
			while (true) {
				Socket us = s.accept();
				// String from = s.getInetAddress().toString();
				// System.out.println("Accepted connection from " + from);
				Socket dest = new Socket(destIP, destPort);
				new Copier(us, dest).start();
				new Copier(dest, us).start();
			}
		} catch(IOException e) {
			System.err.println(e);
			System.exit(0);
			return;
		}
	}

	class Copier extends Thread {
		Socket from;
		Socket to;
		Copier(Socket from, Socket to) {
			this.from = from;
			this.to   = to;
		}

		public String toString() {
			// return "Copier["+from.getInetAddress()+"-->"
			//	+to.getInetAddress()+"]";
			return "Copier";
		}

		public void run() {
			System.out.println("Starting " + this);
			try {
				String aline;
				BufferedReader is = 
					new BufferedReader(new InputStreamReader(from.getInputStream()));
				PrintWriter os = new PrintWriter(to.getOutputStream(), true);
				while ((aline = is.readLine()) != null) {
					System.out.println(">> " + aline);
					os.println(aline);
				}
				is.close();
				from.close();
				os.flush();
				os.close();
				to.close();
			} catch (IOException e) {
				throw new IllegalArgumentException(e.toString());
			}
		}
	}
}
