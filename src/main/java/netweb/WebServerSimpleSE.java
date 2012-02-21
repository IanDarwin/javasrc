package netweb;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Date;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/** Exercise the simple HTTP server built into Java SE 6+ */
public class SimpleServerSE {

	private static final int PORT = 8800;

	public static void main(String[] args) throws IOException {
		
		HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 5);
		server.createContext("/", new HttpHandler() {			
			@Override
			public void handle(HttpExchange rAndR) throws IOException {
				System.out.println("Server...handle()");
				
				// Clients are expected to readfully the inputstream.
				InputStream is = rAndR.getRequestBody();
		        int j;
				do {
		        	j = is.read();
		        } while (j != -1);
				is.close();
		           
				final String template = "<html><p>Hello at %s!</p></html>";
				String message = String.format(template, new Date());
				
				rAndR.sendResponseHeaders(200, message.length());
				rAndR.getResponseBody().write(message.getBytes());
				rAndR.getResponseBody().close();
			}
		});
		server.setExecutor(null); // "We don't need no ex-e-cute-or, ..."
		server.start();
		System.out.println("Listening on " + server.getAddress());
	}
}
