import java.util.*;
import javax.ejb.*;
import javax.rmi.*;
import javax.naming.*;

import com.darwinsys.orders.PaymentHome;
import com.darwinsys.orders.Payment;

public class Client {
	public static void main(String[] args) {

		try {
			// Properties from jndi.properties in current directory
			Context initial = new InitialContext();

			System.out.println("Getting Home Reference...");
			Object objref = initial.lookup("Payment");
			PaymentHome home =
				(PaymentHome)PortableRemoteObject.narrow(objref,
					PaymentHome.class);

			// Call the EJBHome's create() method to get the EJBObject
			System.out.println("Creating EJB Reference...");
			Payment cashregister = home.create();

			System.out.println("Calling EJB Method...");
			cashregister.payByCash(42, 42.0);

			System.out.println("Survived!");

		} catch (Exception ex) {
			System.err.println("Caught an unexpected exception!");
			ex.printStackTrace();
		}

	}
}
