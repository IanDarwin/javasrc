import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

/** Called from WebServer to handle one connection.
 * We are created with just a Socket, and read the
 * HTTP request, extract a name , read it (saving it
 * in Hashtable h for next time), and write it back.
 */
public class Handler extends Thread {
	/** The Socket that we read from and write to. */
	Socket clntSock;
	/** inputStream, from Viewer */
	BufferedReader is;
	/** outputStream, to Viewer */
	PrintStream os;
	/** Main program */
	WebServer parent;

	/** The Hashtable used to cache all URLs we've read.
	 * Static, shared by all instances of Handler (one per request).
	 */
	static Hashtable h = new Hashtable();

	/** Construct a Handler */
	Handler(WebServer ws, Socket sock) {
		super();
		parent = ws;
		clntSock = sock;
		// System.out.println("Handler::<init>");
		if (h.size() == 0) {
			h.put("", "<HTML><BODY><B>Unknown server error");
		}
	}

	protected static final int RQ_INVALID = 0, RQ_GET = 1, RQ_HEAD = 2; 

	public void run() {
		String request;		// what Viewer sends us.
		int type = RQ_INVALID;
		try {
			System.out.println("Connection accepted from " +
				clntSock.getInetAddress());
			is = new BufferedReader(new InputStreamReader(
				clntSock.getInputStream()));
			// Must do before any chance of errorResponse being called!
			os = new PrintStream(clntSock.getOutputStream());

			request = is.readLine();
			StringTokenizer st = new StringTokenizer(request);
			String rqCode = st.nextToken();
			String rqName = st.nextToken();
			String rqHttpVer = st.nextToken();
			System.out.println("Request: Command " + rqCode +
					", file " + rqName + ", version " + rqHttpVer);
			String nullLine = is.readLine();

			// check that rqCode is either GET or HEAD
			if ("get".equalsIgnoreCase(rqCode))
				  type = RQ_GET;
			else if ("head".equalsIgnoreCase(rqCode))
				  type = RQ_HEAD;
			else {
				errorResponse(400, "invalid code: " + rqCode);
				return;
			}

			// A bit of paranoia may be a good thing...
			if (rqName.indexOf("../") != -1 ||
		 		rqName.indexOf("..\\") != -1) {
				errorResponse(404, "can't seem to find: " + rqName);
				return;
			}
				
			doFile(rqName, type == RQ_HEAD, os);
			os.flush();
			this.sleep(1000);
			clntSock.close();
		} catch (IOException e) {
			System.out.println("IOException " + e);
		} catch (InterruptedException e2) {
			// do nothing...
		}
		System.out.println("END OF REQUEST");
	}

	/** Processes one file */
	void doFile(String rqName, boolean headerOnly, PrintStream os) throws IOException {
		if (rqName.endsWith("/"))
			rqName += "index.html";
		// strip leading / from rqName
		while (rqName.startsWith("/"))
			rqName = rqName.substring(1);
		File f;
		byte[] content;
		content = (byte[])h.get(rqName);
		if (content != null) {
			System.out.println("Using cached file " + rqName);
			sendFile(rqName, headerOnly, content, os);
		} else if ((f = new File("./"+rqName)).isDirectory()) {
			System.out.println("DIRECTORY FOUND");
			os.println("HTTP/1.0 200 directory found");
			os.println("Content-type: text/html");
			os.println("");
			os.println("<HTML>");
			os.println("Contents of directory " + rqName);
			String fl[] = f.list();
			for (int i=0; i<fl.length; i++)
				os.println("<BR>File " + fl[i]);
			os.println("<HR>");
			os.println("</HTML>");
		} else if (f.canRead()) {
			System.out.println("Loading file " + rqName);
			InputStream in = new FileInputStream(rqName);
			byte c_content[] = new byte[(int)f.length()];
			int n = in.read(c_content);
			content = c_content;
			h.put(rqName, content);
			sendFile(rqName, headerOnly, content, os);
		} else {
			errorResponse(404, "File not found");
		}
	}

	/** Send one file.
	 * TODO add a boolean justHead and if true, return before content.
	 */
	void sendFile(String fname, boolean justHead,
		byte[] content, PrintStream os) throws IOException {
		os.println("HTTP/1.0 200 Here's your file");
		os.println("Content-type: " + guessMime(fname));
		os.println("Content-length: " + content.length);
		os.println("");
		if (justHead)
			return;
		os.write(content);
	}

	/** The type for unguessable files */
	final static String UNKNOWN = "unknown/unknown";
	String guessMime(String fn) {
		String lcname = fn.toLowerCase();
		int extenStartsAt = lcname.lastIndexOf('.');
		if (extenStartsAt<0) {
			if (fn.equalsIgnoreCase("makefile"))
				return "text/plain";
			return UNKNOWN;
		}
		String exten = lcname.substring(extenStartsAt);
		String guess = parent.mimeTypes.getProperty(exten, UNKNOWN);

		// System.out.println("guessMime: input " + fn + 
		// 	", extention " + exten + ", result " + guess);

		return guess;
	}

	/** Sends an error response, by number */
	protected void errorResponse(int errNum, String errMsg) {

		// Check for localized messages
		ResourceBundle messages = ResourceBundle.getBundle("errors");

		String response;
		try { response = messages.getString(Integer.toString(errNum)); }
		catch (MissingResourceException e) { response=errMsg; }

		// Generate and send the response
		os.println("HTTP/1.0 " + errNum + " " + response);
		os.println("Content-type: text/html");
		os.println("");
		os.println("<HTML>");
		os.println("<HEAD><TITLE>Error " + errNum + "--" + response +
			"</TITLE></HEAD>");
		os.println("<H1>" + errNum + " " + response + "</H1>");
		os.println("<HR>");
		os.println("JavaWeb 0.1");
		os.println("<HR>");
		os.println("</HTML>");
		os.println("");
	}
}
