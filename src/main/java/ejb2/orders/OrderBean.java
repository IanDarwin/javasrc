package ejb2.orders;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Implementation Class for Order EJB.
 * @author Ian Darwin, originally by MkBean
 */
public abstract class OrderBean implements EntityBean {

	// Virtual CMP fields.
	public abstract void setOrderNumber(Integer x);		// primary key
	public abstract Integer getOrderNumber();		// primary key
	public abstract void setCustomer(Integer id);
	public abstract Integer getCustomer();

	// Virtual CMR fields
	public abstract Collection<OrderItem> getOrderItems();
	public abstract void setOrderItems(Collection<OrderItem> c);

	private static int seqNumber;

	public void addItem(int product, int qty) {
		OrderItemLocalHome tmp;
		try {
			tmp = (OrderItemLocalHome)new InitialContext().
				lookup("local/OrderItemEJB");

			OrderItem l = tmp.create(new Integer(++seqNumber), product, qty);

			getOrderItems().add(l);
		} catch (NamingException ex) {
			throw new EJBException(ex);
		} catch (CreateException ex) {
			throw new EJBException(ex);
		}
	}

	// Create Methods
	// method implementation for Create/PostCreate
	public Integer ejbCreate(Integer ord, Integer cust) throws CreateException{
		if (ord.intValue() <= 0) {
			throw new CreateException("Order number must be positive integer");
		}
		setOrderNumber(ord);
		setCustomer(cust);
		return null;
	}

	public void ejbPostCreate(Integer ord, Integer cust) throws CreateException{
	}

	// Find methods will be implemented by deployment.

	/** reference to passed-in Session context */
	@SuppressWarnings("unused")
	private EntityContext ctx;

	// method implementations imposed by {type}Bean interface

	/** save the session context */
	public void setEntityContext(EntityContext x) { ctx = x; }
	public void unsetEntityContext() { ctx = null; }

	public void ejbActivate() {}
	public void ejbPassivate() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void ejbLoad() {}

}
