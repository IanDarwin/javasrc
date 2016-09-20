package webproxy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import com.darwinsys.util.Debug;

/** Called from Httpd in a Thread to handle one connection.
 * We are created with just a Socket, and read the
 * HTTP request, extract a name, read it (saving it
 * in Hashtable h for next time), and write it back.
 * <p>
 * TODO split into general handler stuff and "FileServlet",
 *	then handle w/ either user HttpServlet subclasses or FileServlet.
 */
// BEGIN main
public class Handler {

	/** inputStream, from Viewer */
	protected BufferedReader is;
	/** outputStream, to Viewer */
	protected PrintStream os;
	/** Main program */
	protected WebProxy parent;
	/** The default filename in a directory. */
	protected final static String DEF_NAME = "/index.html";

	/** The Hashtable used to cache all URLs we've read.
	 * Static, shared by all instances of Handler (one Handler per request;
	 * this is probably quite inefficient, but simple. Need ThreadPool).
	 * Note that Hashtable methods *are* synchronized.
	 */
	private static Map<String,Object> cache  = new HashMap<String,Object>();

	static {
		cache.put("", "<html><body><b>Unknown server error</b>".getBytes());
	}

	/** Construct a Handler */
	Handler(WebProxy parent) {
		this.parent = parent;
	}
	
	protected enum RequestType {
		RQ_INVALID, RQ_GET, RQ_HEAD, RQ_POST
	} 

	String requestURL;
	
	public void process(Socket clntSock) {
		String request;		// what Viewer sends us.
		RequestType methodType = RequestType.RQ_INVALID;
		try {
			System.out.println("Connection accepted from " +
				clntSock.getInetAddress());
			is = new BufferedReader(new InputStreamReader(
				clntSock.getInputStream()));
			// Must do before any chance of errorResponse being called!
			os = new PrintStream(clntSock.getOutputStream());

			request = is.readLine();
			if (request == null || request.length() == 0) {
				// No point nattering: the sock died, nobody will hear
				// us if we scream into cyberspace... 
				System.err.println("The sock has died...");
				return;
			}

			// Use a StringTokenizer to break the request into its three parts:
			// HTTP method, resource name, and HTTP version
			StringTokenizer st = new StringTokenizer(request);
			if (st.countTokens() != 3) {
				errorResponse(444, "Unparseable input " + request);
				clntSock.close();
				return;
			}
			String requestCommand = st.nextToken();
			requestURL = st.nextToken();
			String requestHTTPVersion = st.nextToken();
			System.out.println("Request: Command " + requestCommand +
					", file " + requestURL + ", version " + requestHTTPVersion);

			// First, check that rqCode is either GET or HEAD or ...
			if ("get".equalsIgnoreCase(requestCommand))
				  methodType = RequestType.RQ_GET;
			else if ("head".equalsIgnoreCase(requestCommand))
				  methodType = RequestType.RQ_HEAD;
			else if ("post".equalsIgnoreCase(requestCommand))
				  methodType = RequestType.RQ_POST;
			else {
				errorResponse(400, "invalid method: " + requestCommand);
				clntSock.close();
				return;
			}
			
			// Read headers, up to the null line before the body,
			// so the body can be read directly if it's a POST.
			Map<String,String> headersMap = new HashMap<String,String>();
			String hdrLine;
			while ((hdrLine = is.readLine()) != null &&
					hdrLine.length() != 0) {
					int ix;
					if ((ix=hdrLine.indexOf(':')) != -1) {
						String hdrName = hdrLine.substring(0, ix);
						String hdrValue = hdrLine.substring(ix+1).trim();
						Debug.println("hdr", hdrName+","+hdrValue);
						headersMap.put(hdrName, hdrValue);
					} else {
						System.err.println("INVALID HEADER: " + hdrLine);
					}
			}
			
			if (methodType == RequestType.RQ_POST) {
				errorResponse(501, "Protocol not written yet");
				clntSock.close();
				return;
			}

			// Make a URL from the request
			URL url = new URL(requestURL);
			String protocol = url.getProtocol();
			if (!"http".equals(protocol)) {
				errorResponse(401, "protocol not supported: " + requestURL);
				clntSock.close();
				return;
			}				

			returnURL(url, os);
			os.flush();
			clntSock.close();
			
			System.out.println("END OF REQUEST");
		} catch (FileNotFoundException e) {
			errorResponse(404, "Server can't find " + requestURL);
		} catch (IOException e) {
			errorResponse(500, "IO Error on proxy");
			System.out.println("IOException " + e);
			e.printStackTrace();
		}		
	}

	private void returnURL(URL url, PrintStream os) throws IOException {
		InputStream is = url.openStream();
		int c;
		while ((c = is.read()) != -1) {
			os.write(c);
		}
	}

	/** Sends an error response, by number, hopefully localized. */
	protected void errorResponse(final int errNum, final String errMsg) {

		// Check for localized messages
		ResourceBundle messages = null;
		try {
			messages = ResourceBundle.getBundle("errors");
		} catch (MissingResourceException e) {
			System.err.println(e);
		}

		String response = errMsg;
		
		if (messages != null) {
			try {
				response = messages.getString(Integer.toString(errNum));
			} catch (MissingResourceException e) {
				response = errMsg;
			}
		}

		// Generate and send the response
		os.println("HTTP/1.0 " + errNum + " " + response);
		os.println("Content-type: text/html");
		os.println();
		os.println("<html>");
		os.println("<head><title>Error " + errNum + "--" + response +
			"</title></head>");
		os.println("<h1>" + errNum + " " + response + "</h1>");
		os.println("<hr>");
		os.println("<address>Java Web Proxy,");
		String myAddr = "http://www.darwinsys.com/freeware/";
		os.println("<a href=\"" + myAddr + "\">" +
			myAddr + "</a>");
		os.println("</address>");
		os.println("</html>");
		os.println();
		os.close();
	}
}
// END main
