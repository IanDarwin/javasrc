package orders;

import java.rmi.*;
import javax.ejb.*;
import java.util.Collection;

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
