import java.io.*;
import java.net.*;
import java.util.*;

/** NetLog - simple NetLog client.
 * @author	Ian Darwin, ian@darwinsys.com
 * @version Copyright (C) 1995, 1996 Ian Darwin
 */
public class NetLog {
	protected final static int NETLOG_PORT = 65432;
	protected int port = NETLOG_PORT;
	protected Socket sock = null;
	PrintWriter os;

	public NetLog() throws IOException {
		this(NETLOG_PORT);
	}

	public NetLog(int po) throws IOException {
		port = po;
		sock = new Socket(InetAddress.getLocalHost(), po);
		os = new PrintWriter(
			new OutputStreamWriter(
				sock.getOutputStream(), "8859_1"), true);
	}

	/** Send one String to the log */
	public void log(String mesg) throws IOException {
		if (os == null)
			return;

		// System.out.print(">> ");
		os.print(mesg);
		// Do the CRLF ourself since println appends only a \r on
		// platforms where that is the native line ending.
		os.print("\r\n");
		os.flush();
	}

	/** Send one Object to the log */
	public void log(Object obj) throws IOException {
		if (os == null)
			return;
		if (obj == null) {
			// throw IllegalStateException??
			return;
		}
		log(obj.toString());
	}

	/** Close the log. */
	public void close() {
		os.close();
		os = null;
	}

	public static void main(String[] args) throws IOException {
		NetLog nl = new NetLog();
		nl.log("Hello Java");
		nl.log(new Date());
		nl.log(nl);
		nl.log(null);
		nl.log("");
		nl.close();
	}
}
