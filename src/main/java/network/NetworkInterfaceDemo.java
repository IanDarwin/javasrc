package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Show some uses of the new-in-1.4 NetworkInterface class.
 */
// BEGIN main
public class NetworkInterfaceDemo {
	public static void main(String[] a) throws IOException {
		Enumeration<NetworkInterface> list = NetworkInterface.getNetworkInterfaces();
		while (list.hasMoreElements()) {
			// Get one NetworkInterface
			NetworkInterface iface = list.nextElement();
			// Print its name
			System.out.println(iface.getDisplayName());
			Enumeration<InetAddress> addrs = iface.getInetAddresses();
			// And its address(es)
			while (addrs.hasMoreElements()) {
				InetAddress addr = addrs.nextElement();
				System.out.println(addr);
			}

		}
		// Try to get the Interface for a given local (this machine's) address
		InetAddress destAddr = InetAddress.getByName("laptop");
		try {
			NetworkInterface dest = NetworkInterface.getByInetAddress(destAddr);
			System.out.println("Address for " + destAddr + " is " + dest);
		} catch (SocketException ex) {
			System.err.println("Couldn't get address for " + destAddr);
		}
	}
}
// END main
