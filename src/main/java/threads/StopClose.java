import java.io.*;
import java.net.*;

public class StopClose extends Thread {
	protected Socket io;

	public void run() {
		try {
			io = new Socket("localhost", 80);	// HTTP
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
			System.out.println("StopClose FINISHED!?");
		} catch (IOException ex) {
			System.err.println("StopClose terminating: " + ex);
		}
	}

	public void shutDown() throws IOException {
		if (io != null) {
			// This is supposed to interrupt the waiting read.
			io.close();
		}
	}
}
