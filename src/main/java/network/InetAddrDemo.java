import java.net.*;

public class InetAddrDemo {
	public static void main(String[] args) throws UnknownHostException {
		String ipNumber = "123.45.67.89";
		String hostName = "www.darwinsys.com";
		System.out.println(hostName + "'s address is " +
			InetAddress.getByName(hostName).getHostAddress());
		System.out.println(ipNumber + "'s name is " +
			InetAddress.getByName(ipNumber).getHostName());
	}
}
