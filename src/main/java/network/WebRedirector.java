package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;


/**
 * Web Redirector - yet another useful? subclass of WebServer0, this one to redirect 
 * any and all incoming requests to a new URL and/or PORT (like 8080).
 */
public class WebRedirector extends WebServer0 {
	public static final String GOTO = "http://localhost:8080/";
	
	private static String gotoURL = GOTO;
	
	public static void main(String[] args) throws Exception {
		if (args.length == 1) {
			gotoURL = args[0];
		}
		System.out.println("DarwinSys Web Redirector 0.0 starting...");
		WebRedirector w = new WebRedirector();
		w.runServer(HTTP);		// never returns!!
	}
	
	/** Generate one redirection request
	 * @see WebServer0#Handler(java.net.Socket)
	 */
	public void Handler(Socket s) {
		BufferedReader is;	// inputStream, from Viewer
		PrintWriter os;		// outputStream, to Viewer
		String request;		// what Viewer sends us.
		try {
			String from = s.getInetAddress().toString();
			System.out.println("Accepted connection from " + from);
			os = new PrintWriter(s.getOutputStream(), true);
			is = new BufferedReader(new InputStreamReader(s.getInputStream()));
			request = is.readLine();
			StringTokenizer st = new StringTokenizer(request);
			if (st.countTokens() < 3) {
				os.println("HTTP/1.0 400 invalid request");
				os.println();
				os.flush();
				s.close();
			}
			String requestMethod = st.nextToken();
			String requestURI = st.nextToken();
			String requestVersion = st.nextToken();
			System.out.println("Request: " + requestMethod + " for " + requestURI + " via " + requestVersion);
			// Read and discard what we hope is the null line
			is.readLine();
			
			os.println("HTTP/1.1 302 Moved Temporarily");
			os.println("Location: " + gotoURL + requestURI);
			os.println("Content-Type: text/plain");
			os.println("Server-name: DarwinSys NULL Java WebRedirector 0");
			os.flush();
			s.close();
		} catch (IOException ex){
			System.err.println("Error: " + ex);
		}
	}
}
