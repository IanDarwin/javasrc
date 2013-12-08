package logging;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/** NetLog - NetLog client API.
 * @see		NetLogSimple -- demonstration example usage.
 * @author	Ian Darwin, http://www.darwinsys.com/
 */
public class NetLog {
	protected final static int NETLOG_PORT = NetLogServer.PORT;
	protected int port = NETLOG_PORT;
	protected Socket sock = null;
	protected PrintWriter os;

	public NetLog() throws IOException {
		this(NETLOG_PORT);
	}

	public NetLog(int prtNum) throws IOException {
		this(InetAddress.getLocalHost(), prtNum);
	}

	public NetLog(String host, int prtNum) throws IOException {
		this(InetAddress.getByName(host), prtNum);
	}

	public NetLog(InetAddress host, int prtNum) throws IOException {
		port = prtNum;
		sock = new Socket(host, prtNum);
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
}
