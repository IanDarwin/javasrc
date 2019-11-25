package rmi;

import java.rmi.Naming;
import java.time.LocalDateTime;

// tag::main[]
/* A very simple client for the RemoteDate service. */
public class DateClient {

	/** The local proxy for the service. */
	protected static RemoteDate netConn = null;

	public static void main(String[] args) {
		try {
			System.out.println("DateClient starting lookup...");
			netConn = (RemoteDate)Naming.lookup(RemoteDate.LOOKUPNAME);
			LocalDateTime today = netConn.getRemoteDate();
			System.out.println("DateClient received date: " + today.toString()); // Could use a DateFormat...
		} catch (Exception e) {
			System.err.println("DateClient: RemoteDate exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
// end::main[]
