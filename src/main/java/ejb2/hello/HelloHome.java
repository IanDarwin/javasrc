import javax.ejb.*;
import java.rmi.*;

public interface HelloHome extends EJBHome {
	/** Stateless Session beans MUST have one create() method. */
	public Hello create() throws CreateException, RemoteException;
}
