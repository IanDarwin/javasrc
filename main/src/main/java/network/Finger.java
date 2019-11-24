package network;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

/**
 * Finger -- access remote finger client.
 *
 * @author	Ian Darwin, Learning Tree, Course 471/478
 */
public class Finger {
	static String mesg;
	static Socket sock;
	static BufferedReader is;
	static PrintStream os;

	public static void main(String[] av) {
		if (av.length == 0)
			System.out.println("Just you");
		else for (int i=0; i<av.length; i++)
			new Finger().finger(av[i]);
	}

	void finger(String s) {
		StringTokenizer st = new StringTokenizer(s, "@", true);
		String user = (String)st.nextElement();
		st.nextElement();
		String host = (String)st.nextElement();
		//if (st.countTokens() != 2) {
		//	System.out.println("Count = " + st.countTokens());
		//	System.err.println("User " + s + " invalid, must be user@host");
		//	return;
		//}
		System.out.println("[" + user + "@" + host + "]");
		try {
			sock = new Socket(host, 7);
			is =
				new BufferedReader(
					new InputStreamReader(sock.getInputStream()));
			os = new PrintStream(sock.getOutputStream());

			if (true) // long flag
				os.print("/W "); // wide mode

			// Write request, with \n so readLine() doesn't hang!
			os.print("\r\n");
			os.flush();

			// Whatever we get back, we print.
			// No timeout policy -- if it hangs, user can INTR.
			String reply;
			while ((reply = is.readLine()) != null)
				System.out.println(reply);

		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
