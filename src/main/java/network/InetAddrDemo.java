package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

// BEGIN main
public class InetAddrDemo {
	public static void main(String[] args) throws IOException {
		String hostName = "www.darwinsys.com";
		String ipNumber = "8.8.8.8"; // currently a well-known Google DNS server

		// Show getting the InetAddress (looking up a host) by host name
		System.out.println(hostName + "'s address is " +
			InetAddress.getByName(hostName).getHostAddress());

		// Look up a host by address
		System.out.println(ipNumber + "'s name is " +
			InetAddress.getByName(ipNumber).getHostName());

		// Look up my localhost addresss
		final InetAddress localHost = InetAddress.getLocalHost();
		System.out.println("My localhost address is " + localHost);

		// Show getting the InetAddress from an open Socket
		String someServerName = "www.google.com";
		// assuming there's a web server on the named server:
		Socket theSocket = new Socket(someServerName, 80);	
		InetAddress remote = theSocket.getInetAddress();
		System.out.printf("The InetAddress for %s is %s%n",
			someServerName, remote);
		theSocket.close();
	}
}
// END main
