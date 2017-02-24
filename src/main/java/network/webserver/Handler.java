package network.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;
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
 * @version $Id$
 */
public class Handler {

	/** inputStream, from Viewer */
	protected BufferedReader is;
	/** outputStream, to Viewer */
	protected PrintStream os;
	/** Main program */
	protected Httpd parent;
	/** The default filename in a directory. */
	protected final static String DEF_NAME = "/index.html";
	/** Used to make line endings standards-compliant */
	public static final String CRLF = "\r\n";

	/** The Hashtable used to cache all URLs we've read.
	 * Static, shared by all instances of Handler (one Handler per request;
	 * this is probably quite inefficient, but simple. Need ThreadPool).
	 * Note that Hashtable methods *are* synchronized.
	 */
	private static Map<String,Object> h = new HashMap<String,Object>();

	static {
		h.put("", "<html><body><b>Unknown server error</b>".getBytes());
	}

	/** Construct a Handler */
	Handler(Httpd parent) {
		this.parent = parent;
	}
	
	protected static final int RQ_INVALID = 0, RQ_GET = 1, RQ_HEAD = 2,
		RQ_POST = 3; 

	public void process(Socket clntSock) {
		String request;		// what Viewer sends us.
		int methodType = RQ_INVALID;
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
				// us if we scream into cyberspace... Could log it though.
				return;
			}

			// Use a StringTokenizer to break the request into its three parts:
			// HTTP method, resource name, and HTTP version
			StringTokenizer st = new StringTokenizer(request);
			if (st.countTokens() != 3) {
				errorResponse(444, "Unparseable input " + request);
				return;
			}
			String rqCode = st.nextToken();
			String rqName = st.nextToken();
			String rqHttpVer = st.nextToken();
			System.out.println("Request: Command " + rqCode +
					", file " + rqName + ", version " + rqHttpVer);


			// Read headers, up to the null line before the body,
			// so the body can be read directly if it's a POST.
			HashMap map = new HashMap();
			String hdrLine;
			while ((hdrLine = is.readLine()) != null &&
					hdrLine.length() != 0) {
					int ix;
					if ((ix=hdrLine.indexOf(':')) != -1) {
						String hdrName = hdrLine.substring(0, ix);
						String hdrValue = hdrLine.substring(ix+1).trim();
						Debug.println("hdr", hdrName+","+hdrValue);
						map.put(hdrName, hdrValue);
					} else {
						System.err.println("INVALID HEADER: " + hdrLine);
					}
			}

			// check that rqCode is either GET or HEAD or ...
			if ("get".equalsIgnoreCase(rqCode))
				  methodType = RQ_GET;
			else if ("head".equalsIgnoreCase(rqCode))
				  methodType = RQ_HEAD;
			else if ("post".equalsIgnoreCase(rqCode))
				  methodType = RQ_POST;
			else {
				errorResponse(400, "invalid method: " + rqCode);
				return;
			}

			// A bit of paranoia may be a good thing...
			if (rqName.indexOf("..") != -1) {
				errorResponse(404, "can't seem to find: " + rqName);
				return;
			}
				
			// XXX new MyRequest(clntSock, rqName, methodType);
			// XXX new MyResponse(clntSock, os);

