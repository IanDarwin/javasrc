import javax.ejb.*;
import java.rmi.*;

public interface MyMethods {

	/** This method will have RemoteException added in the Remote */
	public int computeMeaning() throws RemoteException;

}

