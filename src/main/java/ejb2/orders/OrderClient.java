package ejb2.orders;

import javax.ejb.ObjectNotFoundException;
import javax.naming.Context;
import javax.naming.InitialContext;

public class OrderClient {
	public static void main(String[] args) throws Exception {

		Context ctx = new InitialContext();

		// JNDI lookup on Orders
		OrderHome oh = (OrderHome)ctx.lookup("OrderEJB");

		Integer pkey = new Integer(24);

		// Create Order Item (try removing it first to avoid duplicate pkey).
		try {
			oh.findByPrimaryKey(pkey).remove();
			System.out.println("[Removed old " + pkey + "]");
		} catch (ObjectNotFoundException ex) {
			// no problem, nothing to do.
		} catch (Exception ex) {
			System.out.println("Niggly Little Warning: " + ex);
		}
		OrderRemote orig = oh.create(pkey, new Integer(42));

		// JNDI lookup on order
		OrderRemote lookedup = oh.findByPrimaryKey(pkey);

		System.out.println("Order found, isIdentical comparison " +
			orig.isIdentical(lookedup));

		// Order 100 items of product # 12
		orig.addItem(12, 100);

		// Order 50 items of product # 13
		orig.addItem(13, 50);

		// Order 25 items of product # 14
		orig.addItem(14, 25);

		System.out.println("Added three items to product");

		// Clean up.
		// orig.remove();
		// System.out.println("Cleaned up OK.");
	}
}
