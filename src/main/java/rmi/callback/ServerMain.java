import java.rmi.*;

public class ServerMain {
	public static void main(String[] args) {
		// System.setSecurityManager(new RMISecurityManager());
		try {
			callback.RegisterImpl im = new callback.RegisterImpl();
			System.out.println("Server starting...");
			Naming.rebind("Server", im);
			System.out.println("Server ready.");
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
