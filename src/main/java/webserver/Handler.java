import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;

/** Called from Httpd to handle one connection.
 * We are created with just a Socket, and read the
 * HTTP request, extract a name, read it (saving it
 * in Hashtable h for next time), and write it back.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class Handler extends Thread {
	/** The Socket that we read from and write to. */
	Socket clntSock;
	/** inputStream, from Viewer */
	BufferedReader is;
	/** outputStream, to Viewer */
	PrintStream os;
	/** Main program */
	Httpd parent;
	/** The default filename in a directory. */
	final static String DEF_NAME = "/index.html";

	/** The Hashtable used to cache all URLs we've read.
	 * Static, shared by all instances of Handler (one per request).
	 */
	static Hashtable h = new Hashtable();

	/** Construct a Handler */
	Handler(Httpd prnt, Socket sock) {
		super("client thread");
		parent = prnt;
		clntSock = sock;
		// First time, put in null handler.
		if (h.size() == 0) {
			h.put("", "<HTML><BODY><B>Unknown server error");
		}
	}

	protected static final int RQ_INVALID = 0, RQ_GET = 1, RQ_HEAD = 2,
		RQ_POST = 3; 

	public void run() {
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
						System.out.println("hdr("+hdrName+","+hdrValue+")");
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
		} else if ((f = new File(parent.rootDir + rqName)).isDirectory()) {
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

	void doDirList(String rqName, File dir, boolean justAHead, PrintStream os) {
		os.println("HTTP/1.0 200 directory found");
		os.println("Content-type: text/html");
		os.println("Date: " + new Date().toString());
		os.println("");
		if (justAHead)
			return;
		os.println("<HTML>");
		os.println("<TITLE>Contents of directory " + rqName + "</TITLE>");
		os.println("<H1>Contents of directory " + rqName + "</H1>");
		String fl[] = dir.list();
		Arrays.sort(fl);
		for (int i=0; i<fl.length; i++)
			os.println("<BR><A HREF=\"" + fl[i] + "\">" +
			"<IMG ALIGN=absbottom BORDER=0 SRC=\"internal-gopher-unknown\">" +
			' ' + fl[i] + "</A>");
	}

	/** Send one file, given a File object. */
	void doFile(String rqName, File f, boolean headerOnly, PrintStream os) throws IOException {
		System.out.println("Loading file " + rqName);
		InputStream in = new FileInputStream(f);
		byte c_content[] = new byte[(int)f.length()];
		// Single large read, should be fast.
		int n = in.read(c_content);
		h.put(rqName, c_content);
		sendFile(rqName, headerOnly, c_content, os);
		in.close();
	}

	/** Send one file, given the filename and contents.
	 * boolean justHead - if true, send heading and return.
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

	/** Sends an error response, by number, hopefully localized. */
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
		sendEnd();
	}

	/** Send the tail end of any page we make up. */
	protected void sendEnd() {
		os.println("<HR>");
		os.println("<ADDRESS>Java Web Server,");
		String myAddr = "http://www.darwinsys.com/freeware/";
		os.println("<A HREF=\"" + myAddr + "\">" +
			myAddr + "</A>");
		os.println("</ADDRESS>");
		os.println("</HTML>");
		os.println("");
	}
}
