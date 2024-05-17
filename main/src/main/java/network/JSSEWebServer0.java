package network;

import java.net.ServerSocket;
import java.io.IOException;
import javax.net.ssl.SSLServerSocketFactory;

// tag::main[]
/**
 * JSSEWebServer - subclass trivial WebServer0 to make it use SSL.
 * N.B. You MUST have set up a server certificate (see the
 * accompanying book text), or you will get the dreaded
 * javax.net.ssl.SSLHandshakeException: no cipher suites in common
 * (because without it JSSE can't use any of its built-in ciphers!).
 */
public class JSSEWebServer0 extends WebServer0 {

	public static final int HTTPS_PORT = 8443;

	public JSSEWebServer0() throws IOException {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		if (System.getProperty("javax.net.ssl.keyStore") == null) {
			System.err.println(
				"You must pass in a keystore via -D; see the documentation!");
			System.exit(1);
		}
		System.out.println("DarwinSys JSSE Server 0.0 starting...");
		SSLServerSocketFactory ssf =
			(SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
		var sock = ssf.createServerSocket(HTTPS_PORT);
		WebServer0 w = new WebServer0(sock);
		w.runServer();		// never returns!!
	}
}
// end::main[]
