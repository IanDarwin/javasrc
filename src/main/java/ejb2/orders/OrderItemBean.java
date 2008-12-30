package ejb2.orders;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

/**
 * Bean Implementation Class for OrderItem.
 * @author Ian Darwin, originally by MkBean
 */
public abstract class OrderItemBean implements EntityBean {

	// Virtual CMP fields
	public abstract void setOrderItemSequence(Integer n);
	public abstract Integer getOrderItemSequence();
	public abstract int getProduct();
	public abstract void setProduct(int n);
	public abstract int getQuantity();
	public abstract void setQuantity(int n);

	// Create Methods
	// method implementation for Create/PostCreate
	public Integer ejbCreate(Integer ord, int prod, int qty) throws CreateException{
		if (ord.intValue() <= 0) {
			throw new CreateException("Order number must be positive");
		}
		setOrderItemSequence(ord);
		setProduct(prod);
		setQuantity(qty);
		return null;
	}

	public void ejbPostCreate(Integer ord, int i, int q) throws CreateException{
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
