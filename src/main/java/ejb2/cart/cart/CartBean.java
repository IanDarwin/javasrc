package ejb2.cart.cart;

import javax.ejb.*;
import java.util.List;
import java.util.ArrayList;
import javax.naming.*;

/** The Implementation class for a Shopping Cart Stateful Session EJB.
 */
public class CartBean implements SessionBean {

	private static final long serialVersionUID = -8998524480478847292L;

	/** The list of items */
	private List<Product> cartItems;

	/** True if we've been checked out */
	private boolean checkedOut;

	/** The usual Session Context */
	private SessionContext sessionContext;

	/** A JNDI Context for finding other beans */
	private Context ctx;

	public void ejbCreate() throws CreateException {
		cartItems = new ArrayList<Product>();
		checkedOut = false;
	}
	public void ejbRemove() {
		cartItems = null;
	}

	public void add(Product o) {
		cartItems.add(o);
	}

	public void remove(Product o) {
		cartItems.remove(o);
	}

	public int size() {
		return cartItems.size();
	}

	/** Expose the ENTIRE list to the client. May be slow! */
	public List<Product> getItems() {
		return cartItems;
	}

	/** Checking Out converts the info from this Cart to an
	 * Order and a Billing. These are both required, so this
	 * method MUST be declared to be transactional in the deployment
	 * descriptor!!
	 */
	public void checkOut() {
		// Order is Entity bean; create() makes entry in DBMS

		//OrderHome ordHome = null;
		// ordHome = (OrderHome) ctx.lookup("OrderEJB");
		// Order ord = ordHome.create(custID, cartItems);

		// Billing is Entity bean; create() makes entry in DBMS
		// BillingHome bHome = null
		// bHome = (BillingHome) ctx.lookup("BillingEJB");
		// Billing b = bHome.create(custID);

		// b.setTotal(totalPrice(cartItems));
		checkedOut = true;
	}

	public int getOrderNumber() {
		if (!checkedOut) {
			throw new IllegalStateException(
				"getOrderNumber called when not checked out");
		}
		return -1;
	}

	public int getBillingNumber() {
		if (!checkedOut) {
			throw new IllegalStateException(
				"getBillingNumber called when not checked out");
		}
		return -1;
	}

	private double totalPrice(List v) {
		// not implemented yet
		return 100.00;
	}

	public void ejbActivate() { }
	public void ejbPassivate() { }

	public void setSessionContext(SessionContext ctx) {
		this.sessionContext = ctx;
	}
}
