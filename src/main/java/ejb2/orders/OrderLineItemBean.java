package rain;

import javax.ejb.*;

/**
 *  Implementation Class for OrderLineItem.
 *
 * @author MkBean
 */
public class OrderLineItemBean implements EntityBean {

	// fields.
	public Integer Order;		// primary key
	public int product;
	public int quantity;

	// implementation of main logic

	public int getProduct() { return product; }
	public void setProduct(int n) { product = n; }
	public int getQuantity() { return quantity; }
	public void setQuantity(int n) { quantity = n; }


	// Create Methods
	// method implementation for Create/PostCreate
	public OrderLineItem ejbCreate(Integer ord, int prod, int qty) throws CreateException{
		if (ord.intValue() == 0) {
			throw new CreateException("Order number cannot be zero");
		}
		Order = ord;
		product = prod;
		quantity = qty;
		return null;
	}
	public OrderLineItem ejbPostCreate(Integer ord, int i, int q) throws CreateException{
		return null;
	}

	// Find methods will be implemented by deployment.

	/** reference to passed Session context */
	private EntityContext ctx;

	// method implementations imposed by {type}Bean interface

	/** save the session context */
	public void setEntityContext(EntityContext x) { ctx = x; }
	public void unsetEntityContext() { ctx = null; }

	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbRemove() {}
	public void ejbStore() {};
	public void ejbLoad() {};

}
