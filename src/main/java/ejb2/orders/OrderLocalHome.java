package orders;

import java.rmi.*;
import javax.ejb.*;

/**
 *  Home interface for the Order EJB.
 *  @author MkBean
 */
public interface OrderLocalHome extends EJBLocalHome {

	// Create Methods
	public OrderLocal create(Integer ord, Integer cust) throws CreateException;

	// Find Methods
	public  OrderLocal findByPrimaryKey(Integer ord) throws FinderException;
}
