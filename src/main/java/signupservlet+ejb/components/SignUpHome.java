package components;

import java.rmi.*;
import javax.ejb.*;

/**
 *  Home interface for the SignUp EJB.
 *
 *  @author MkBean
 */

public interface SignUpHome extends EJBHome {

	// Create Methods
	public SignUp create(String name, String email)
						throws RemoteException, CreateException;
	// Find Methods
	public SignUp findByPrimaryKey(String name)
						throws RemoteException, FinderException;
}
