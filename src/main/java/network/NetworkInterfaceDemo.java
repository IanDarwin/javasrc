import java.net.*;
import java.util.*;
import java.io.*;
public class NetworkInterfaceDemo {
public static void main(String[] a) throws IOException {
Enumeration list = NetworkInterface.getNetworkInterfaces();
while (list.hasMoreElements()) {
	// Get one NetworkInterface
	NetworkInterface iface = (NetworkInterface)list.nextElement();
	// Print its name
	System.out.println(iface.getDisplayName());
	Enumeration addrs = iface.getInetAddresses();
	// And its address(es)/
	while (addrs.hasMoreElements()) {
		InetAddress addr = (InetAddress)addrs.nextElement();
		System.out.println(addr);
	}

}
// Try to get the Interface on which a given packet will go out?
InetAddress destAddr = InetAddress.getByName("192.168.1.254");
try {
	NetworkInterface dest = NetworkInterface.getByInetAddress(destAddr);
	System.out.println("Route to " + destAddr + " is " + dest);
} catch (SocketException ex) {
	System.err.println("Couldn't route to " + destAddr);
}
}
}
