import java.rmi.*;

import com.darwinsys.callback.*;

public class ServerMain {
	public static void main(String[] args) {
		// System.setSecurityManager(new RMISecurityManager());
		try {
			System.out.println("Server: Constructing TickerServerImpl");
			com.darwinsys.callback.TickerServerImpl im =
				new com.darwinsys.callback.TickerServerImpl();

			System.out.println("Server: Registering with RMI...");
			Naming.rebind(com.darwinsys.callback.TickerServer.LOOKUP_NAME, im);
			System.out.println("Server: bound and ready.");
			im.start();		// start background thread running.
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
