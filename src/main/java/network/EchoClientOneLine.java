import java.io.*;
import java.net.*;
/**
 * EchoClientOneLine - create client socket, send one line,
 * read it back. See also EchoClient.java, slightly fancier.
 */
public class EchoClientOneLine {
	/** What we send across the net */
	String mesg = "Hello across the net";
	/** Where we send "mesg" across the net TO */
	String hostName;

	public static void main(String argv[]) {
		if (argv.length == 0)
			new EchoClientOneLine().converse();
		else
			new EchoClientOneLine(argv[0]).converse();
	}

	/** Construct a EchoClientOneLine with a default hostname */
	public EchoClientOneLine() {
		this("server");
	}

	/** Construct a EchoClientOneLine with a given hostname */
	public EchoClientOneLine(String hName) {
		hostName = hName;
	}

	/** Hold one conversation across the net */
	protected void converse() {
		Socket sock;
		BufferedReader is;
		PrintWriter os;
		try {
			sock = new Socket(hostName, 7); // echo server.
			is = new BufferedReader(new 
				InputStreamReader(sock.getInputStream()));
			os = new PrintWriter(sock.getOutputStream(), true);
			os.println(mesg);
			String reply = is.readLine();
			System.out.println("Sent \"" + mesg  + "\"");
			System.out.println("Got  \"" + reply + "\"");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
