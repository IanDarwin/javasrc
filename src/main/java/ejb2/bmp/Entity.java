import javax.ejb.*;
import java.rmi.RemoteException;

public interface Entity extends EJBObject {

	public Recording getRecording() throws RemoteException;

}
