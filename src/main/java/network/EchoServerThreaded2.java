package network;

import java.net.*;
import java.io.*;

/**
 * Threaded Echo Server, pre-allocation scheme.
 * Each Thread waits in its accept() call for a connection; this synchronizes
 * on the serversocket when calling its accept() method.
 * @author Ian F. Darwin.
 */
// BEGIN main
public class EchoServerThreaded2 {

	public static final int ECHOPORT = 7;

	public static final int NUM_THREADS = 4;

	/** Main method, to start the servers. */
	public static void main(String[] av) {
		new EchoServerThreaded2(ECHOPORT, NUM_THREADS);
	}

	/** Constructor */
	public EchoServerThreaded2(int port, int numThreads) {
		ServerSocket servSock;

		try {
			servSock = new ServerSocket(port);

		} catch(IOException e) {
			/* Crash the server if IO fails. Something bad has happened */
			throw new RuntimeException("Could not create ServerSocket ", e);
		}

		// Create a series of threads and start them.
		for (int i=0; i<numThreads; i++) {
			new Handler(servSock, i).start();
		}
	}

	/** A Thread subclass to handle one client conversation. */
	class Handler extends Thread {
		ServerSocket servSock;
		int threadNumber;

		/** Construct a Handler. */
		Handler(ServerSocket s, int i) {
			servSock = s;
			threadNumber = i;
			setName("Thread " + threadNumber);
		}

		public void run() {
			/* Wait for a connection. Synchronized on the ServerSocket
			 * while calling its accept() method.
			 */
			while (true) {
				try {
					System.out.println( getName() + " waiting");

					Socket clientSocket;
					// Wait here for the next connection.
					synchronized(servSock) {
						clientSocket = servSock.accept();
					}
					System.out.println(getName() + " starting, IP=" +
						clientSocket.getInetAddress());
					BufferedReader is = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
					PrintStream os = new PrintStream(
						clientSocket.getOutputStream(), true);
					String line;
					while ((line = is.readLine()) != null) {
						os.print(line + "\r\n");
						os.flush();
					}
					System.out.println(getName() + " ENDED ");
					clientSocket.close();
				} catch (IOException ex) {
					System.out.println(getName() + ": IO Error on socket " + ex);
					return;
				}
			}
		}
	}
}
// END main
