package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class InetAddrDemo {
	public static void main(String[] args)
	throws IOException {
		String ipNumber = "123.45.67.89";
		String hostName = "www.darwinsys.com";

		// Look up a host by name
		System.out.println(hostName + "'s address is " +
			InetAddress.getByName(hostName).getHostAddress());

		// Look up a host by address
		System.out.println(ipNumber + "'s name is " +
			InetAddress.getByName(ipNumber).getHostName());

		Socket theSocket = new Socket("server", 80);
		int myPortNumber = 12345;

		// Connect to different portnum on same host as an open Socket
		InetAddress remote = theSocket.getInetAddress();
		Socket anotherSocket = new Socket(remote, myPortNumber);
		System.out.println(anotherSocket); // Show that we got here...
	}
}
