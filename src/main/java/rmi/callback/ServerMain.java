import java.rmi.*;

public class ServerMain {
	public static void main(String[] args) {
		// System.setSecurityManager(new RMISecurityManager());
		try {
			darwinsys.callback.RegisterImpl im = darwinsys.new callback.RegisterImpl();
			System.out.println("Server starting...");
			Naming.rebind("Server", im);
			System.out.println("Server ready.");
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
