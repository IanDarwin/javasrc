import java.rmi.*;

import agents.*;
import servers.*;

public class ServerMain {
	public static void main(String[] args) {
		// System.setSecurityManager(new RMISecurityManager());
		try {
			System.out.println("Server: Constructing RunServerImpl");
			RunServerImpl im = new RunServerImpl();

			System.out.println("Server: Registering with RMI...");
			Naming.rebind(RunServer.LOOKUP_NAME, im);
			System.out.println("Server: bound and ready.");
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
