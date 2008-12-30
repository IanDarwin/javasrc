package ejb2.orders;

import java.util.Collection;

import javax.ejb.EJBLocalObject;

/**
 *  List of client methods for Order
 *
 *  @author MkBean
 */
public interface OrderLocal extends EJBLocalObject  {

	public Integer getCustomer();
	public void setCustomer(Integer i);

	public Collection getOrderItems();
	public void setOrderItems(Collection c);

	public void addItem(int product, int qty);
}
