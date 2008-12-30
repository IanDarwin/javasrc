package ejb2.cart.cart;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.EJBObject;

/** The Remote Interface for a Shopping Cart.
 * The whole notion is not very realistic; who'd keep a Cart
 * remotely, instead of locally or in an HttpSession in a web app?
 */
public interface Cart extends EJBObject {

	/** Add a Product to the cart */
	public void add(Product o) throws RemoteException;

	/** Remove one product from the cart */
	public void remove(Product o) throws RemoteException;

	/** The size of the cart, in items, like List.size() */
	public int size() throws RemoteException;

	/** Get the list of items */
	public List getItems() throws RemoteException;

	/** Checkout */
	public void checkOut() throws RemoteException;

	/** Get the order number created by checkOut */
	public int getOrderNumber() throws RemoteException;

	/** Get the billing invoice number created by checkOut */
	public int getBillingNumber() throws RemoteException;
}
