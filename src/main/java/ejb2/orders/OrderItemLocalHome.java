package orders;

import java.rmi.*;
import javax.ejb.*;

/**
 *  Home interface for the OrderItem EJB.
 *  @author MkBean
 */
public interface OrderItemLocalHome extends EJBLocalHome {

	// Create Methods
	public OrderItemLocal create(Integer ord, int prod, int qty)
		throws CreateException;

	// Find Methods
	public  OrderItemLocal findByPrimaryKey(Integer ord)
		throws FinderException;
}
