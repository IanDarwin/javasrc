// $Id$

// The names of these packages are implementation-dependant; most use 
// "sun.", but Kaffe uses "kaffe.".
package sun.net.www.protocol.date;

import java.io.*;
import java.net.*;

/** This is an example of a Java "Protocol Handler". This is a <B>very
 * advanced topic</B> (basically changing the functionality of Netscape
 * or HotJava), so don't start here, folks!
 *
 * This is, as the name implies, a handler for the SSH protocol.
 * SSH is like an encrypted telnet. We use only SSH1 since it is
 * free software; SSH2 has been commercialized.
 *
 * We simply fire up an external SSH command to the specified host.
 * 
 * Usage: java Browser0 ssh://server/		# note the trailing / is needed.
 */
public class Handler extends URLStreamHandler {
	/** The TCP port number */
	public static final int DAYTIME_PORT = 13;

	/** Internal class for handling connection */
	class SSHConnection extends URLConnection {
		String hostName;
		Process sshProc;

		/** Constructor */
		SSHConnection(URL u) {
			super(u);
			hostName = u.getHost();
		}

		public void connect() {
			sshProc = Runtime.getRuntime().exec("ssh " + hostName);
		}

		public InputStream getInputStream() throws IOException {
			return sshProc.getInputStream();
		}

		public Object getContent() throws IOException {
			String retval;
			BufferedReader is = new BufferedReader(
				new InputStreamReader(getInputStream()));
			retval = is.readLine();
			is.close();
			return retval;
		}
	}

	public synchronized URLConnection openConnection(URL u) {
		SSHConnection conn = null;
		try {
			conn = new SSHConnection(u);
		} catch (IOException e1) {
			System.err.println("Protocol failure " + e1);
			return null;
		}
		return conn;
	}
}
