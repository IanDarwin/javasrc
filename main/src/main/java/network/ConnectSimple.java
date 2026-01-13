 package network;

// tag::main[]
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

/* Client with NO error handling */
public class ConnectSimple {

	public static void main(String[] argv) throws Exception {

		try (Socket sock = new Socket("suggestqueries.google.com", 80);
			PrintStream pout = new PrintStream(sock.getOutputStream());
			BufferedReader is = 
				new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

			pout.println("GET /complete/search?client=firefox&q=darwin HTTP/1.0\r");
			pout.println("\r");

			String line;
			while ((line = is.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
// end::main[]
