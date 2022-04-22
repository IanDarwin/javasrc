package network;

import java.awt.Frame;
import java.awt.TextArea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * EchoGui - create client socket, do I-O on it.
 *
 * @author	Ian Darwin, Learning Tree, Course 471/478
 */
public class EchoGui {
	String hostname = null;
	Socket sock;
	BufferedReader is;
	PrintWriter os;
	TextArea ta;

	public static void main(String[] argv) {
		if (argv.length == 0)
			new EchoGui().converse();
		else
			new EchoGui(argv[0]).converse();
	}

	EchoGui(String host) {
		Frame f = new Frame("EchoGui");
		f.add("Center", ta = new TextArea(24, 80));
		f.pack();
		f.setVisible(true);
		hostname = host;
	}
	EchoGui() {
		this("localhost");
	}
	protected void converse() {
		try {
			String mesg = "Hello \u08b7 across the net\u2603";
			sock = new Socket(hostname, 7);	// echo server.
			is = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			os = new PrintWriter(sock.getOutputStream(), true);

			os.write(mesg); os.print("\r\n");
			ta.append("Sent \"" + mesg  + "\"");

			String reply = is.readLine();
			ta.append("Got  \"" + reply + "\"");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
