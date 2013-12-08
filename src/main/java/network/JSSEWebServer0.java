package network;

import java.net.ServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * JSSEWebServer - subclass trivial WebServer0 to make it use SSL.
 */
public class JSSEWebServer0 extends WebServer0 {

	public static final int HTTPS = 8443;
	
	public static void main(String[] args) throws Exception {
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
