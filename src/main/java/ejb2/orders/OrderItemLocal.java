package orders;

import java.rmi.*;
import javax.ejb.*;

/**
 *  List of client methods for OrderItem
 *  @author MkBean
 */
public interface OrderItemLocal extends EJBLocalObject  {

	public int getProduct();
	public void setProduct(int p);
	public int getQuantity();
	public void setQuantity(int p);
}
