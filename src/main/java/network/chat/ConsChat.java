import java.io.*;
import java.net.*;

/** Simple console-mode (command-line) chat client.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id$
 */
public class ConsChat {
	public static void main(String args[]) throws IOException {
		new ConsChat().chat();
	}

	protected Socket sock;
	protected BufferedReader is;
	protected PrintWriter pw;
	protected BufferedReader cons;

	protected ConsChat() throws IOException {
		sock = new Socket("localhost", Chat.PORTNUM);
		is   = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		pw   = new PrintWriter(sock.getOutputStream(), true);
		cons = new BufferedReader(new InputStreamReader(System.in));
	}

	protected void chat() throws IOException {
		String text;

		System.out.print("Login name: "); System.out.flush();
		text = cons.readLine();
		send(Chat.CMD_LOGIN + text);
		while ((text = cons.readLine()) != null) {
			if (text.charAt(0) == '/')
				send(text.substring(1));
			else send("B"+text);
		}
	}

	protected void send(String s) {
		pw.println(s);
		pw.flush();
	}
}
