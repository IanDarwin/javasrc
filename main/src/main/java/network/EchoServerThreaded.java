package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Threaded Echo Server, sequential allocation scheme.
 * 
 * @author Ian F. Darwin.
 */
// tag::main[]
public class EchoServerThreaded {

	final static ExecutorService threadPool =
		Executors.newVirtualThreadPerTaskExecutor();
	public static final int ECHOPORT = 2007;

	public static void main(String[] av) {
		new EchoServerThreaded().runServer();
	}

	public void runServer() {
		ServerSocket sock;
		Socket clientSocket;

		try {
			sock = new ServerSocket(ECHOPORT);

			System.out.println("EchoServerThreaded ready for connections.");

			/* Wait for a connection */
			while (true) {
				clientSocket = sock.accept();
				/* Create a thread to do the communication, and start it */
				new Handler(clientSocket).handle();
			}
		} catch (IOException e) {
			/* Crash the server if IO fails. Something bad has happened */
			System.err.println("Could not accept " + e);
			System.exit(1);
		}
	}

	/** A Thread subclass to handle one client conversation. */
	class Handler {
		final Socket sock;

		Handler(Socket s) {
			sock = s;
		}

		public void handle() {
			System.out.println("Socket starting: " + sock);
			threadPool.submit( () -> {
				try (BufferedReader is = new BufferedReader(
							new InputStreamReader(sock.getInputStream()));
						PrintStream os = new PrintStream(
							sock.getOutputStream(), true);) {
					String line;
					while ((line = is.readLine()) != null) {
						os.print(line + "\r\n");
						os.flush();
					}
					sock.close();
				} catch (IOException e) {
					System.out.println("IO Error on socket " + e);
					return;
				}
				System.out.println("Socket ENDED: " + sock);
			});
		}
	}
}
// end::main[]
