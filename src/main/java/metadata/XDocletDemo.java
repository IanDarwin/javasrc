package myejbs;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * This is a Shopping Cart Stateful Session bean that is
 * really an example of the XDoclet EJB tags.
 * @see Product
 * @ejb.bean
 *		name="store/Cart"
 *		type="Stateful"
 *		jndi-name="store/Cart"
 * @ejb.interface
 *		remote-class="myejbs.Cart"
 * @version $Id$
 */
public class XDocletDemo implements SessionBean {

	/** Dummy inner class */
	class Product {
	}

	/** The list of items */
	private List cartItems;

	/** True if we've been checked out */
	private boolean checkedOut;

	/** The usual Session Context */
	private SessionContext sessionContext;

	/**
	 * @ejb.create-method
	 */
	public void ejbCreate() throws CreateException {
		cartItems = new ArrayList();
		checkedOut = false;
		// Do more work here...
	}

	public void ejbRemove() {
		cartItems = null;
	}

	/** @ejb.interface-method
	 */
	public void add(Product o) {
		cartItems.add(o);
	}

	/** @ejb.create-method
	 */
	public void remove(Product o) {
		cartItems.remove(o);
	}

	/** @ejb.interface-method
	 */
	public int size() {
		return cartItems.size();
	}

	/** Expose the ENTIRE list to the client. May be slow!
	 * @ejb.interface-method
	 */
	public List getItems() {
		return cartItems;
	}

	/** Checking Out converts the info from this Cart to an
	 * Order and a Billing. These are both required, so this
	 * method MUST be declared to be transactional in the deployment
	 * descriptor!!
	 * @ejb.interface-method
	 */
	public void checkOut() {
		//  Must do some work here...

		checkedOut = true;
	}

	/**
	 * @ejb.interface-method
	 */
	public int getOrderNumber() {
		if (!checkedOut) {
			throw new IllegalStateException(
				"getOrderNumber called when not checked out");
		}
		return -1;
	}

	/**
	 * @ejb.interface-method
	 */
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