			// XXX if (isServlet(rqName)) [
			// 		doServlet(rqName, methodType, map);
			// else
				doFile(rqName, methodType == RQ_HEAD, os /*, map */);
			os.flush();
			clntSock.close();
		} catch (IOException e) {
			System.out.println("IOException " + e);
		}
		System.out.println("END OF REQUEST");
	}

	/** Processes one file request */
	void doFile(String rqName, boolean headerOnly, PrintStream os) throws IOException {
		File f;
		byte[] content = null;
		Object o = h.get(rqName);
		if (o != null && o instanceof byte[]) {
			content = (byte[])o;
			System.out.println("Using cached file " + rqName);
			sendFile(rqName, headerOnly, content, os);
		} else if ((f = new File(parent.getRootDir() + rqName)).isDirectory()) {
			// Directory with index.html? Process it.
			File index = new File(f, DEF_NAME);
			if (index.isFile()) {
				doFile(rqName + DEF_NAME, index, headerOnly, os);
				return;
			}
			else {
				// Directory? Do not cache; always make up dir list.
				System.out.println("DIRECTORY FOUND");
				doDirList(rqName, f, headerOnly, os);
				sendEnd();
			}
		} else if (f.canRead()) {
			// REGULAR FILE
			doFile(rqName, f, headerOnly, os);
		}
		else {
			errorResponse(404, "File not found");
		}
	}

	void doDirList(String rqName, File dir, boolean justAHead, PrintStream out) {
		out.print("HTTP/1.0 200 directory found" + CRLF);
		out.print("Content-type: text/html" + CRLF);
		out.print("Date: " + new Date().toString() + CRLF);
		out.print(CRLF);
		if (justAHead)
			return;
		out.println("<html>");
		out.println("<head><title>Contents of directory " + rqName + "</title></head>");
		out.println("<body><h1>Contents of directory " + rqName + "</h1>");
		String fl[] = dir.list();
		Arrays.sort(fl);
		for (int i=0; i<fl.length; i++)
			out.println("<br/><a href=\"" + rqName + File.separator + fl[i] + "\">" +
			"<img align='center' border='0' src=\"/images/file.jpg\">" +
			' ' + fl[i] + "</a>");
	}

	/** Send one file, given a File object. */
	void doFile(String rqName, File f, boolean headerOnly, PrintStream out) throws IOException {
		System.out.println("Loading file " + rqName);
		InputStream in = new FileInputStream(f);
		byte c_content[] = new byte[(int)f.length()];
		// Single large read, should be fast.
		int n = in.read(c_content);
		h.put(rqName, c_content);
		sendFile(rqName, headerOnly, c_content, out);
		in.close();
	}

	/** Send one file, given the filename and contents.
	 * @param justHead - if true, send heading and return.
	 */
	void sendFile(String fname, boolean justHead,
		byte[] content, PrintStream out) throws IOException {
		out.print("HTTP/1.0 200 Here's your file" + CRLF);
		out.print("Content-type: " + guessMime(fname) + CRLF);
		out.print("Content-length: " + content.length + CRLF);
		out.print(CRLF);
		if (justHead)
			return;
		out.write(content);
	}

	/** The type for unguessable files */
	final static String UNKNOWN = "unknown/unknown";
	
	protected String guessMime(String fn) {
		String lcname = fn.toLowerCase();
		int extenStartsAt = lcname.lastIndexOf('.');
		if (extenStartsAt<0) {
			if (fn.equalsIgnoreCase("makefile"))
				return "text/plain";
			return UNKNOWN;
		}
		String exten = lcname.substring(extenStartsAt);
		String guess = parent.getMimeType(exten, UNKNOWN);

		return guess;
	}

	/** Sends an error response, by number, hopefully localized. */
	protected void errorResponse(int errNum, String errMsg) {

		// Check for localized messages
		ResourceBundle messages = ResourceBundle.getBundle("errors");

		String response;
		try { response = messages.getString(Integer.toString(errNum)); }
		catch (MissingResourceException e) { response=errMsg; }

		// Generate and send the response
		os.print("HTTP/1.0 " + errNum + " " + response + CRLF);
		os.print("Content-type: text/html" + CRLF);
		os.print(CRLF);
		os.println("<html>");
		os.println("<head><title>Error " + errNum + "--" + response +
			"</title></head>");
		os.println("<h1>" + errNum + " " + response + "</h1>");
		sendEnd();
	}

	/** Send the tail end of any page we make up. */
	protected void sendEnd() {
		os.println("<hr>");
		os.println("<address>Java Web Server,");
		String myAddr = "http://www.darwinsys.com/freeware/";
		os.println("<a href=\"" + myAddr + "\">" +
			myAddr + "</a>");
		os.println("</address>");
		os.println("</html>");
		os.println();
	}
}
