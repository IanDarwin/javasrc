import javax.ejb.*;
import java.rmi.*;

public interface Home extends EJBHome {

	public Remote create() throws CreateException, RemoteException;

}
