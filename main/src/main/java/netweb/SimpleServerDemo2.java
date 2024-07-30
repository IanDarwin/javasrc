package netweb;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.SimpleFileServer;
import static com.sun.net.httpserver.SimpleFileServer.OutputLevel;
import java.io.*;
import java.net.*;
import java.nio.file.*;

// tag::main[]
public class SimpleServerDemo2 {

	public static final int PORT = 8910;

	/** This Handler checks for exploit attempts, but doesn't
	 * actually handle mapping the URL to local files yet.
	 * "Left as an exercise for the reader."
	 */
	HttpHandler handler = (HttpExchange exch) -> {
			int status;
			String response;
			String url = exch.getRequestURI().toString();
			System.out.println("Request for " + url);
			if (is0dayUrl(url)) {
				response = "Scammer, go get a real job";
				status = 403;
			} else {
				response = "This is the response";
				status = 200;
			}
			exch.sendResponseHeaders(status, response.length());
			OutputStream os = exch.getResponseBody();
			os.write(response.getBytes());
			os.close();
	 };

	void main() throws IOException {
		var addr = new InetSocketAddress(PORT);
		var server = HttpServer.create(addr, 0);
		server.createContext("/", handler);
		System.out.printf("Starting listening on port %d to serve %s\n", PORT, "/");
		server.start();
	}
	// end::main[]

	/**
	 * Non-serious demo - needs a much longer list of exploit URLs
	 */
	private boolean is0dayUrl(String url) {
		return url.contains("/exploit");
	}
}
