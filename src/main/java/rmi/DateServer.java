package rmi;

package darwinsys.distdate;

import java.rmi.*;

public class DateServer {
	public static void main(String[] args) {

		// You may want a SecurityManager for downloading of classes:
		// System.setSecurityManager(new RMISecurityManager());

		try {
			// Create an instance of the server object
			RemoteDateImpl im = new RemoteDateImpl();

			System.out.println("DateServer starting...");
			// Locate it in the RMI registry.
			Naming.rebind(RemoteDate.LOOKUPNAME, im);

			System.out.println("DateServer ready.");
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
