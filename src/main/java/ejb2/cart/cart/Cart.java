import javax.ejb.*;
import java.rmi.RemoteException;

/** The Remote Interface for a Shopping Cart.
 * The whole notion is not very realistic; who'd keep a Cart
 * remotely, instead of locally or in an HttpSession in a web app?
 */
public interface Cart extends EJBObject {
	public void add(Product o) throws RemoteException;
	public void remove(Product o) throws RemoteException;
	/** The size of the cart, in items, like List.size() */
	public int size() throws RemoteException;
	public void checkOut() throws RemoteException;
	/** Get the order number created by checkOut */
	public int getOrderNumber() throws RemoteException;
	/** Get the billing invoice number created by checkOut */
	public int getBillingNumber() throws RemoteException;
}
