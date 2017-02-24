package network.webserver;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import com.darwinsys.util.FileProperties;

// BEGIN main
/**
 * A very very simple Web server.
 * <p>
 * NO SECURITY. ALMOST NO CONFIGURATION. NO CGI. NO SERVLETS.
 *<p>
 * This version is threaded. I/O is done in Handler.
 */
public class Httpd {
	/** The default port number */
	public static final int HTTP = 80;
	/** The server socket used to connect from clients */
	protected ServerSocket sock;
	/** A Properties, for loading configuration info */
	private Properties wsp;
	/** A Properties, for loading mime types into */
	private Properties mimeTypes;
	/** The root directory */
	private String rootDir;

	public static void main(String argv[]) throws Exception {
		System.out.println("DarwinSys JavaWeb Server 0.1 starting...");
		Httpd w = new Httpd();
		if (argv.length == 2 && argv[0].equals("-p")) {
			w.startServer(Integer.parseInt(argv[1]));
		} else {
			w.startServer(HTTP);
		}
		w.runServer();
		// NOTREACHED
	}

	/** Run the main loop of the Server. Each time a client connects,
	 * the ServerSocket accept() returns a new Socket for I/O, and
	 * we pass that to the Handler constructor, which creates a Thread,
	 * which we start.
	 */
	void runServer() throws Exception  {
		while (true) {
				final Socket clntSock = sock.accept();
				Thread t = new Thread(){
					public void run() {
						new Handler(Httpd.this).process(clntSock);
					}
				};
				t.start();
		}
	}

	/** Construct a server object for a given port number */
	Httpd() throws Exception {
		wsp=new FileProperties("httpd.properties");
		rootDir = wsp.getProperty("rootDir", ".");
		mimeTypes = 
			new FileProperties(
				wsp.getProperty("mimeProperties",
					"mime.properties"));
	}

	public void startServer(int portNum) throws Exception {
		String portNumString = null;
		if (portNum == HTTP) {
			portNumString = wsp.getProperty("portNum");
			if (portNumString != null) {
				portNum = Integer.parseInt(portNumString);
			}
		}
		sock = new ServerSocket(portNum);
		System.out.println("Listening on port " + portNum);
	
	}

	public String getMimeType(String type) {
		return mimeTypes.getProperty(type);
	}
	public String getMimeType(String type, String dflt) {
		return mimeTypes.getProperty(type, dflt);
	}
	public String getServerProperty(String name) {
		return wsp.getProperty(name);
	}

	public String getRootDir() {
		return rootDir;
	}
}
// END main
