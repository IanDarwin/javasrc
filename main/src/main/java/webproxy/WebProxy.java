package webproxy;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.*;

import com.darwinsys.util.FileProperties;

/**
 * HttpConcurrent - Httpd Subclass using java.lang.concurrent
 */
public class WebProxy {
	/** The default port number; the same as "squid" uses. */
	public static final int HTTP_PROXY = 3128;
	/** The server socket used to connect from clients */
	protected ServerSocket serverSocket;
	/** A Properties, for loading configuration info */
	private Properties wsp;
	/** The root directory */
	private String rootDir;
	Executor myThreadPool = Executors.newFixedThreadPool(5);

	public WebProxy() throws Exception {
		super();
		wsp=new FileProperties("httpd.properties");
		rootDir = wsp.getProperty("rootDir", ".");
	}
	
	public static void main(String[] argv) throws Exception {
		System.out.println("DarwinSys JavaWeb Proxy 0.1 starting...");
		WebProxy w = new WebProxy();
		if (argv.length == 2 && argv[0].equals("-p")) {
			w.startServer(Integer.parseInt(argv[1]));
		} else {
			w.startServer(HTTP_PROXY);
		}
		w.runServer();
	}
	
	public void startServer(int portNum) throws Exception {
		String portNumString = null;
		if (portNum == HTTP_PROXY) {
			portNumString = wsp.getProperty("portNum");
			if (portNumString != null) {
				portNum = Integer.parseInt(portNumString);
			}
		}
		serverSocket = new ServerSocket(portNum);
		System.out.println("Listening on port " + portNum);
	
	}

	public void runServer() throws Exception {
		while (true) {
			final Socket clientSocket = serverSocket.accept();
			myThreadPool.execute(new Runnable() {
				public void run() {
					new Handler(WebProxy.this).process(clientSocket);
				}
			});
		}
	}
	
	public String getRootDir() {
		return rootDir;
	}
}
