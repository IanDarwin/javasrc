package rain;

import java.rmi.*;
import javax.ejb.*;

/**
 *  Home interface for the Order EJB.
 *
 *  @author MkBean
 */

public interface OrderHome extends EJBHome {

	// Create Methods
	public Order create(Integer ord, int cust) throws RemoteException, CreateException;
	// Find Methods
	public  Order findByPrimaryKey(Integer ord) throws RemoteException;
}
