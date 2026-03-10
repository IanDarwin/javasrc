package netweb;

import com.sun.net.httpserver.SimpleFileServer;
import static com.sun.net.httpserver.SimpleFileServer.OutputLevel;
import java.net.InetSocketAddress;
import java.nio.file.Path;

public class SimpleServerDemo {

	public static final int PORT = 8910;

// tag::main[]
	void main(String[] args) {
		var addr = new InetSocketAddress(PORT);
		var path = Path.of(
			args.length == 0 ?
				System.getProperty("user.home") + "/public_html" :
				args[0]).toAbsolutePath();
		var server = SimpleFileServer.createFileServer(addr, path, OutputLevel.INFO);
		System.out.printf("Starting listening on port %d to serve %s\n", PORT, path);
		server.start();
	}
// end::main[]
}
