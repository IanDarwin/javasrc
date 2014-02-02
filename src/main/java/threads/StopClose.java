package threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/** Stop a thread by closing a socket.
 */
// BEGIN main
public class StopClose extends Thread {
	protected Socket io;

	public void run() {
		try {
			io = new Socket("java.sun.com", 80);	// HTTP
			BufferedReader is = new BufferedReader(
				new InputStreamReader(io.getInputStream()));
			System.out.println("StopClose reading");

			// The following line will deadlock (intentionally), since HTTP 
			// enjoins the client to send a request (like "GET / HTTP/1.0")
			// and a null line, before reading the response.

			String line = is.readLine();	// DEADLOCK

			// Should only get out of the readLine if an interrupt
			// is thrown, as a result of closing the socket.

			// So we shouldn't get here, ever:
			System.out.printf("StopClose FINISHED after reading %s!?", line);
		} catch (IOException ex) {
			System.out.println("StopClose terminating: " + ex);
		}
	}

	public void shutDown() throws IOException {
		if (io != null) {
			// This is supposed to interrupt the waiting read.
			synchronized(io) {
				io.close();
			}
		}
		System.out.println("StopClose.shutDown() completed");
	}

	public static void main(String[] args) 
	throws InterruptedException, IOException {
		StopClose t = new StopClose();
		t.start();
		Thread.sleep(1000*5);
		t.shutDown();
	}
}
// END main
