package network;

import java.net.ServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

// BEGIN main
/**
 * JSSEWebServer - subclass trivial WebServer0 to make it use SSL.
 * N.B. You MUST have set up a server certificate (see the
 * accompanying book text), or you will get the dreaded
 * javax.net.ssl.SSLHandshakeException: no cipher suites in common
 * (because without it JSSE can't use any of its built-in ciphers!).
 */
public class JSSEWebServer0 extends WebServer0 {

	public static final int HTTPS = 8443;
	
	public static void main(String[] args) throws Exception {
		if (System.getProperty("javax.net.ssl.keyStore") == null) {
			System.err.println(
				"You must pass in a keystore via -D; see the documentation!");
			System.exit(1);
		}
		System.out.println("DarwinSys JSSE Server 0.0 starting...");
		JSSEWebServer0 w = new JSSEWebServer0();
		w.runServer(HTTPS);		// never returns!!
	}

	/** Get an HTTPS ServerSocket using JSSE.
	 * @see WebServer0#getServerSocket(int)
	 * @throws ClassNotFoundException if the SecurityProvider cannot be instantiated.
	 */
	protected ServerSocket getServerSocket(int port) throws Exception {
		
		SSLServerSocketFactory ssf =
			(SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
		
		return ssf.createServerSocket(port);
	}

}
// END main
