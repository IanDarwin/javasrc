import javax.ejb.*;
import java.util.*;

public class CartBean implements SessionBean {

	private ArrayList cartItems;

	public void ejbCreate() {
		cartItems = new ArrayList();
	}
	public void ejbRemove() {
		cartItems = null;
	}

	public void add(Object o) {
		cartItems.add(o);
	}

	public void checkOut() {
		OrderHome ordHome = null;
		// ordHome = (OrderHome) ... lookup

		// Order is Entity bean; create creates in DBMS
		Order ord = ordHome.create(custID, cartItems);

		BillingHome bHome = null
		// bHome = (BillingHome) ... lookup
		Billing b = bHome.create(custID);

		b.setTotal(totalPrice(cartItems));
	}

	private int totalPrice(List v) {
		// not implemented yet
		return 10000;	// i.e., 100.00
	}

	public void ejbActivate() { }
	public void ejbPassivate() { }

}
