package ejb2.cart;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import ejb2.cart.cart.Cart;
import ejb2.cart.cart.CartHome;
import ejb2.cart.cart.Product;

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
			theCart.add(new Product(123));
			theCart.add(new Product(456));
			int cartSize = theCart.size();

			System.out.println("Count: should be 2; was: " + cartSize);
			List<Product> items = theCart.getItems();
			for (Product item : items) {
				System.out.println(item);
			}

		} catch (Exception ex) {
			System.err.println("Caught an unexpected exception!");
			ex.printStackTrace();
		}

	}
}
