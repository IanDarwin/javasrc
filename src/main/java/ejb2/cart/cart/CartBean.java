import javax.ejb.*

public class CartBean extends EntityBean {

	private Vector cartItems();

	public void ejbCreate() {
		cartItems = new Vector();
	}

	public void add(Object o) {
		cartItems.addElement(o);
	}

	public void checkOut() {
		OrderHome ordHome = (OrderHome) ... lookup

		// Order is Entity bean; create creates in DBMS
		Order ord = ordHome.create(custID, cartItems);

		BillingHome bHome = (BillingHome) ... lookup
		Billing b = bHome.create(custID);

		b.setTotal(totalPrice(cartItems));
		
	}

	private int totalPrice(Vector v) {
		// not implemented yet
		return 10000;	// i.e., 100.00
	}
}
