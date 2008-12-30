package ejb2.interfaces;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class Client {
	public static void main(String[] args) {

		try {
			// Properties from jndi.properties in current directory
			Context initial = new InitialContext();

			System.out.println("Getting Home Reference...");
			Object objref = initial.lookup("Universe");
			Home home =
				(Home)PortableRemoteObject.narrow(objref,
					Home.class);

			// Call the EJBHome's create() method to get the EJBObject
			System.out.println("Creating EJB Reference...");
			Remote universe = home.create();

			System.out.println("Calling EJB Method...");
			int meaningOfUniverse = universe.computeMeaning();

			System.out.println("Result was: " + meaningOfUniverse);

		} catch (Exception ex) {
			System.err.println("Caught an unexpected exception!");
			ex.printStackTrace();
		}

	}
}
