package rain;

import javax.ejb.*;

/**
 *  Implementation Class for Order.
 *
 * @author MkBean
 */
public class OrderBean implements EntityBean {

	// fields.
	public Integer order;		// primary key
	public int customer;

	// implementation of main logic

	public int getCustomer() { return customer; }
	public void setCustomer(int cust) { customer = cust; }

	// Create Methods
	// method implementation for Create/PostCreate
	public Order ejbCreate(Integer ord, int cust) throws CreateException{
		if (ord.intValue() == 0) {
			throw new CreateException("Order number cannot be zero");
		}
		order = ord;
		customer = cust;
		return null;
	}
	public Order ejbPostCreate(Integer ord, int cust) throws CreateException{
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
