import java.net.*;
import java.io.*;


/**
 * Course 333: Hands-On UNIX Systems and Applications Programming in C
 * <BR>
 * Specimen Solution to Hands-On Exercise Chapter 7: a threaded echo server
 *
 * @author	John McDermott, Jr
 * @version 3 June 1996
 *
 * @author	Rewritten in Java by Ian F. Darwin, LT471/478 Author.
 * @version	January, 1997
 * <BR>
 * No need for "struct client": use object-wide data
 * the echo_client function is renamed "run" to fit with Java threads
 */
public class EchoServerThreaded {

	public final int TESTPORT = 666;

	public static void main(String[] av)
	{
		new EchoServerThreaded().runServer();
	}

	public void runServer()
	{
		ServerSocket sock;
		Socket newsock;

		try {
			/* STEPS for starting a TCP/IP socket based server */
			/* STEP 1, 2, and 3 in Java are all done in one line: */
			sock = new ServerSocket(TESTPORT);
		
			/* STEP 4: Wait for a connection */
			while(true){
				newsock = sock.accept();
				/* Create a thread to do the communication, and start it */
				new OneClient(newsock).start();
			}
		} catch(IOException e) {
			/* Crash the server if IO fails. Something bad has happened */
			System.err.println("Could not accept " + e);
		}
	}
}

class OneClient extends Thread {
	Socket sock;

	OneClient(Socket s) {
		sock = s;
	}

	public void run() 
	{
		System.out.println("Starting client, socket = " + sock);
		try {
			DataInputStream is = new DataInputStream(sock.getInputStream());
			PrintStream os = new PrintStream(sock.getOutputStream());
			String line;
			while ((line = is.readLine()) != null)
				os.println(line);
			sock.close();
		} catch (IOException e) {
			System.out.println("IO Error on socket " + e);
		}
		return;
	}
}
