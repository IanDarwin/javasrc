import java.net.*;
import java.io.*;
import java.util.*;

/**
 * A very very simple Web server.
 *
 * NO SECURITY. ALMOST NO CONFIGURATION.
 * NO CGI. NO SERVLETS.
 *
 * This version is threaded. I/O is done in Handler.
 *
 * TODO
 *	Web Standard logfile formats.
 *	More property definitions...
 */
public class Httpd {
	/** The default port number */
	public static final int HTTP = 80;
	/** The server socket used to connect from clients */
	protected ServerSocket sock;
	/** A Properties, for loading configuration info */
	protected Properties wsp;
	/** A Properties, for loading mime types into */
	protected Properties mimeTypes;
	/** The root directory */
	protected String rootDir;

	public static void main(String argv[]) {
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
	void runServer() {
		while (true) {
			try {
				Socket clntSock = sock.accept();
				new Handler(this, clntSock).start();
			} catch(IOException e) {
				System.err.println(e);
			}
		}
	}

	/** Construct a server object for a given port number */
	Httpd() {
		super();
		// A ResourceBundle can't load from the same basename as your class,
		// but a simple Properties can.
		wsp=loadProps("httpd.properties");
		rootDir = wsp.getProperty("rootDir", ".");
		mimeTypes = loadProps(wsp.getProperty("mimeProperties", "mime.properties"));
	}

	public void startServer(int portNum) {
		String portNumString = null;
		if (portNum == HTTP) {
			portNumString = wsp.getProperty("portNum");
			if (portNumString != null) {
				portNum = Integer.parseInt(portNumString);
			}
		}
		try {
			sock = new ServerSocket(portNum);
			System.out.println("Listening on port " + portNum);
		} catch(NumberFormatException e) {
			System.err.println("Httpd: \"" + portNumString +
				"\" not a valid number, unable to start server");
			System.exit(1);
		} catch(IOException e) {
			System.err.println("Network error " + e);
			System.err.println("Unable to start server");
			System.exit(1);
		}
	}

	/** Load the Properties. */
	protected Properties loadProps(String fname) {
		Properties sp = new Properties();

		try {
			// Create input file to load from.
			FileInputStream ifile = new FileInputStream(fname);

			sp.load(ifile);
		} catch (FileNotFoundException notFound) {
			System.err.println(notFound);
			System.exit(1);
		} catch (IOException badLoad) {
			System.err.println(badLoad);
			System.exit(1);
		}
		return sp;
	}
}
