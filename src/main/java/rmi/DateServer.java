import java.rmi.*;
import darwinsys.distdate.*;

public class DateServer {
	public static void main(String[] args) {
		// System.setSecurityManager(new RMISecurityManager());
		try {
			darwinsys.distdate.RemoteDateImpl im = new darwinsys.distdate.RemoteDateImpl();
			System.out.println("DateServer starting...");
			Naming.rebind("DateServer", im);
			System.out.println("DateServer ready.");
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
	}
}
