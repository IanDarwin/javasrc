package components;

import java.rmi.*;
import javax.ejb.*;

/**
 *  List of client methods for SignUp
 *
 *  @author MkBean
 */
public interface SignUp extends EJBObject  {

	public void setemail(String x) throws RemoteException;
	public String getemail() throws RemoteException;
}
