import java.util.*;
import javax.ejb.*;
import javax.rmi.*;
import javax.naming.*;

public class CartClient {
	public static void main(String[] args) {

		try {
			Context initial = new InitialContext();

			System.out.println("Getting Home Reference...");
			Object objref = initial.lookup("CartEJB");
			CartHome home =
				(CartHome)PortableRemoteObject.narrow(objref,
					CartHome.class);

			// Call the EJBHome's create() method to get the EJBObject
			System.out.println("Creating EJB Reference...");
			Cart theCart = home.create();

			System.out.println("Calling EJB Methods...");
			theCart.add(new Product());
			theCart.add(new Product());
			int cartSize = theCart.size();

			System.out.println("Count: should be 2; was: " + cartSize);

		} catch (Exception ex) {
			System.err.println("Caught an unexpected exception!");
			ex.printStackTrace();
		}

	}
}
