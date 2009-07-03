package ejb2.orders;

import javax.ejb.EJBLocalObject;

/**
 *  List of client methods for OrderItem
 *  @author MkBean
 */
public interface OrderItem extends EJBLocalObject  {

	public int getProduct();
	public void setProduct(int p);
	public int getQuantity();
	public void setQuantity(int p);
}
