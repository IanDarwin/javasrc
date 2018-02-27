package rmi;

import java.rmi.Naming;

// BEGIN main
public class DateServer {
	public static void main(String[] args) {
		System.out.println("DateServer starting...");

		// You probably need a SecurityManager for downloading of classes:
		// System.setSecurityManager(new SecurityManager());

		System.out.println("Security Manager installed; setting up service");

		try {
			// Create an instance of the server object
			RemoteDateImpl im = new RemoteDateImpl();

			System.out.println("Publishing DateServer...");

			// Publish it in the RMI registry.
			// Of course you have to have rmiregistry or equivalent running!
			Naming.rebind(RemoteDate.LOOKUPNAME, im);

			System.out.println("DateServer ready.");
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
// END main
